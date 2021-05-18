package com.sample.wireviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sample.data.datarepository.DataRepository
import com.sample.data.model.RelatedTopic

class DataViewModel : ViewModel() {

    fun getCharacterList(): LiveData<List<RelatedTopic>>? {
        return DataRepository.getSimpsonCharacters()
    }

    fun getWireCharacterList(): LiveData<List<RelatedTopic>>? {
        return DataRepository.getTheWireCharacters()
    }

}