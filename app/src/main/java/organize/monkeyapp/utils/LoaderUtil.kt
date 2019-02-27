package organize.monkeyapp.utils

import android.app.Activity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import organize.monkeyapp.R

/**
 * Created by organize on 12/12/2017.
 */
class LoaderUtil {

    val activity: Activity
    var view: View? = null
    var progressBar: ProgressBar? = null
    var loadingTxt: TextView? = null
    var errorTxt: TextView? = null

    constructor(activity: Activity) {
        this.activity = activity
        createUi()
    }


    private fun createUi() {
        progressBar = activity.findViewById(R.id.progress)
        view = activity.findViewById(R.id.view_loader)
        loadingTxt = activity.findViewById(R.id.loading_txt)
        errorTxt = activity.findViewById(R.id.error_txt)
        view?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                hideLoader()
            }

        })
    }

    fun showLoader() {
        progressBar!!.visibility = View.VISIBLE
        view!!.visibility = View.VISIBLE
        loadingTxt!!.visibility = View.VISIBLE
    }

    fun hideLoader() {
        progressBar!!.visibility = View.GONE
        view!!.visibility = View.GONE
        loadingTxt!!.visibility = View.GONE
    }

    fun showError() {
        progressBar!!.visibility = View.GONE
        view!!.visibility = View.VISIBLE
        loadingTxt!!.visibility = View.GONE
        errorTxt!!.visibility = View.VISIBLE
    }

}