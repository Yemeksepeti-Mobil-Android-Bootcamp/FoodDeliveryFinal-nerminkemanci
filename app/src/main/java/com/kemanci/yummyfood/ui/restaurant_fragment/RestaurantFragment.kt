package com.kemanci.yummyfood.ui.restaurant_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kemanci.yummyfood.R
import com.kemanci.yummyfood.databinding.RestaurantFragmentBinding
import com.kemanci.yummyfood.model.entity.Food
import com.kemanci.yummyfood.utils.RecyclerViewItemOnClickListener
import com.kemanci.yummyfood.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class RestaurantFragment:Fragment() {
    private lateinit var binding: RestaurantFragmentBinding
    private val viewModel:RestaurantViewModel by viewModels()
    private lateinit var foodRecyclerViewAdapter: FoodRecyclerViewAdapter
    private lateinit var foodListRecyclerView: RecyclerView
    private lateinit var foodList:ArrayList<Food>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RestaurantFragmentBinding.inflate(inflater,container,false)
        foodListRecyclerView = binding.recyclerViewFoodList
        foodList = ArrayList()
        foodListRecyclerView.layoutManager = LinearLayoutManager(context)
        foodRecyclerViewAdapter = FoodRecyclerViewAdapter(
            foodList = foodList,
            context = requireContext(),
            clicker = object:RecyclerViewItemOnClickListener{
                override fun onClick(position: Int) {
                    Log.e("TAG", "onClick: ", )
                    viewModel.wishOrder(
                        account_id = viewModel.getToken().toString(),
                        restaurant_id = foodList[position].restaurant_id,
                        food_id = foodList[position]._id
                    ).observe(viewLifecycleOwner,{})
                }
            }
        )
        foodListRecyclerView.adapter = foodRecyclerViewAdapter
        observeFoods()
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun observeFoods(){
        val _id:String = RestaurantFragmentArgs.fromBundle(requireArguments()).restaurantId.toString()
        viewModel.getRestaurantById(_id).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.SUCCESS -> {
                    binding.textViewRestaurantAddress.text = it.data?.address
                    binding.textViewRestaurantScore.text = it.data?.point.toString()
                    binding.textViewRestaurantName.text = it.data?.name
                    binding.textViewRestaurantMinDeliveryPrice.text = it.data?.min_order.toString().plus("TL")
                    binding.textViewRestaurantDeliveryTime.text = it.data?.average_delivery_time.toString().plus(" dk")
                    foodRecyclerViewAdapter.updateData(it.data!!.menu!!)
                    val baseUrl = "https://yummyfoodserver.herokuapp.com"
                    Glide.with(requireContext()).load(baseUrl.plus(it.data.image_url)).error(R.drawable.img_not_found).into(binding.imageViewRestaurantLogo)
                }
            }
        })
    }
}