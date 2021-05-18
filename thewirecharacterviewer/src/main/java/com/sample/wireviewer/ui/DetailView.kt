package com.sample.wireviewer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.data.model.RelatedTopic
import com.sample.wireviewer.R
import com.sample.wireviewer.databinding.ActivityDetailViewBinding
import com.sample.wireviewer.viewmodel.DataViewModel

class DetailView : AppCompatActivity() {
    var viewModel: DataViewModel? = null
    var activityDetailViewBinding: ActivityDetailViewBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailViewBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_detail_view
        )
        viewModel = DataViewModel()
        val position = intent.extras?.getInt("character_position", 0)
        viewModel!!.getWireCharacterList()?.observe(
            this,
            Observer<List<RelatedTopic?>?> { list -> ///if any thing chnage the update the UI
                val imageLink =
                    position?.let { list?.get(it)?.FirstURL } + position?.let { list?.get(it)?.Icon?.URL }
                val options = RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                Glide.with(this).load(imageLink).apply(options)
                    .into(activityDetailViewBinding?.characterIV)
                val charText = position?.let { list?.get(it)?.Text?.split("-") }
                activityDetailViewBinding?.titleTv?.text = charText?.get(0)
                activityDetailViewBinding?.descTv?.text = charText?.get(1)
            })
    }


}