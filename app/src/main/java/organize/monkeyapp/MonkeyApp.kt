package organize.monkeyapp

import android.app.Application
import organize.monkeyapp.providers.AppComponent
import organize.monkeyapp.providers.DaggerAppComponent

/**
 * Created by organize on 11/12/2017.
 */
class MonkeyApp : Application() {

    companion object {
        var mAppComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder().build()
    }

}