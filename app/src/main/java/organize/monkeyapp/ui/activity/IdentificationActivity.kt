package organize.monkeyapp.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import organize.monkeyapp.R
import organize.monkeyapp.utils.AppUtils
import organize.monkeyapp.utils.PreferenceUtils


class IdentificationActivity : AppCompatActivity() {

    /**
     * View
     * */
    lateinit var mMailEditText: EditText
    lateinit var mValidateBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identification)
        mMailEditText = findViewById(R.id.mail_text) as EditText
        mValidateBtn = findViewById(R.id.validate_btn) as Button
    }

    override fun onResume() {
        super.onResume()
        mValidateBtn.setOnClickListener(OnValidateClicked())

    }

    override fun onPause() {
        super.onPause()
        mValidateBtn.setOnClickListener(null)
    }

    /**
     * Utils Functions
     * */

    fun openMonkeyActivity() {
        var showInformationIntent = Intent(this, MonkeyActivity::class.java)
        startActivity(showInformationIntent)
    }

    /**
     * Listener
     * */
    inner class OnValidateClicked : View.OnClickListener {
        override fun onClick(p0: View?) {
            val mail = mMailEditText.text.toString()
            if (mail.isNotEmpty()) {
                PreferenceUtils.saveString(this@IdentificationActivity, AppUtils.MAIL, mail)
                openMonkeyActivity()
            } else {
                Toast.makeText(this@IdentificationActivity, getString(R.string.text_invalid), Toast.LENGTH_SHORT).show()
            }

        }

    }

}
