package organize.monkeyapp.ui.recycler.bananaRecycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import organize.monkeyapp.R

/**
 * Created by organize on 12/12/2017.
 */
class BananaRecyclerAdapter : RecyclerView.Adapter<BananaViewHolder>() {

    var NUMBER_BANANE = 3;

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BananaViewHolder {
        val view = LayoutInflater.from(parent?.getContext()).inflate(R.layout.recycler_banana, parent, false)
        return BananaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BananaViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int {
        return NUMBER_BANANE;
    }

    fun setNumberBanana() {
        NUMBER_BANANE -= 1
    }

    fun setNumberBanana(value: Int?) {
        NUMBER_BANANE -= value!!
    }


}