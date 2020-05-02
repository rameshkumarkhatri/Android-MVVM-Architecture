package com.mobifyall.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobifyall.mvvm.adapters.EpisodesNameAdapter
import com.mobifyall.mvvm.databinding.ActivityMainBinding
import com.mobifyall.mvvm.repositories.EpisodesRepositoryImp
import com.mobifyall.mvvm.service.RetrofitFactory
import com.mobifyall.mvvm.viewmodels.MainActivityViewModel
import com.mobifyall.mvvm.viewstates.MainActivityViewState

/**
 * Created by Ramesh Kumar
 * r.khatri91@gmail.com
 */
class MainActivity : AppCompatActivity() {
    //it's always better to use the lateinit when you know it will initialize before the call otherwise nullable
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewState: MainActivityViewState

    /**
     * lazy will get initialized when it's called first time
     * this factory will created the object of MainActivityViewModel class
     * because we can not create viewmodel using provider with paratermized constructor
     */

    private val repository: EpisodesRepositoryImp by lazy {
        EpisodesRepositoryImp(RetrofitFactory.service)
    }
    private val viewModel: MainActivityViewModel by lazy {
        val factory = MainActivityViewModel.Factory(repository = repository)
        ViewModelProviders.of(this, factory)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rv.layoutManager = LinearLayoutManager(this)
        viewState = MainActivityViewState()
        binding.viewState = viewState

        viewModel.episodesLiveData.observe(this, Observer {
            viewState.apiInProgress = false
            if(it.isSuccess) {
                binding.rv.adapter = EpisodesNameAdapter(it.data?.episodes)
            } else {
                viewState.setError(it.message)
            }
        })

        viewModel.getEpisodes()
    }
}
