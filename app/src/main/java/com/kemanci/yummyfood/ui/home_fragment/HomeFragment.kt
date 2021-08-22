package com.kemanci.yummyfood.ui.home_fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kemanci.yummyfood.R
import com.kemanci.yummyfood.databinding.HomeFragmentBinding
import com.kemanci.yummyfood.model.entity.AccountResponse
import com.kemanci.yummyfood.utils.Common
import com.kemanci.yummyfood.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var accountResponse:AccountResponse? = null
    private lateinit var binding:HomeFragmentBinding
    private val viewModel:HomeViewModel by viewModels()
    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var orderRecyclerViewAdapter: OrderRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  HomeFragmentBinding.inflate(layoutInflater,container,false)

        accountResponse =  HomeFragmentArgs.fromBundle(requireArguments()).accountResponse
        populateViews(
            fullname = accountResponse?.account!!.name.plus(" ".plus(accountResponse?.account!!.surname)),
            province = accountResponse?.account!!.province,
            county = accountResponse?.account!!.county
        )
        ordersRecyclerView = binding.previousOrderRecyclerView
        ordersRecyclerView.layoutManager = LinearLayoutManager(this.context)
        orderRecyclerViewAdapter = OrderRecyclerViewAdapter(
            context = this.requireContext(),
            ordersList = accountResponse!!.account.orders!!
        )

        observeOrders()


        ordersRecyclerView.adapter  = orderRecyclerViewAdapter

        binding.settingsButton.setOnClickListener{
            Common.alphaAnim(binding.settingsButton)
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
        }

        binding.setAddressButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
        }

        binding.materialButton.setOnClickListener {
            Log.e("TAG", "onCreateView: "+accountResponse!!.account.province )
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRestaurantListFragment(accountResponse!!.account.province))
        }

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /*fun getProfile(){
        if (accountResponse!=null) return
        viewModel.profile(accountResponse!!.token).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS ->{

                }
                Resource.Status.ERROR -> {

                }
            }
        })
    }*/

    @SuppressLint("NotifyDataSetChanged")
    fun observeOrders(){
        binding.progressLayout.visibility = View.VISIBLE
        accountResponse!!.account.orders!!.forEach { order ->
            viewModel.getFoodById(order.food_id).observe(viewLifecycleOwner,{
                if(it.status == Resource.Status.SUCCESS){
                    if (order.food_id!=it.data!!._id){
                        Log.e("TAG", "observeOrders:yemek hata var" )
                    }
                    order.foodName = it.data.name;
                    order.foodPrice = it.data.price;
                    this.orderRecyclerViewAdapter.notifyDataSetChanged()
                    binding.progressLayout.visibility = View.GONE

                }
            })
            viewModel.getRestaurantById(order.restaurant_id).observe(viewLifecycleOwner,{
                if(it.status == Resource.Status.SUCCESS){
                    if(order.restaurant_id!=it.data!!._id){
                        Log.e("TAG", "observeOrders:restoran hata var" )
                    }
                    order.restaurantName = it.data.name
                    order.restaurantPoint = it.data.point.toString()
                    this.orderRecyclerViewAdapter.notifyDataSetChanged()
                    binding.progressLayout.visibility = View.GONE
                }
            })
        }
    }

    fun populateViews(fullname:String,province:String,county:String){
        binding.fullNameTextView.text = fullname
        binding.provinceTextView.text = province
        binding.countyTextView.text = county
    }


}