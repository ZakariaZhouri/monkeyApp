package organize.monkeyapp.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by organize on 07/12/2017.
 */
class MonkeyModel {
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("name")
    var name: String = ""
    @SerializedName("banana")
    var banana: Int? = null

}