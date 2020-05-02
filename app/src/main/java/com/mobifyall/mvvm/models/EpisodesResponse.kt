package com.mobifyall.mvvm.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EpisodesResponse(val request_hash: String? = null, val request_cached: Boolean = false,
                            val request_cache_expiry: Long, val episodes_last_page: Int = 0,
                            val episodes: List<Episode>) : Parcelable

@Parcelize
data class Episode(val episode_id: Int = 0, val title: String? = null,
                   val title_japanese: String? = null, val title_romanji: String? = null,
                   val aired: Boolean = false, val filler: Boolean = false,
                   val recap: Boolean = false, val video_url: String? = null,
                   val forum_url: String? = null) : Parcelable

data class ServiceResponse(val data: EpisodesResponse? = null, val code: Int = 0,
                           val message: String, val isSuccess: Boolean = true)