package com.mwhive.lastfmcharts.base;


import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */
@Module
public class ApplicationModule {

  private final Application application;

  ApplicationModule(Application application) {

    this.application = application;
  }

  @Provides
  Context provideApplicationContext() {
    return application;
  }
}
