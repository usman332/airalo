package com.usman.airalo.ui.popularCountry

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.usman.airalo.databinding.ItemCountryBinding
import com.usman.common.entities.country.CountryItem
import com.usman.common.entities.country.CountryList

class PopularCountryAdapter(
    private val countryList: CountryList,
    private val onCountryClick: (countryId:String) -> Unit,
) :
    RecyclerView.Adapter<PopularCountryAdapter.CountryViewHolder>() {

    //region Country
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding =
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = CountryViewHolder(binding)
        onCountryItemClicked(viewHolder)
        return viewHolder
    }

    private fun onCountryItemClicked(viewHolder: CountryViewHolder) {
        viewHolder.parent.setOnClickListener {
            onCountryClick(countryList[viewHolder.adapterPosition].slug)
        }

    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = countryList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
    //endregion

    //region View Holder
    inner class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val parent = binding.countryParent

        fun bind(item: CountryItem) {
            binding.txtCountry.text = item.title
            loadImage(binding.imgFlag, item.image.url)

        }

        private fun loadImage(image: ImageView, url: String) = Glide.with(image)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .load(url)
            .into(image)
    }
    //endregion
}