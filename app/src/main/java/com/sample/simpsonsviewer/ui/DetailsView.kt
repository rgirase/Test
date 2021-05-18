package com.sample.simpsonsviewer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.data.model.RelatedTopic
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.databinding.ActivityDetailsViewBinding
import com.sample.simpsonsviewer.viewmodel.DataViewModel

class DetailsView : AppCompatActivity() {
    var viewModel: DataViewModel? = null
    var activityDetailsViewBinding: ActivityDetailsViewBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = DataViewModel()
        activityDetailsViewBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_details_view
        )
        val position = intent.extras?.getInt("character_position", 0)
        viewModel!!.getCharacterList()?.observe(
            this,
            Observer<List<RelatedTopic?>?> { list -> ///if any thing chnage the update the UI
                val imageLink =
                    position?.let { list?.get(it)?.FirstURL } + position?.let { list?.get(it)?.Icon?.URL }
                val options = RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                Glide.with(this).load(imageLink).apply(options)
                    .into(activityDetailsViewBinding?.characterIV)
                val charText = position?.let { list?.get(it)?.Text?.split("-") }
                activityDetailsViewBinding?.titleTv?.text = charText?.get(0)
                activityDetailsViewBinding?.descTv?.text = charText?.get(1)
            })
    }


}