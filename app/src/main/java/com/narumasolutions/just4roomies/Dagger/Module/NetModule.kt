package com.narumasolutions.just4roomies.Dagger.Module

import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Utils.BASE_URL
import com.narumasolutions.just4roomies.Utils.PASS
import com.narumasolutions.just4roomies.Utils.USER
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object NetModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApi(retrofit: Retrofit): Just4RoomiesServices {
        return retrofit.create(Just4RoomiesServices::class.java)
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {

        val clientBuilder = OkHttpClient.Builder()

        val auth = Credentials.basic(USER, PASS)

        val headerInterceptor = Interceptor { chain ->
            var request = chain.request()
            val headers = request.headers().newBuilder().add("Authorization", auth).add("Content-Type","application/json").build()
            request = request.newBuilder().headers(headers).build()
            chain.proceed(request)
        }

        clientBuilder.addInterceptor(headerInterceptor)


        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(clientBuilder.build())
                .build()
    }


}