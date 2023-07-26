package com.example.ecommerce.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.ecommerce.data.Products
import com.example.ecommerce.databinding.BestDealsRvItemBinding
import com.example.ecommerce.databinding.SpecialRvItemBinding

class BestDealsAdapter:RecyclerView.Adapter<BestDealsAdapter.BestDealsViewHolder>() {

    inner class BestDealsViewHolder(private val binding: BestDealsRvItemBinding): ViewHolder(binding.root){
        fun bind(products: Products){
            binding.apply {
                Glide.with(itemView).load(products.images[0]).into(imgBestDeal)
                products.offerPercentage?.let {
                    val remainingPricePercentage = 1f - it
                    val priceAfterOffer= remainingPricePercentage * products.price
                    tvNewPrice.text="₹ ${String.format("%.2f",priceAfterOffer)}"
                }
                tvOldPrice.text= "₹ ${products.price}"
                tvDealProductName.text=products.name
            }
        }
    }
    private val diffCallback= object :DiffUtil.ItemCallback<Products>(){
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
          return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
           return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestDealsViewHolder {
        return BestDealsViewHolder(
            BestDealsRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BestDealsViewHolder, position: Int) {
        val products= differ.currentList[position]
        holder.bind(products)
    }

}