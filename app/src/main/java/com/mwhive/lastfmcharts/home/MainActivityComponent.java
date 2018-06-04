package com.mwhive.lastfmcharts.home;


import com.mwhive.lastfmcharts.di.ActivityScope;
import com.mwhive.lastfmcharts.ui.DefaultScreenNavigator;
import com.mwhive.lastfmcharts.ui.NavigationModule;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */
@ActivityScope
@Subcomponent(modules = {
    MainScreenBindingModule.class,
    NavigationModule.class,

})
public interface MainActivityComponent extends AndroidInjector<MainActivity>{

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<MainActivity>{

    //prevents injection of MainActivity into screens and memory leaks
    @Override
    public void seedInstance(MainActivity instance) {

    }
  }

}
