package com.mobifyall.mvvm.viewstates

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mobifyall.mvvm.models.Episode

data class EpisodeItemViewState(private val episode: Episode?, @Bindable val number: String) :
        BaseObservable() {
    @Bindable
    val titleEnglish: String? = episode?.let { "English : ${it.title}" }

    @Bindable
    val titleJapanese: String? = episode?.let { "Japanese : ${it.title_japanese}" }

    @Bindable
    val titleRomanji: String? = episode?.let { "Romanji : ${it.title_romanji}" }
}