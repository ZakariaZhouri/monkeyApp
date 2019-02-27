package organize.monkeyapp.presenter

import organize.monkeyapp.MonkeyApp
import organize.monkeyapp.network.api.MonkeyApi
import javax.inject.Inject

/**
 * Created by organize on 10/12/2017.
 */
open class MainPresenter {
    constructor()


    init {
        MonkeyApp.mAppComponent?.inject(this)
    }

    @Inject
    lateinit var mMonkeyApi: MonkeyApi

}