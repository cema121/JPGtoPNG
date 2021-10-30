package com.hungryshark.jpgtopng.view

import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface MessageShower {
    fun showMessage(message: String)
}