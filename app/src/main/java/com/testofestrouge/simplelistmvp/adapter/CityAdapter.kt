package com.testofestrouge.simplelistmvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testofestrouge.simplelistmvp.CityDataModel
import com.testofestrouge.simplelistmvp.R
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.NumberFormat

class CityAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var listOfCity: List<CityDataModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CityListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfCity.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cityViewHolder = holder as CityListViewHolder
        cityViewHolder.bindView(listOfCity[position])
    }

    fun setCityList(listOfCity: List<CityDataModel>) {
        this.listOfCity = listOfCity
        notifyDataSetChanged()
    }

    inner class CityListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        fun bindView(cityData: CityDataModel)
        {
            itemView.tv_country.text = cityData.country
            itemView.tv_city.text    = cityData.city
            itemView.tv_population.text = formatText(cityData.population)
        }

        private fun formatText(numberPopularion: Int):String{
            val number = numberPopularion.toDouble()
            val textFormat = NumberFormat.getPercentInstance().format(number)
            return textFormat.replace("%","")
        }
    }
}