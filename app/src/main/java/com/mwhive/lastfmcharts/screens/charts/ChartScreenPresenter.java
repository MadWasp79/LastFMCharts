package com.mwhive.lastfmcharts.screens.charts;


import android.annotation.SuppressLint;
import com.mwhive.lastfmcharts.data.ChartRequester;
import com.mwhive.lastfmcharts.di.ScreenScope;
import com.mwhive.lastfmcharts.models.charts.Artist;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by MadWasp79 on 03-Jun-18.
 */
@ScreenScope
class ChartScreenPresenter implements ArtistsListAdapter.ArtistClickListener {

  private final ChartScreenViewModel viewModel;
  private final ChartRequester chartRequester;

  private String country = "ukraine";
  private int countryCode = 1;


  @Inject
  ChartScreenPresenter(ChartScreenViewModel viewModel, ChartRequester chartRequester) {
    this.viewModel = viewModel;
    this.chartRequester = chartRequester;
    loadCharts(country);
    switchCountry();
  }


  @SuppressLint("CheckResult")
  private void loadCharts(String country) {

    chartRequester.getCharts(country)
        .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
        .doOnEvent((data, throwable) -> viewModel.loadingUpdated().accept(false))
        .subscribe(viewModel.artistsUpdated(), viewModel.onError());
  }

  @SuppressLint("CheckResult")
  private void switchCountry() {
    viewModel.country().subscribe(val -> {
      switch(val){
        case 1:
          countryCode = 1;
          country = "ukraine";
          Timber.d("FAB clicked: Ukraine");
          loadCharts(country);
          break;
        case 2:
          countryCode = 2;
          country = "israel";
          Timber.d("FAB clicked: Israel");
          loadCharts(country);
          break;
        case 3:
          countryCode = 3;
          country = "united kingdom";
          Timber.d("FAB clicked: GB");
          loadCharts(country);
          break;
      }
        viewModel.countrySelectorUpdated().accept(countryCode);
    });

    try {
      viewModel.countrySelectorUpdated().accept(countryCode);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  @Override
  public void onArtistClicked(Artist artist) {
    Timber.d("artist clicked: " + artist.artistName());
  }
}
