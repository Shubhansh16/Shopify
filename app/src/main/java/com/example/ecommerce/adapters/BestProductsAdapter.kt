package com.example.ecommerce.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.data.Products
import com.example.ecommerce.databinding.BestDealsRvItemBinding
import com.example.ecommerce.databinding.ProductRvItemBinding

class BestProductsAdapter:RecyclerView.Adapter<BestProductsAdapter.BestProductsViewHolder>() {

    inner class BestProductsViewHolder(private val binding: ProductRvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(products: Products){
            binding.apply {
                Glide.with(itemView).load(products.images[0]).into(imgProduct)
                products.offerPercentage?.let {
                    val remainingPricePercentage = 1f - it
                    val priceAfterOffer= remainingPricePercentage * products.price
                    tvNewPrice.text="₹ ${String.format("%.2f",priceAfterOffer)}"
                    tvPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                if (products.offerPercentage == null)
                    tvNewPrice.visibility= View.INVISIBLE
                tvPrice.text ="₹ ${products.price}"
                tvName.text=products.name
            }
        }
    }
    private val diffCallback= object : DiffUtil.ItemCallback<Products>(){
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductsViewHolder {
        return BestProductsViewHolder(
            ProductRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BestProductsViewHolder, position: Int) {
        val products= differ.currentList[position]
        holder.bind(products)
    }

}