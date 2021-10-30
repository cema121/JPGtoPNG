package com.hungryshark.jpgtopng.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView, IImageGetter, MessageShower {
    fun setImageSrc(src: String)
    fun showDisposeDialog()
    fun hideDisposeDialog()
}