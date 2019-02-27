package organize.monkeyapp.providers

import dagger.Module
import dagger.Provides
import organize.monkeyapp.presenter.MonkeyPresenter
import javax.inject.Singleton

/**
 * Created by organize on 10/12/2017.
 */
@Module
class PresenterProvider {

    @Singleton
    @Provides
    fun providePresenter(): MonkeyPresenter {
        return MonkeyPresenter()
    }
}