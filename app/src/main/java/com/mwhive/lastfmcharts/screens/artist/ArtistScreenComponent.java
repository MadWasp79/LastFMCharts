package com.mwhive.lastfmcharts.screens.artist;


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
public interface ArtistScreenComponent extends AndroidInjector<ArtistScreenController>{

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<ArtistScreenController> {

    //make this field (string from our bundle) injectable to any class in the scope
    @BindsInstance
    public abstract void bindArtistName(@Named("artist_name") String artistName);

    @BindsInstance
    public abstract void bindArtistPlaycount(@Named("artist_playcount") String artistPlayCount);

    @BindsInstance
    public abstract void bindArtistImgUrl(@Named("artist_img_url") String imgUrl);

    @Override
    public void seedInstance(ArtistScreenController instance) {
      bindArtistName(instance.getArgs().getString(ArtistScreenController.ARTIST_NAME));
      bindArtistPlaycount(instance.getArgs().getString(ArtistScreenController.ARTIST_PLAYCOUNT));
      bindArtistImgUrl(instance.getArgs().getString(ArtistScreenController.ARTIST_IMG_URL));
    }
  }



}
