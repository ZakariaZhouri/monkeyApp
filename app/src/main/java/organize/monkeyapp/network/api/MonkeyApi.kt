package organize.monkeyapp.network.api

import io.reactivex.Observable
import organize.monkeyapp.network.models.MonkeyModel
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by organize on 10/12/2017.
 */
interface MonkeyApi {

    @GET("/monkeys")
    fun getMonkeys(@Header("User") mail: String?): Observable<List<MonkeyModel>>

    @GET("/monkeys/{id}")
    fun getMonkeyInformation(@Header("User") mail: String?, @Path("id") customerId: Int?): Observable<MonkeyModel>

    @GET("/feed")
    fun getFeed(@Header("User") mail: String?): Observable<ArrayList<Int>?>

    @POST("/feed")
    fun postFeed(@Header("User") mail: String?, @Body listId: ArrayList<Int>?): Observable<Response<Void>>
}