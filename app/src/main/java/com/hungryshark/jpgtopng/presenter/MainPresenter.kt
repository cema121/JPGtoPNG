package com.hungryshark.jpgtopng.presenter

import com.hungryshark.jpgtopng.model.ImageWorker
import com.hungryshark.jpgtopng.view.MainView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class MainPresenter : IMainPresenter, MvpPresenter<MainView>() {

    private var saveDisposable: Disposable? = null
    private var imageWorker: ImageWorker? = null

    override fun onImagePressed() {
        viewState.requestImage()
    }

    override fun onConvertImagePressed() {
        imageWorker?.apply {
            saveAsPng(DEFAULT_SAVE_FILENAME)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable?) {
                        viewState.showMessage(LOADING_STARTED_MESSAGE)
                        viewState.showDisposeDialog()
                        saveDisposable = d
                    }

                    override fun onComplete() {
                        viewState.showMessage(LOADING_FINISHED)
                        viewState.hideDisposeDialog()
                    }

                    override fun onError(e: Throwable?) {
                        viewState.hideDisposeDialog()
                        viewState.showMessage(e?.message ?: DEFAULT_ERROR)
                    }
                })
        }
    }

    override fun onCancelLoadingPressed() {
        saveDisposable?.dispose()
    }

    override fun onLoadingFinished() {
        viewState.showMessage(LOADING_FINISHED)
    }

    override fun onImageSelected(stringUri: String) {
        viewState.setImageSrc(stringUri)
    }

    override fun onImageWorkerReset(img: ImageWorker) {
        imageWorker = img
    }

    companion object {
        private const val DEFAULT_ERROR = "Sorry, something went wrong"
        private const val LOADING_STARTED_MESSAGE = "Loading started"
        private const val LOADING_FINISHED = "Loading finished"
        private const val DEFAULT_SAVE_FILENAME = "Some.png"
    }
}