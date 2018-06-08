package com.mwhive.lastfmcharts.screens.album;


import com.jakewharton.rxrelay2.BehaviorRelay;
import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.di.ScreenScope;
import com.mwhive.lastfmcharts.models.album.AlbumDetails;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by MadWasp79 on 04-Jun-18.
 */
@ScreenScope
public class AlbumScreenViewModel {

  private final BehaviorRelay<AlbumDetails> AlbumDetailsRelay = BehaviorRelay.create();
  private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
  private final BehaviorRelay<Boolean> loadingRelay= BehaviorRelay.create();
  private final BehaviorRelay<List<String>> labelsRelay = BehaviorRelay.create();

  @Inject
  AlbumScreenViewModel() {}


  Observable<AlbumDetails> albumDeatils() {
    return AlbumDetailsRelay;
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


  Consumer<AlbumDetails> albumDetailsUpdated() {
    return AlbumDetailsRelay;
  }

  Consumer<List<String>> labelsUpdated() {
    return labelsRelay;
  }

  Consumer<Throwable> onError() {
    return throwable -> {
      Timber.e(throwable, "Error loading Album info");
      errorRelay.accept(R.string.error_loading_album_info);
    };
  }

  Consumer<Boolean> loadingUpdated() {
    return loadingRelay;
  }


}
