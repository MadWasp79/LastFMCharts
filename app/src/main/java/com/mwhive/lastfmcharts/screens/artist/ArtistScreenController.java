package com.mwhive.lastfmcharts.screens.artist;


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
import com.mwhive.lastfmcharts.models.artist.Album;
import com.mwhive.lastfmcharts.ui.ScreenNavigator;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by MadWasp79 on 04-Jun-18.
 * Second Screen (Artist' Top Albums)
 */

public class ArtistScreenController extends BaseController {

  private static ScreenNavigator screenNavigator;
  @Inject ArtistScreenPresenter presenter;
  @Inject ArtistScreenViewModel viewModel;

  @BindView(R.id.artist_foto_image_view) ImageView artistLargeImage;
  @BindView(R.id.artist_name) TextView artistName;
  @BindView(R.id.artist_playcount_tv) TextView artistPlaycount;
  @BindView(R.id.albums_list) RecyclerView albumsList;
  @BindView(R.id.loading_indicator) View loadingView;
  @BindView(R.id.tv_error) TextView errorText;
  @BindView(R.id.back_to_charts) FloatingActionButton backButton;

  static final String ARTIST_NAME = "artist_name";
  static final String ARTIST_PLAYCOUNT = "artist_playcount";
  static final String ARTIST_IMG_URL = "artist_img_url";

  public static Controller newInstance(
      String artistName,
      String artistPlayCount,
      String artistImageUrl,
      ScreenNavigator screenNavigator) {
    ArtistScreenController.screenNavigator = screenNavigator;
    Bundle bundle = new Bundle();
    bundle.putString(ARTIST_NAME, artistName);
    bundle.putString(ARTIST_PLAYCOUNT, artistPlayCount);
    bundle.putString(ARTIST_IMG_URL, artistImageUrl);
    return new ArtistScreenController(bundle);
  }


  public ArtistScreenController(Bundle bundle) {
    super(bundle);
  }

  @Override
  protected void onViewBound(View view) {
    albumsList.setLayoutManager(new LinearLayoutManager(view.getContext()));
    albumsList.setAdapter(new AlbumsListAdapter(presenter));
  }

  @Override
  protected Disposable[] subscriptions() {
    return new Disposable[]{
        viewModel.loading()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(loading-> {
              loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
              albumsList.setVisibility(loading ? View.GONE : View.VISIBLE);
              errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
            }),
        viewModel.albumsList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(((AlbumsListAdapter) albumsList.getAdapter())::setData),
        viewModel.error()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(errorRes -> {
          if (errorRes == -1) {
            errorText.setText(null);
            errorText.setVisibility(View.GONE);
          } else {
            errorText.setVisibility(View.VISIBLE);
            albumsList.setVisibility(View.GONE);
            errorText.setText(errorRes);
          }
        }),
        viewModel.labels()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(list -> {
          artistName.setText(list.get(0));
          artistPlaycount.setText(list.get(1));
          Glide.with(artistLargeImage.getContext())
              .load(list.get(2))
              .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(20, 10)))
              .into(artistLargeImage);
        }),
        RxView.clicks(backButton).subscribe(__ -> screenNavigator.pop())
    };
  }

  @Override
  protected int layoutRes() {
    return R.layout.screen_artist;
  }
}
