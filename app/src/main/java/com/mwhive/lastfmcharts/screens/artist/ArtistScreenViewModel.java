package com.mwhive.lastfmcharts.screens.artist;


import android.arch.persistence.room.Insert;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.di.ScreenScope;
import com.mwhive.lastfmcharts.models.artist.Album;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;


/**
 * Created by MadWasp79 on 04-Jun-18.
 */

@ScreenScope
class ArtistScreenViewModel {

  private final BehaviorRelay<List<Album>> albumsListRelay = BehaviorRelay.create();
  private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
  private final BehaviorRelay<Boolean> loadingRelay= BehaviorRelay.create();
  private final BehaviorRelay<List<String>> labelsRelay = BehaviorRelay.create();

  @Inject
  ArtistScreenViewModel() {}




  Observable<List<Album>> albumsList() {
    return albumsListRelay;
  }

  Observable<List<String>> labels() {
    return labelsRelay;
  }

  Observable<Integer> error() {
    return errorRelay;
  }

  Observable<Boolean> loading() {
    return loadingRelay;
  }



  Consumer<List<Album>> albumsUpdated() {
    return albumsListRelay;
  }

  Consumer<List<String>> labelsUpdated() {
    return labelsRelay;
  }

  Consumer<Throwable> onError() {
    return throwable -> {
      Timber.e(throwable, "Error loading Artist Info");
      errorRelay.accept(R.string.error_loading_artist_info);
    };
  }

  Consumer<Boolean> loadingUpdated() {
    return loadingRelay;
  }

}
