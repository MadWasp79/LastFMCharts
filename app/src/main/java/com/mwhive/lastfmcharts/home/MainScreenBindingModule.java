package com.mwhive.lastfmcharts.home;


import com.bluelinelabs.conductor.Controller;
import com.mwhive.lastfmcharts.di.ControllerKey;
import com.mwhive.lastfmcharts.screens.album.AlbumScreenComponent;
import com.mwhive.lastfmcharts.screens.album.AlbumScreenController;
import com.mwhive.lastfmcharts.screens.artist.ArtistScreenComponent;
import com.mwhive.lastfmcharts.screens.artist.ArtistScreenController;
import com.mwhive.lastfmcharts.screens.charts.ChartScreenComponent;
import com.mwhive.lastfmcharts.screens.charts.ChartScreenController;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */
@Module(subcomponents = {
    ChartScreenComponent.class,
    ArtistScreenComponent.class,
    AlbumScreenComponent.class
})
public abstract class MainScreenBindingModule {

  @Binds
  @IntoMap
  @ControllerKey(ChartScreenController.class)
  abstract AndroidInjector.Factory<? extends Controller> bindChartScreenInjector(ChartScreenComponent.Builder builder);

  @Binds
  @IntoMap
  @ControllerKey(ArtistScreenController.class)
  abstract AndroidInjector.Factory<? extends Controller> bindArtistScreenInjector(ArtistScreenComponent.Builder builder);

  @Binds
  @IntoMap
  @ControllerKey(AlbumScreenController.class)
  abstract AndroidInjector.Factory<? extends Controller> bindsAlbumScreenInjector(AlbumScreenComponent.Builder builder);

}
