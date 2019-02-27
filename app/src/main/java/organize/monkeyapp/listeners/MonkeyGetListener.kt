package organize.monkeyapp.listeners

import org.greenrobot.eventbus.Subscribe
import organize.monkeyapp.event.PostFeedEvent
import organize.monkeyapp.event.ShowInformationEvent
import organize.monkeyapp.network.models.MonkeyModel

/**
 * Created by organize on 11/12/2017.
 */
interface MonkeyGetListener {
    fun showMonkeys(value: List<MonkeyModel>?)
    fun showMonkeyInformation(value: MonkeyModel?)
    fun postSucced()
    fun postSuccedOverdose()
    fun getFeedSucced(value: ArrayList<Int>?)
    fun showError()
}