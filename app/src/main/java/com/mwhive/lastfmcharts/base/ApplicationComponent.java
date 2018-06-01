package com.mwhive.lastfmcharts.base;


import android.app.Application;
import dagger.Component;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */

@Singleton
@Component(modules = {
    ApplicationModule.class,
    ActivityBindingModule.class,
})
public interface ApplicationComponent {

  void inject(MyApplication myApplication);

}
