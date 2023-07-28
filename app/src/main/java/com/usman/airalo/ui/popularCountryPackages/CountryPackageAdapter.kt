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

/**
 * An adapter for displaying a list of country packages in a [RecyclerView].
 *
 * @param packages The list of [PackageInfo] items to be displayed.
 */
class CountryPackageAdapter(
    private val packages: ArrayList<PackageInfo>,
) : RecyclerView.Adapter<CountryPackageAdapter.PackageViewHolder>() {

    //region Country

    /**
     * Called when the [RecyclerView.ViewHolder] is created and returns a [PackageViewHolder].
     *
     * @param parent The [ViewGroup] into which the new [View] will be added after it is bound to an adapter position.
     * @param viewType The view type of the new [View].
     * @return A new [PackageViewHolder] that holds the [ItemCountryPackageBinding].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        val binding =
            ItemCountryPackageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = PackageViewHolder(binding)
        onCountryItemClicked(viewHolder)
        return viewHolder
    }

    /**
     * Sets the click listener for handling country package item clicks.
     * This function is currently commented out in the code.
     *
     * @param viewHolder The [PackageViewHolder] for the country package item.
     */
    private fun onCountryItemClicked(viewHolder: PackageViewHolder) {
        /*
         * viewHolder.parent.setOnClickListener {
         *    onCountryClick(countryList[viewHolder.adapterPosition].slug)
         * }
         */
    }

    /**
     * Called when a [PackageViewHolder] needs to be updated with new data.
     *
     * @param holder The [PackageViewHolder] to be updated.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        val item = packages[position]
        holder.bind(item)
    }

    /**
     * Returns the number of items in the adapter's data set.
     *
     * @return The total number of items in the country packages list.
     */
    override fun getItemCount(): Int {
        return packages.size
    }

    //endregion

    //region View Holder

    /**
     * [RecyclerView.ViewHolder] class representing an item view in the [CountryPackageAdapter].
     *
     * @param binding The [ItemCountryPackageBinding] for the country package item view.
     */
    inner class PackageViewHolder(private val binding: ItemCountryPackageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the data of the country package item to the [PackageViewHolder].
         *
         * @param item The [PackageInfo] representing the data of the country package item.
         */
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

        /**
         * Loads the image into the provided [ImageView] using the [Glide] library.
         *
         * @param image The [ImageView] where the image will be loaded.
         * @param url The URL of the image to be loaded.
         */
        private fun loadImage(image: ImageView, url: String) =
            Glide.with(image)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .load(url)
                .into(image)
    }
    //endregion
}
