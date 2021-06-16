package com.example.countriesapplication.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.countriesapplication.R
import com.example.countriesapplication.main.model.Country

class CountryAdapter: RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var data: ArrayList<Country>? = null

    fun setData(list: ArrayList<Country>){
        data = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.country_card, parent, false))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Country?) {
            itemView.findViewById<ImageView>(R.id.iv_flag).loadSVG(item?.flag ?: "")
            itemView.findViewById<TextView>(R.id.tv_name_value).text = item?.name
            itemView.findViewById<TextView>(R.id.tv_capital_value).text = item?.capital
            itemView.findViewById<TextView>(R.id.tv_region_value).text = item?.region
            itemView.findViewById<TextView>(R.id.tv_abbrv_value).text = item?.alpha2Code
            itemView.findViewById<TextView>(R.id.tv_call_value).text = item?.callingCodes?.joinToString(separator = ", ")
            itemView.findViewById<TextView>(R.id.tv_pop_value).text = item?.population.toString()
            itemView.findViewById<TextView>(R.id.tv_currname_value).text = item?.currencies?.get(0)?.name
            itemView.findViewById<TextView>(R.id.tv_currcode_value).text = item?.currencies?.get(0)?.code
            itemView.findViewById<TextView>(R.id.tv_currsym_value).text = item?.currencies?.get(0)?.symbol
            itemView.findViewById<TextView>(R.id.tv_latlng_value).text = item?.latlng?.joinToString(separator = ", ")
            itemView.findViewById<TextView>(R.id.tv_lang_value).text = item?.languages?.get(0)?.name
            itemView.findViewById<TextView>(R.id.tv_border_value).text = item?.borders?.joinToString(separator = ", ")
        }

        fun ImageView.loadSVG(url: String) {
            var imageLoader = ImageLoader.Builder(this.context)
                .componentRegistry{ add(SvgDecoder(this@loadSVG.context)) }
                .build()

            imageLoader.enqueue(
                ImageRequest.Builder(this.context)
                    .data(url)
                    .target(this)
                    .build())
        }
    }
}