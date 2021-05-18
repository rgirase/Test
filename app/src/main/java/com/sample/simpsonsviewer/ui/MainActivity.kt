package com.sample.simpsonsviewer.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.data.model.RelatedTopic
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.adapter.RecyclerViewAdapter
import com.sample.simpsonsviewer.databinding.ActivityMainBinding
import com.sample.simpsonsviewer.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {
    var viewModel: DataViewModel? = null
    var recyclerView: RecyclerView? = null
    var recyclerViewAdapter: RecyclerViewAdapter? = null
    var activityMainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        recyclerView = activityMainBinding?.rvsimpson
        viewModel = DataViewModel()
        viewModel!!.getCharacterList()?.observe(
            this,
            Observer<List<RelatedTopic?>?> { list -> ///if any thing chnage the update the UI
                recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity, list)

                recyclerView?.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView?.adapter = recyclerViewAdapter

                activityMainBinding?.loadBar?.visibility = View.GONE
            })

        activityMainBinding?.searchID?.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerViewAdapter?.filter?.filter(newText)
                return false
            }

        })
    }

}