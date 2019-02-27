package organize.monkeyapp.providers

import dagger.Component
import organize.monkeyapp.presenter.MainPresenter
import organize.monkeyapp.ui.activity.MonkeyActivity
import organize.monkeyapp.ui.recycler.monkeyRecycler.MonkeyViewHolder
import organize.monkeyapp.utils.LoaderUtil
import javax.inject.Singleton

/**
 * Created by organize on 10/12/2017.
 */
@Singleton
@Component(modules = arrayOf(PresenterProvider::class, MonkeyApiProvider::class, BusProvider::class))

interface AppComponent {
    fun inject(typePresenter: MainPresenter)

    fun inject(monkeyActivity: MonkeyActivity)

    fun inject(monkeyViewHolder: MonkeyViewHolder)


}