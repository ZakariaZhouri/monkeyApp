package organize.monkeyapp.ui.recycler.bananaRecycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import organize.monkeyapp.R

/**
 * Created by organize on 12/12/2017.
 */
class BananaViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var mBananaImage: ImageView

    init {
        mBananaImage = itemView?.findViewById(R.id.banana_image)!!
    }
}