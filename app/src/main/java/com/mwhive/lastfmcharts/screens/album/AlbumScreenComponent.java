package com.mwhive.lastfmcharts.screens.album;


import com.mwhive.lastfmcharts.di.ScreenScope;
import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import javax.inject.Named;

/**
 * Created by MadWasp79 on 04-Jun-18.
 */
@ScreenScope
@Subcomponent
public interface AlbumScreenComponent extends AndroidInjector<AlbumScreenController>{

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<AlbumScreenController> {

    @BindsInstance
    public abstract void bindArtistAName(@Named("artist_album_name")String artistName);

    @BindsInstance
    public abstract void bindAlbumName(@Named("album_title") String albumTitle);

    @Override
    public void seedInstance(AlbumScreenController instance) {
      bindAlbumName(instance.getArgs().getString(AlbumScreenController.ALBUM_TITLE));
      bindArtistAName(instance.getArgs().getString(AlbumScreenController.ARTIST_NAME));
    }
  }


}

