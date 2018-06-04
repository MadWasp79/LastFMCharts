package com.mwhive.lastfmcharts.base;

import android.app.Application;
import com.mwhive.lastfmcharts.BuildConfig;
import com.mwhive.lastfmcharts.di.ActivityInjector;
import javax.inject.Inject;
import timber.log.Timber;
import timber.log.Timber.DebugTree;


/**
 * Created by MadWasp79 on 01-Jun-18.
 */

public class MyApplication extends Application {

  @Inject ActivityInjector activityInjector;

  protected ApplicationComponent component;

  @Override
  public void onCreate() {
    super.onCreate();

    //noinspection deprecation
    component = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();

    component.inject(this);

    if(BuildConfig.DEBUG)
      Timber.plant(new DebugTree());
  }

  public ActivityInjector getActivityInjector() {
    return activityInjector;
  }
}
