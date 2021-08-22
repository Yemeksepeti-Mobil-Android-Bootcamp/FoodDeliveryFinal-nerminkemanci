package com.kemanci.yummyfood.ui.restaurant_list_fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kemanci.yummyfood.databinding.RestaurantListFragmentBinding
import com.kemanci.yummyfood.model.entity.Restaurant
import com.kemanci.yummyfood.utils.RecyclerViewItemOnClickListener
import com.kemanci.yummyfood.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListFragment:Fragment(){

    private lateinit var binding:RestaurantListFragmentBinding
    private val viewModel:RestaurantListViewModel by viewModels()
    private lateinit var restaurantList:ArrayList<Restaurant>
    private lateinit var restaurantListRecyclervView: RecyclerView
    private lateinit var restaurantRecyclerViewAdapter: RestaurantRecyclerViewAdapter
    private var province:String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        requireActivity().window.statusBarColor = Color.parseColor("#6C9FA0")
        restaurantList = ArrayList<Restaurant>()
        binding =  RestaurantListFragmentBinding.inflate(inflater, container, false)
        restaurantListRecyclervView = binding.restaurantListRecyclerview
        province = RestaurantListFragmentArgs.fromBundle(requireArguments()).province.toString()
        restaurantListRecyclervView.layoutManager = LinearLayoutManager(this.context)
        restaurantRecyclerViewAdapter = RestaurantRecyclerViewAdapter(
            restaurantList = restaurantList,
            context = requireContext(),
            recyclerViewItemOnClickListener = object:RecyclerViewItemOnClickListener{
                override fun onClick(position: Int) {
                    findNavController().navigate(RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantFragment(restaurantId = restaurantList[position]._id))
                }
            }
        )
        restaurantListRecyclervView.adapter = restaurantRecyclerViewAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRestaurantList()
    }

    fun observeRestaurantList(){
        viewModel.getRestaurantByProvince(province).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.LOADING -> binding.progressLayout.visibility = View.VISIBLE
                Resource.Status.SUCCESS -> {
                    binding.progressLayout.visibility = View.GONE

                    restaurantRecyclerViewAdapter.updateRestaurantList(it.data!!)
                }
                Resource.Status.ERROR -> {
                    binding.progressLayout.visibility = View.GONE
                }
            }
        })
    }
}