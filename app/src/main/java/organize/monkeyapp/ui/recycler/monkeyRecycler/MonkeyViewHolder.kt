package organize.monkeyapp.ui.recycler.monkeyRecycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.greenrobot.eventbus.EventBus
import organize.monkeyapp.MonkeyApp
import organize.monkeyapp.R
import organize.monkeyapp.event.PostFeedEvent
import organize.monkeyapp.event.ShowInformationEvent
import organize.monkeyapp.network.models.MonkeyModel
import organize.monkeyapp.presenter.MonkeyPresenter
import javax.inject.Inject

/**
 * Created by organize on 11/12/2017.
 */
class MonkeyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var mMonkeyName: TextView?
    var mMokeyId: TextView?
    var mFeedBtn: Button?
    var mShowInformationBtn: Button?

    @Inject
    lateinit var bus: EventBus

    init {
        MonkeyApp.mAppComponent?.inject(this)
        mMonkeyName = itemView?.findViewById(R.id.monkey_name)!!
        mMokeyId = itemView?.findViewById(R.id.monkey_id)!!
        mFeedBtn = itemView?.findViewById(R.id.feed_btn)!!
        mShowInformationBtn = itemView?.findViewById(R.id.show_information_btn)!!
    }

    fun bindInformation(monkeyModel: MonkeyModel?, feedClick: Boolean) {
        mMonkeyName?.text = monkeyModel?.name
        mMokeyId?.text = monkeyModel?.id.toString()
        mShowInformationBtn?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                bus.post(ShowInformationEvent(monkeyModel))
            }
        })

        mFeedBtn?.isClickable = feedClick
        mFeedBtn?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                bus.post(PostFeedEvent(monkeyModel))
            }
        })

    }
}