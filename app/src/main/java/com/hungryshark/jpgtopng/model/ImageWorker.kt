package com.hungryshark.jpgtopng.model

import io.reactivex.rxjava3.core.Completable

interface ImageWorker {
    fun saveAsPng(filename: String): Completable
}