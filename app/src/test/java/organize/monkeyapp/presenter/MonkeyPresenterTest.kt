package organize.monkeyapp.presenter

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Test

import org.junit.Assert.*
import org.junit.BeforeClass
import org.mockito.InOrder
import org.mockito.Mockito
import organize.monkeyapp.listeners.MonkeyGetListener
import organize.monkeyapp.network.api.MonkeyApi
import organize.monkeyapp.network.models.MonkeyModel
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * Created by organize on 12/12/2017.
 */
class MonkeyPresenterTest {
    companion object {
        @JvmStatic
        @BeforeClass
        fun setUpRxSchedulers() {
            val immediate = object : Scheduler() {
                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                    return super.scheduleDirect(run, 0, unit)
                }

                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }
            RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
        }
    }

    @Test
    fun getMonkeys() {
        val monkeyApi = org.mockito.Mockito.mock<MonkeyApi>(MonkeyApi::class.java)
        val listener = org.mockito.Mockito.mock<MonkeyGetListener>(MonkeyGetListener::class.java)
        var monkeyModel = MonkeyModel()
        var monkeyPresenter = MonkeyPresenter()
        monkeyPresenter.mMonkeyGetListener = listener
        monkeyPresenter.mMonkeyApi = monkeyApi

        var listMange = ArrayList<MonkeyModel>()

        for (i in 0..5) {
            monkeyModel.name = "monkey" + i
            monkeyModel.id = i
            listMange.add(i, monkeyModel)
        }

        Mockito.`when`(monkeyApi?.getMonkeys("test@gmail.com"))
                .thenReturn(Observable.just(listMange))
        monkeyPresenter?.getMonkeys("test@gmail.com")

        var inOrder: InOrder = Mockito.inOrder(listener)
        inOrder.verify(listener, Mockito.times(1))?.showMonkeys(listMange)
    }

    @Test
    fun getMonkeyInformation() {
        val monkeyApi = org.mockito.Mockito.mock<MonkeyApi>(MonkeyApi::class.java)
        val listener = org.mockito.Mockito.mock<MonkeyGetListener>(MonkeyGetListener::class.java)
        var monkeyModel = MonkeyModel()
        var monkeyPresenter = MonkeyPresenter()
        monkeyPresenter.mMonkeyGetListener = listener
        monkeyPresenter.mMonkeyApi = monkeyApi


        monkeyModel.name = "monkey" + 1
        monkeyModel.id = 1

        Mockito.`when`(monkeyApi?.getMonkeyInformation("test@gmail.com", 1))
                .thenReturn(Observable.just(monkeyModel))
        monkeyPresenter?.getMonkeyInformation("test@gmail.com", 1)

        var inOrder: InOrder = Mockito.inOrder(listener)
        inOrder.verify(listener, Mockito.times(1))?.showMonkeyInformation(monkeyModel)
    }

}