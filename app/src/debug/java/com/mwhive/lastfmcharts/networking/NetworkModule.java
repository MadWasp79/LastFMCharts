package com.mwhive.lastfmcharts.networking;


import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

/**
 * Created by MadWasp79 on 03-Jun-18.
 */
@Module
public class NetworkModule {

  @Provides
  @Singleton
  static Call.Factory provideOkHttp() {
    OkHttpClient.Builder client = new Builder();
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(Level.BODY);
    return client.addInterceptor(loggingInterceptor).build();
  }

  @Provides
  @Named("base_url")
  static String provideBaseUrl() {
    return "http://ws.audioscrobbler.com/2.0/";
  }

}
