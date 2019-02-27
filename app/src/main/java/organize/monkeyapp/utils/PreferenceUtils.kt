package organize.monkeyapp.utils

import android.content.Context

/**
 * Created by organize on 12/12/2017.
 */
class PreferenceUtils {

    companion object {

        fun saveString(context: Context, key: String, yourString: String) {
            val sharedPref = context.getSharedPreferences(AppUtils.PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(key, yourString)
            editor.commit()
        }

        fun getString(context: Context, Key: String): String {
            val sharedPref = context.getSharedPreferences(AppUtils.PREF_NAME, Context.MODE_PRIVATE)
            val defaultValue = ""
            return sharedPref.getString(Key, defaultValue)
        }

        fun clearShared(context: Context) {
            val preferences = context.getSharedPreferences(AppUtils.PREF_NAME, 0)
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
        }

        fun removeShare(context: Context, key: String) {
            val preferences = context.getSharedPreferences(AppUtils.PREF_NAME, 0)
            preferences.edit().remove(key).apply()
        }
    }

}