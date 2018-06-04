package com.mwhive.lastfmcharts.base;



import com.mwhive.lastfmcharts.data.ChartServiceModule;
import com.mwhive.lastfmcharts.networking.ServiceModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */

@Singleton
@Component(modules = {
    ApplicationModule.class,
    ActivityBindingModule.class,
    ServiceModule.class,
    ChartServiceModule.class
})
public interface ApplicationComponent {

  void inject(MyApplication myApplication);

}
