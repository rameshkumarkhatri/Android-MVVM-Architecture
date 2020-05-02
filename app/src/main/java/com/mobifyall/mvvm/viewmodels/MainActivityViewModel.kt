package com.mobifyall.mvvm.viewmodels

import androidx.lifecycle.*
import com.mobifyall.mvvm.models.ServiceResponse
import com.mobifyall.mvvm.repositories.EpisodesRepository

class MainActivityViewModel(private val repository: EpisodesRepository) : ViewModel() {
    private val query = MutableLiveData<String>()
    val episodesLiveData: LiveData<ServiceResponse> = Transformations.switchMap(query, ::getEpisodeFromRepo)
//    val episodesLiveData: LiveData<ServiceResponse> = Transformations.switchMap(query, {q -> repository.getEpisodes()})

    private fun getEpisodeFromRepo(pageNo: String) = repository.getEpisodes()

    fun getEpisodes() = apply { query.value = "" }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: EpisodesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(repository) as T
        }
    }
}