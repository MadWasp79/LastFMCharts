package com.mwhive.lastfmcharts.data;


import com.mwhive.lastfmcharts.models.charts.Artist;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by MadWasp79 on 03-Jun-18.
 * Used by Presenters to fetch data
 */

public class ChartRequester {

  private final ChartService service;

  private final String country = "ukraine";


  @Inject
  ChartRequester(ChartService service) {
    this.service = service;
  }

  public Single<List<Artist>> getCharts(String country) {
    return service.getCharts(country)
        .map(charts -> charts.topArtists().artist())
        .subscribeOn(Schedulers.io());
  }

}
