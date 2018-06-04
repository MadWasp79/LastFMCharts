package com.mwhive.lastfmcharts.networking;


import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by MadWasp79 on 03-Jun-18.
 */

public class NetworkModule {

  @Provides
  @Singleton
  static Call.Factory provideOkHttp() {
    return new OkHttpClient.Builder().build();
  }

  @Provides
  @Named("base_url")
  static String provideBaseUrl() {
    return "http://ws.audioscrobbler.com/2.0/";
  }

}
