package com.mobifyall.mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobifyall.mvvm.models.EpisodesResponse
import com.mobifyall.mvvm.models.ServiceResponse
import com.mobifyall.mvvm.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ramesh Kumar
 * r.khatri91@gmail.com
 */
class EpisodesRepositoryImp(private val apiService: ApiService) : EpisodesRepository {
    private val _liveData: MutableLiveData<ServiceResponse> by lazy {
        MutableLiveData<ServiceResponse>()
    }

    override fun getEpisodes(): LiveData<ServiceResponse> {
        apiService.getEpisodes().enqueue(object : Callback<EpisodesResponse> {
            override fun onFailure(call: Call<EpisodesResponse>, t: Throwable) {
                _liveData.value = ServiceResponse(isSuccess = false, message = t.localizedMessage)
            }

            override fun onResponse(call: Call<EpisodesResponse>,
                                    response: Response<EpisodesResponse>) {
                _liveData.value = ServiceResponse(isSuccess = response.code() == 200,
                        message = response.message(), data = response.body(),
                        code = response.code())
            }
        })
        return _liveData
    }
}

interface EpisodesRepository {
    fun getEpisodes(): LiveData<ServiceResponse>
}