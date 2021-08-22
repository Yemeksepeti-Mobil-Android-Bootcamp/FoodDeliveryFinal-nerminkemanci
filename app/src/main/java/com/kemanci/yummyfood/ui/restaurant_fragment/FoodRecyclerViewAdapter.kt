package com.kemanci.yummyfood.ui.restaurant_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kemanci.yummyfood.R
import com.kemanci.yummyfood.databinding.FoodListRecyclerItemBinding
import com.kemanci.yummyfood.model.entity.Food
import com.kemanci.yummyfood.utils.RecyclerViewItemOnClickListener

class FoodRecyclerViewAdapter(
    private val foodList:ArrayList<Food>,
    private val context: Context,
    private val clicker:RecyclerViewItemOnClickListener):RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodViewHolder>(){



    inner class FoodViewHolder(private val binding:FoodListRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(food:Food,position: Int){
            binding.orderButton.setOnClickListener {
                clicker.onClick(position = position)
            }
            binding.textViewFoodName.text = food.name
            binding.textViewFoodPrice.text = food.price
            binding.textViewFoodIngredients.text = food.content

            val baseUrl = "https://yummyfoodserver.herokuapp.com"
            Glide.with(context).load(baseUrl.plus(food.image_url)).error(R.drawable.img_not_found).into(binding.imageViewFood)

        }

    }

    fun updateData(data:ArrayList<Food>){
        this.foodList.clear()
        this.foodList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding:FoodListRecyclerItemBinding = FoodListRecyclerItemBinding.inflate(LayoutInflater.from(context),parent,false)

        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        holder.bind(foodList[position],position)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}