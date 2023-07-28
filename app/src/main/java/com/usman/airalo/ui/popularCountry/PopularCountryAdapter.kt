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

/**
 * An adapter for displaying a list of popular countries in a [RecyclerView].
 *
 * @param countryList The list of popular countries to be displayed.
 * @param onCountryClick The click listener for handling country item clicks. It takes a country ID as a parameter.
 */
class PopularCountryAdapter(
    private val countryList: CountryList,
    private val onCountryClick: (countryId: String) -> Unit,
) : RecyclerView.Adapter<PopularCountryAdapter.CountryViewHolder>() {

    //region Country

    /**
     * Called when the [RecyclerView.ViewHolder] is created and returns a [CountryViewHolder].
     *
     * @param parent The [ViewGroup] into which the new [View] will be added after it is bound to an adapter position.
     * @param viewType The view type of the new [View].
     * @return A new [CountryViewHolder] that holds the [ItemCountryBinding].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding =
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = CountryViewHolder(binding)
        onCountryItemClicked(viewHolder)
        return viewHolder
    }

    /**
     * Sets the click listener for handling country item clicks.
     *
     * @param viewHolder The [CountryViewHolder] for the country item.
     */
    private fun onCountryItemClicked(viewHolder: CountryViewHolder) {
        viewHolder.parent.setOnClickListener {
            onCountryClick(countryList[viewHolder.adapterPosition].slug)
        }
    }

    /**
     * Called when a [CountryViewHolder] needs to be updated with new data.
     *
     * @param holder The [CountryViewHolder] to be updated.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = countryList[position]
        holder.bind(item)
    }

    /**
     * Returns the number of items in the adapter's data set.
     *
     * @return The total number of items in the country list.
     */
    override fun getItemCount(): Int {
        return countryList.size
    }

    //endregion

    //region View Holder

    /**
     * [RecyclerView.ViewHolder] class representing an item view in the [PopularCountryAdapter].
     *
     * @param binding The [ItemCountryBinding] for the country item view.
     */
    inner class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * The parent view of the country item.
         */
        val parent = binding.countryParent

        /**
         * Binds the data of the country item to the [CountryViewHolder].
         *
         * @param item The [CountryItem] representing the data of the country item.
         */
        fun bind(item: CountryItem) {
            binding.txtCountry.text = item.title
            loadImage(binding.imgFlag, item.image.url)
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
