package com.mwhive.lastfmcharts.screens.charts;


import com.mwhive.lastfmcharts.di.ScreenScope;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */
@ScreenScope
@Subcomponent
public interface ChartScreenComponent extends AndroidInjector<ChartScreenController> {

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<ChartScreenController>{

  }
  
}
