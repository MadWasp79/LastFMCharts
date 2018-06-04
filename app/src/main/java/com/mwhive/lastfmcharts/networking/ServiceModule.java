package com.mwhive.lastfmcharts.networking;



import com.mwhive.lastfmcharts.models.AdapterFactory;
import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;

import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

/**
 * Created by MadWasp79 on 02-Jun-18.
 * Our networking module which provides dependencies of Moshi & Retrofit instances
 */

@Module(includes = NetworkModule.class)
public abstract class ServiceModule {

  @Provides
  @Singleton
  static Moshi provideMoshi() {
    return new Moshi.Builder()
        .add(AdapterFactory.create())
        .build();
  }

  @Provides
  @Singleton
  static Retrofit provideRetrofit(Moshi moshi, Call.Factory callFactory, @Named("base_url") String baseUrl) {
    Timber.d(baseUrl);
    return new Retrofit.Builder()
        .callFactory(callFactory)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .build();
  }

}
