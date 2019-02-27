package organize.monkeyapp.ui.recycler.monkeyRecycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import organize.monkeyapp.R
import organize.monkeyapp.network.models.MonkeyModel

/**
 * Created by organize on 11/12/2017.
 */
class MonkeyRecyclerAdapter : RecyclerView.Adapter<MonkeyViewHolder>() {

    var mMonkeyList: List<MonkeyModel>? = null
    var feedClick = true

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MonkeyViewHolder {
        val view = LayoutInflater.from(parent?.getContext()).inflate(R.layout.recycler_monkey, parent, false)
        return MonkeyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MonkeyViewHolder?, position: Int) {
        holder?.bindInformation(mMonkeyList?.get(position), feedClick)

    }

    override fun getItemCount(): Int {
        return mMonkeyList?.size!!
    }

    fun setClick(isFeedclick: Boolean) {
        feedClick = isFeedclick
    }

}