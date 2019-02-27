package organize.monkeyapp.providers

import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import organize.monkeyapp.presenter.MonkeyPresenter
import javax.inject.Singleton

/**
 * Created by organize on 11/12/2017.
 */
@Module
class BusProvider {

    @Singleton
    @Provides
    fun provideBus(): EventBus {
        return EventBus()
    }
}