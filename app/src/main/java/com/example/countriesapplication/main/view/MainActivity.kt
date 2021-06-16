package com.example.countriesapplication.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapplication.R
import com.example.countriesapplication.main.model.Country
import com.example.countriesapplication.main.viewmodel.CountryViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var vm: CountryViewModel
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.rv_countries)

        findViewById<ImageButton>(R.id.btn_search).setOnClickListener(this)

        vm = ViewModelProvider(this)[CountryViewModel::class.java]

        initAdapter(recyclerView)

        vm.getAllCountries()

        vm.countryLiveData?.observe(this, Observer {

            findViewById<ProgressBar>(R.id.pb_loading).visibility = View.GONE
            if (it!=null) {
                adapter.setData(it as ArrayList<Country>)
            } else {

            }
        })
    }

    private fun initAdapter(rv: RecyclerView) {
        adapter = CountryAdapter()
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_search -> {
                var recyclerView = findViewById<RecyclerView>(R.id.rv_countries)
                var progressBar = findViewById<ProgressBar>(R.id.pb_loading)
                var searchTerm = findViewById<EditText>(R.id.et_search).text.toString()
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.INVISIBLE

                if (searchTerm == "") {
                    vm.getAllCountries()
                    vm.countryLiveData?.observe(this, Observer {

                        progressBar.visibility = View.GONE
                        if (it != null) {
                            recyclerView.visibility = View.VISIBLE
                            adapter.setData(it as ArrayList<Country>)
                        } else {

                        }
                    })
                } else {
                    vm.getCountryByName(searchTerm)
                    vm.countryLiveData?.observe(this, Observer {

                        progressBar.visibility = View.GONE
                        if (it != null) {
                            recyclerView.visibility = View.VISIBLE
                            adapter.setData(it as ArrayList<Country>)
                        } else {

                        }
                    })
                }
            }
        }
    }
}