package organize.monkeyapp.ui.activity

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import organize.monkeyapp.MonkeyApp
import organize.monkeyapp.R
import organize.monkeyapp.event.PostFeedEvent
import organize.monkeyapp.event.ShowInformationEvent
import organize.monkeyapp.listeners.MonkeyGetListener
import organize.monkeyapp.network.models.MonkeyModel
import organize.monkeyapp.presenter.MonkeyPresenter
import organize.monkeyapp.ui.recycler.bananaRecycler.BananaRecyclerAdapter
import organize.monkeyapp.ui.recycler.monkeyRecycler.MonkeyRecyclerAdapter
import organize.monkeyapp.utils.AppUtils
import organize.monkeyapp.utils.LoaderUtil
import organize.monkeyapp.utils.PreferenceUtils
import javax.inject.Inject

class MonkeyActivity : AppCompatActivity(), MonkeyGetListener {
    /**
     * Constant
     * */
    val BANANA_MAX = 3

    init {
        MonkeyApp.mAppComponent?.inject(this)
    }

    /***
     *View
     */
    lateinit var mMonkeyRecyclerView: RecyclerView
    lateinit var mBananaRecyclerView: RecyclerView
    /**
     * Variables
     * */
    lateinit var mMonkeyAdapter: MonkeyRecyclerAdapter
    lateinit var mBananaAdapter: BananaRecyclerAdapter
    lateinit var mMail: String
    var numberBanana = BANANA_MAX


    @Inject
    lateinit var mMonkeyPresenter: MonkeyPresenter
    @Inject
    lateinit var bus: EventBus

    lateinit var mLoader: LoaderUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monkey)
        mMonkeyRecyclerView = findViewById(R.id.type_recycler) as RecyclerView
        mBananaRecyclerView = findViewById(R.id.banana_recycler) as RecyclerView
        mMonkeyPresenter.mMonkeyGetListener = this
        mMail = PreferenceUtils.getString(this, AppUtils.MAIL)
        mMonkeyPresenter.getFeed(mMail)
        mLoader = LoaderUtil(this)
        mLoader.showLoader()
    }

    override fun onResume() {
        super.onResume()
        bus.register(this)
    }

    override fun onPause() {
        super.onPause()
        bus.unregister(this)

    }

    /**
     * Util function
     * */
    fun updateRecycler(value: List<MonkeyModel>?) {
        mMonkeyAdapter = MonkeyRecyclerAdapter()
        mMonkeyAdapter.mMonkeyList = value
        mMonkeyRecyclerView.adapter = mMonkeyAdapter
        mMonkeyRecyclerView.layoutManager = LinearLayoutManager(this)
        mMonkeyAdapter.notifyDataSetChanged()
    }

    fun createBananaRecycler(value: ArrayList<Int>?) {
        mBananaAdapter = BananaRecyclerAdapter()
        mBananaRecyclerView.adapter = mBananaAdapter
        mBananaRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        if (value?.size == BANANA_MAX) {
            mBananaRecyclerView.visibility = View.GONE
            mMonkeyAdapter.setClick(false)
            mMonkeyAdapter.notifyDataSetChanged()
            Toast.makeText(this, getString(R.string.banana_message), Toast.LENGTH_SHORT).show()
        } else
            mBananaAdapter.setNumberBanana(value?.size)

    }

    fun updateBananaRecycler() {
        if (numberBanana == 0) {
            mBananaRecyclerView.visibility = View.GONE
            mMonkeyAdapter.setClick(false)
            mMonkeyAdapter.notifyDataSetChanged()
        }
        mBananaAdapter.setNumberBanana()
        mBananaAdapter.notifyDataSetChanged()
    }

    fun postFeed(id: Int) {
        var list = ArrayList<Int>()
        list.add(id)
        mMonkeyPresenter.postFeed(mMail, list)
    }

    /**
     * Listeners
     * */
    override fun showMonkeys(value: List<MonkeyModel>?) {
        updateRecycler(value)
        mLoader.hideLoader()
    }

    override fun showMonkeyInformation(value: MonkeyModel?) {
        var showInformationIntent = Intent(this, MonkeyInformationActivity::class.java)
        showInformationIntent.putExtra(MonkeyInformationActivity.MONKEY_ID, value?.id)
        showInformationIntent.putExtra(MonkeyInformationActivity.MONKEY_NAME, value?.name)
        showInformationIntent.putExtra(MonkeyInformationActivity.MONKEY_BANANA, value?.banana)
        startActivity(showInformationIntent)
    }

    override fun postSucced() {
        numberBanana -= 1
        updateBananaRecycler()
    }

    override fun postSuccedOverdose() {
        Toast.makeText(this, getString(R.string.monkey_ovverdose), Toast.LENGTH_SHORT).show()
    }

    override fun getFeedSucced(value: ArrayList<Int>?) {
        createBananaRecycler(value)
        mMonkeyPresenter.getMonkeys(mMail)
    }

    override fun showError() {
        mLoader.showError()
    }

    /**
     * Event
     * */
    @Subscribe
    fun event(showInformationEvent: ShowInformationEvent) {
        mMonkeyPresenter.getMonkeyInformation(mMail, showInformationEvent.monkeyModel?.id)
    }

    @Subscribe
    fun event(showInformationEvent: PostFeedEvent) {
        postFeed(showInformationEvent.monkeyModel?.id!!)
    }

}
