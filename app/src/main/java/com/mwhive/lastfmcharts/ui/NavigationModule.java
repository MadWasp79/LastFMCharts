package com.mwhive.lastfmcharts.ui;


import dagger.Binds;
import dagger.Module;


/**
 * Created by MadWasp79 on 02-Jun-18.
 * Module to provide ScreenNavigator to dependency graph
 */
@Module
public abstract class NavigationModule {

  @Binds
  abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);


}
