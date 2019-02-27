package organize.monkeyapp.presenter

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import organize.monkeyapp.listeners.MonkeyGetListener
import organize.monkeyapp.network.models.MonkeyModel
import retrofit2.Response

/**
 * Created by organize on 10/12/2017.
 */
class MonkeyPresenter : MainPresenter() {

    var mMonkeyGetListener: MonkeyGetListener? = null


    fun getMonkeys(mail: String?) {
        val observable = mMonkeyApi.getMonkeys(mail)
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<List<MonkeyModel>> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(value: List<MonkeyModel>?) {
                mMonkeyGetListener?.showMonkeys(value)
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable?) {
                mMonkeyGetListener?.showError()
            }
        })
    }

    fun getMonkeyInformation(mail: String?, id: Int?) {
        val observable = mMonkeyApi.getMonkeyInformation(mail, id)
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<MonkeyModel> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(value: MonkeyModel?) {
                mMonkeyGetListener?.showMonkeyInformation(value)
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable?) {
                mMonkeyGetListener?.showError()
            }
        })
    }

    fun postFeed(mail: String?, listId: ArrayList<Int>) {
        val observable = mMonkeyApi.postFeed(mail, listId)
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Response<Void>> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(value: Response<Void>) {
                if (value.errorBody() != null) {
                    mMonkeyGetListener?.postSuccedOverdose()
                } else {
                    mMonkeyGetListener?.postSucced()
                }
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable?) {
                mMonkeyGetListener?.showError()
            }
        })
    }

    fun getFeed(mail: String?) {
        val observable = mMonkeyApi.getFeed(mail)
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<ArrayList<Int>?> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(value: ArrayList<Int>?) {
                mMonkeyGetListener?.getFeedSucced(value)
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable?) {
                mMonkeyGetListener?.showError()
            }
        })
    }


}