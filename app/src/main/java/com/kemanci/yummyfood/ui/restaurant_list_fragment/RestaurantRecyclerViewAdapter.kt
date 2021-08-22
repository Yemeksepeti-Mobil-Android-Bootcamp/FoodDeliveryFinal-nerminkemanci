package com.kemanci.yummyfood.ui.restaurant_list_fragment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kemanci.yummyfood.databinding.RestaurantListItemBinding
import com.kemanci.yummyfood.model.entity.Restaurant
import com.kemanci.yummyfood.utils.RecyclerViewItemOnClickListener

class RestaurantRecyclerViewAdapter(private var restaurantList:ArrayList<Restaurant>, val context: Context, val recyclerViewItemOnClickListener: RecyclerViewItemOnClickListener):
    RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder>() {

    fun updateRestaurantList(itemList:ArrayList<Restaurant>){
        this.restaurantList.clear()
        this.restaurantList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val root: RestaurantListItemBinding = RestaurantListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RestaurantViewHolder(root).listen{ position, type ->
            recyclerViewItemOnClickListener.onClick(position)
        }
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurantList[position])
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    inner class RestaurantViewHolder(private val binding: RestaurantListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Restaurant){
            binding.restaurantRemainTimeTextview.text = item.average_delivery_time.toString().plus(" dk")
            binding.restaurantMinPriceTextview.text = item.min_order.toString().plus(" TL")
            binding.restaurantPointTextview.text = item.point.toDouble().toString()


            var alphaVal:Double = 1.0
            var colorCode:String = "#81BF4A"

            if (item.point>3){
                alphaVal = item.point.div(5.0) ?: 1.0
            }
            else{
                colorCode = "#FF6347"
            }
            binding.restaurantPointCard.setCardBackgroundColor(Color.parseColor(colorCode))
            binding.restaurantPointCard.alpha = alphaVal.toFloat()
            binding.restaurantNameEdittext.text = item.name
            val baseUrl = "https://yummyfoodserver.herokuapp.com"
            Glide.with(context).load(baseUrl.plus(item.image_url)).into(binding.restaurantImageview)
        }

    }
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }


}