package com.mwhive.lastfmcharts.home;


import com.mwhive.lastfmcharts.di.ActivityScope;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */
@ActivityScope
@Subcomponent(modules = {
    MainScreenBindingModule.class,

})
public interface MainActivityComponent extends AndroidInjector<MainActivity>{

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<MainActivity>{

  }

}
