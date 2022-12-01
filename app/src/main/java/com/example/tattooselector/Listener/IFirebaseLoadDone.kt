package com.example.tattooselector.Listener

import android.media.Image
import com.example.tattooselector.Model.Tattoo

interface IFirebaseLoadDone {
    fun onTattooLoadSuccess(imageList:List<Tattoo>)
    {

    }
    fun onTattooLoadFailed(message:String)
    {

    }
}