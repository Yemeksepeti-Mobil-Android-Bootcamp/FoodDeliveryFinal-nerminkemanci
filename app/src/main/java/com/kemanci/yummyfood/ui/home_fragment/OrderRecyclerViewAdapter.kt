package com.kemanci.yummyfood.ui.home_fragment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kemanci.yummyfood.databinding.OrderItemLayoutBinding
import com.kemanci.yummyfood.model.entity.Order
import com.kemanci.yummyfood.utils.RecyclerViewItemOnClickListener

class OrderRecyclerViewAdapter(
    private val ordersList:ArrayList<Order>,
    private val context: Context,
) :RecyclerView.Adapter<OrderRecyclerViewAdapter.OrdersViewHolder>()
{
    inner class OrdersViewHolder(private val binding: OrderItemLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Order){
            binding.restaurantPointTextview.text = item.restaurantPoint
            binding.restaurantNameText.text = item.restaurantName
            binding.foodNameText.text = item.foodName
            binding.foodPriceText.text = item.foodPrice
            binding.dateTextView.text = item.order_date.toString().replace("-","/")
            var alphaVal:Double = 1.0
            var colorCode:String = "#81BF4A"
            if (item.restaurantPoint==null){
                item.restaurantPoint = "3"
            }
            if (item.restaurantPoint.isNullOrBlank()){
                item.restaurantPoint = "-1"
            }
            if (item.restaurantPoint.toDouble()>3){
                alphaVal = item.restaurantPoint.toDouble().div(5.0)
            }
            else if(item.restaurantPoint.toDouble()<3){
                colorCode = "#FF6347"
            }
            else{
                colorCode = "#FFFFFF"
                alphaVal = 0.0;
            }
            binding.restaurantPointCard.setCardBackgroundColor(Color.parseColor(colorCode))
            binding.restaurantPointCard.alpha = alphaVal.toFloat()
        }
    }



    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val root: OrderItemLayoutBinding = OrderItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder(root)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.bind(ordersList[position])
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    fun updateRestaurantList(itemList:ArrayList<Order>){
        this.ordersList.clear()
        this.ordersList.addAll(itemList)
        notifyDataSetChanged()
    }
}
