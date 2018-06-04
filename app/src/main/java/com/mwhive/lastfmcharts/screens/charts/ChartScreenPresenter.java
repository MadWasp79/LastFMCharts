package com.mwhive.lastfmcharts.screens.charts;


import android.annotation.SuppressLint;
import com.mwhive.lastfmcharts.data.ChartRequester;
import com.mwhive.lastfmcharts.di.ScreenScope;
import com.mwhive.lastfmcharts.models.charts.Artist;
import javax.inject.Inject;

/**
 * Created by MadWasp79 on 03-Jun-18.
 */
@ScreenScope
public class ChartScreenPresenter implements ArtistsListAdapter.ArtistClickListener{

  private final ChartsScreenViewModel viewModel;
  private final ChartRequester chartRequester;

  @Inject
  ChartScreenPresenter(ChartsScreenViewModel viewModel, ChartRequester chartRequester) {
    this.viewModel = viewModel;
    this.chartRequester = chartRequester;
    loadCharts();
  }



  @SuppressLint("CheckResult")
  private void loadCharts() {
    String country = "ukraine";
    chartRequester.getCharts(country)
        .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
        .doOnEvent((data, throwable) -> viewModel.loadingUpdated().accept(false))
        .subscribe(viewModel.artistsChartUpdated(), viewModel.onError());
  }

  @Override
  public void onArtistClicked(Artist artist) {

  }
}
