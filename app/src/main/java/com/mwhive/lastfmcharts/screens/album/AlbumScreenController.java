package com.mwhive.lastfmcharts.screens.album;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bluelinelabs.conductor.Controller;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding2.view.RxView;
import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.base.BaseController;
import com.mwhive.lastfmcharts.ui.ScreenNavigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by MadWasp79 on 04-Jun-18.
 */

public class AlbumScreenController extends BaseController {

  private static ScreenNavigator screenNavigator;
  @Inject AlbumScreenPresenter presenter;
  @Inject AlbumScreenViewModel viewModel;

  @BindView(R.id.album_foto_image_view) ImageView albumImageIv;
  @BindView(R.id.album_title) TextView albumTitleTv;
  @BindView(R.id.album_playcount_tv) TextView albumPlayCount;
  @BindView(R.id.artist_name_on_album) TextView artistName;
  @BindView(R.id.song_list) RecyclerView songList;
  @BindView(R.id.loading_indicator) View loadingView;
  @BindView(R.id.tv_error) TextView errorText;
  @BindView(R.id.back_to_artist) FloatingActionButton backButton;


  static final String ALBUM_TITLE = "album_title";
  static final String ARTIST_NAME = "artist_album_name";

  public AlbumScreenController(Bundle bundle) {
    super(bundle);
  }

  public static Controller newInstance(
      String albumTitle,
      String artistName,
      ScreenNavigator screenNavigator) {
    AlbumScreenController.screenNavigator = screenNavigator;
    Bundle bundle = new Bundle();
    bundle.putString(ALBUM_TITLE, albumTitle);
    bundle.putString(ARTIST_NAME, artistName);
    return new AlbumScreenController(bundle);
  }

  @Override
  protected void onViewBound(View view) {
    songList.setLayoutManager(new LinearLayoutManager(view.getContext()));
    songList.setAdapter(new SongsListAdapter());
  }

  @Override
  protected Disposable[] subscriptions() {
    return new Disposable[]{
        viewModel.loading()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(loading -> {
          loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
          songList.setVisibility(loading ? View.GONE : View.VISIBLE);
          errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
        }),
        viewModel.albumDeatils()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(albumDetails -> {
          albumTitleTv.setText(albumDetails.album().name());
          albumPlayCount.setText(albumDetails.album().listeners());
          Glide.with(albumImageIv.getContext())
              .load(albumDetails.album().image().get(3).albumImageUrl())
              .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(20, 10)))
              .into(albumImageIv);
          ((SongsListAdapter) songList.getAdapter()).setData(albumDetails.album().tracks().trackList());
        }),

        viewModel.error()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(errorRes -> {
          if (errorRes == -1) {
            errorText.setText(null);
            errorText.setVisibility(View.GONE);
          } else {
            errorText.setVisibility(View.VISIBLE);
            songList.setVisibility(View.GONE);
            errorText.setText(errorRes);
          }
        }),
        RxView.clicks(backButton).subscribe(__-> screenNavigator.pop())
    };
  }

  @Override
  protected int layoutRes() {
    return R.layout.screen_album;
  }
}
