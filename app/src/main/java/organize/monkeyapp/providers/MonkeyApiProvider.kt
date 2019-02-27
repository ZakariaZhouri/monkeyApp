package organize.monkeyapp.providers

import dagger.Module
import dagger.Provides
import organize.monkeyapp.network.api.MonkeyApi
import organize.monkeyapp.utils.AppUtils
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by organize on 10/12/2017.
 */
@Module
class MonkeyApiProvider {
    @Singleton
    @Provides
    fun getApi(): MonkeyApi {
        return Retrofit.Builder().baseUrl(AppUtils.URL_MONKEY)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MonkeyApi::class.java)
    }
}