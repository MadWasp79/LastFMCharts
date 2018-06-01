package com.mwhive.lastfmcharts.base;


import android.app.Activity;
import com.mwhive.lastfmcharts.home.MainActivity;
import com.mwhive.lastfmcharts.home.MainActivityComponent;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by MadWasp79 on 01-Jun-18.
 *
 * Class creates Map of (subcomponent') class literals to class injectors.
 */
@Module(subcomponents = {
    MainActivityComponent.class,
})
public abstract class ActivityBindingModule {

  @Binds
  @IntoMap
  @ActivityKey(MainActivity.class)
  abstract AndroidInjector.Factory<? extends Activity> provideMainActivityIjector(MainActivityComponent.Builder builder);
}
