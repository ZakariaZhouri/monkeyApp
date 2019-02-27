package organize.monkeyapp.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import organize.monkeyapp.R

class MonkeyInformationActivity : AppCompatActivity() {
    companion object {
        var MONKEY_ID: String? = "MONKEY_ID"
        var MONKEY_NAME: String? = "MONKEY_NAME"
        var MONKEY_BANANA: String? = "MONKEY_BANANA"
    }

    lateinit var mMonkeyIdTxt: TextView
    lateinit var mMonkeyNameTxt: TextView
    lateinit var mMonkeyNbrBananaTxt: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_monkey)
        mMonkeyIdTxt = findViewById(R.id.monkey_id_information_txt) as TextView
        mMonkeyNameTxt = findViewById(R.id.monkey_name_information_txt) as TextView
        mMonkeyNbrBananaTxt = findViewById(R.id.monkey_nbr_banana_information_txt) as TextView
        bindMonkeyInformation()
    }


    fun bindMonkeyInformation() {
        val monkeyId = intent.getIntExtra(MONKEY_ID, 1)
        val monkeyName = intent.getStringExtra(MONKEY_NAME)
        val monkeyBanana = intent.getIntExtra(MONKEY_BANANA, 0)
        mMonkeyIdTxt.text = "" + monkeyId
        mMonkeyNameTxt.text = monkeyName
        mMonkeyNbrBananaTxt.text = "" + monkeyBanana
    }
}
