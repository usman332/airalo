package com.usman.airalo.ui.popularCountryPackages

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.usman.airalo.R

import com.usman.airalo.databinding.ItemCountryPackageBinding
import com.usman.common.entities.countryPackage.PackageInfo

class CountryPackageAdapter(
    private val packages: ArrayList<PackageInfo>,
) :
    RecyclerView.Adapter<CountryPackageAdapter.PackageViewHolder>() {

    //region Country
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        val binding =
            ItemCountryPackageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = PackageViewHolder(binding)
        onCountryItemClicked(viewHolder)
        return viewHolder
    }

    private fun onCountryItemClicked(viewHolder: PackageViewHolder) {
        /*
                viewHolder.parent.setOnClickListener {
                    onCountryClick(countryList[viewHolder.adapterPosition].slug)
                }
        */

    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        val item = packages[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return packages.size
    }
    //endregion

    //region View Holder
    inner class PackageViewHolder(private val binding: ItemCountryPackageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //val parent = binding.countryParent

        fun bind(item: PackageInfo) {
            binding.txtOperator.text = item.operatorInfo.title
            binding.txtCountry.text = item.operatorInfo.countries[0].title
            binding.txtDataMeasurement.text = item.dataInfo
            binding.txtValidityDays.text = item.validity
            binding.txtBuyPrice.text = buildString {
                append(binding.txtBuyPrice.context.getString(R.string.us))
                append(item.price)
                append("-")
                append(binding.txtBuyPrice.context.getString(R.string.buy_now))
            }
            loadImage(binding.imgCard, item.operatorInfo.image.url)

        }

        private fun loadImage(image: ImageView, url: String) = Glide.with(image)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .load(url)
            .into(image)
    }
    //endregion
}