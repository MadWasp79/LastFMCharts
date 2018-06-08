package com.mwhive.lastfmcharts.screens.artist;


import android.annotation.SuppressLint;
import com.mwhive.lastfmcharts.data.ChartRequester;
import com.mwhive.lastfmcharts.di.ScreenScope;
import com.mwhive.lastfmcharts.models.artist.Album;
import com.mwhive.lastfmcharts.ui.ScreenNavigator;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by MadWasp79 on 04-Jun-18.
 */
@ScreenScope
public class ArtistScreenPresenter implements AlbumsListAdapter.AlbumClickListener {

  private final ArtistScreenViewModel viewModel;
  private final ChartRequester requester;
  private final ScreenNavigator navigator;
  private final List<String> labelsList = new ArrayList<>();


  @Inject
  public ArtistScreenPresenter(
      ArtistScreenViewModel viewModel,
      ChartRequester requester,
      ScreenNavigator navigator,//switch to repositary
      @Named("artist_name") String artistName,
      @Named("artist_playcount") String artistPlayCount,
      @Named("artist_img_url") String imgUrl) {
    this.viewModel = viewModel;
    this.requester = requester;
    this.navigator = navigator;
    labelsList.add(artistName);
    labelsList.add(artistPlayCount);
    labelsList.add(imgUrl);
    loadTopAlbums(artistName);
  }

  @SuppressLint("CheckResult")
  private void loadTopAlbums(String artistName) {
    requester.getAlbums(artistName)
        .doOnSubscribe(__ -> {
          viewModel.loadingUpdated().accept(true);
          viewModel.labelsUpdated().accept(labelsList);
        })
        .doOnEvent((data, throwable) -> viewModel.loadingUpdated().accept(false))
        .subscribe(viewModel.albumsUpdated(), viewModel.onError());
  }

  @Override
  public void onAlbumClicked(Album album) {
    navigator.goToAlbumDetails(labelsList.get(0), album.albumName());

  }
}
