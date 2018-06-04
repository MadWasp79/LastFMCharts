package com.mwhive.lastfmcharts.data;


import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;

/**
 * Created by MadWasp79 on 03-Jun-18.
 */

@Module
public abstract class ChartServiceModule {

  @Provides
  @Singleton
  static ChartService provideChartService(Retrofit retrofit) {
    return retrofit.create(ChartService.class);
  }

}
