package com.mwhive.lastfmcharts.screens.album;


import android.annotation.SuppressLint;
import com.mwhive.lastfmcharts.data.ChartRequester;
import com.mwhive.lastfmcharts.di.ScreenScope;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by MadWasp79 on 04-Jun-18.
 */

@ScreenScope
public class AlbumScreenPresenter {

  private AlbumScreenViewModel viewModel;
  private ChartRequester requester;
  private List<String> labels;

  @Inject
  public AlbumScreenPresenter(
      AlbumScreenViewModel viewModel,
      ChartRequester requester,
      @Named("album_title") String albumTitle,
      @Named("artist_album_name")String artistName) {
    this.viewModel = viewModel;
    this.requester = requester;
    this.labels = labels;

    loadAlbums(artistName, albumTitle);
  }

  @SuppressLint("CheckResult")
  private void loadAlbums(String artistName, String albumName) {
    requester.getAlbumInfo(artistName, albumName)
        .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
        .doOnEvent((data, throwable) -> viewModel.loadingUpdated().accept(false))
        .subscribe(viewModel.albumDetailsUpdated(), viewModel.onError());
  }
}
