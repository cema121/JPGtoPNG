package com.hungryshark.jpgtopng.presenter

import com.hungryshark.jpgtopng.model.ImageWorker

interface IMainPresenter : ImageListener {

    fun onImagePressed()
    fun onConvertImagePressed()
    fun onCancelLoadingPressed()
    fun onLoadingFinished()
    fun onImageWorkerReset(img: ImageWorker)
}