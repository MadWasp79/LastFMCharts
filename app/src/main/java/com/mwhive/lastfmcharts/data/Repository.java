package com.mwhive.lastfmcharts.data;


import android.annotation.SuppressLint;
import com.mwhive.lastfmcharts.models.charts.Artist;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Created by MadWasp79 on 05-Jun-18. todo: description after db implementation
 */
@Singleton
public class Repository {

  private final Provider<ChartRequester> chartRequesterProvider;

  private final List<Artist> artistList = new ArrayList<>();


  @Inject
  public Repository(Provider<ChartRequester> chartRequesterProvider) {
    this.chartRequesterProvider = chartRequesterProvider;
  }

  public Single<List<Artist>> getCharts(String country) {
    return Maybe.concat(apiListOfArtists(country), dbListOfArtists(country))
        .firstOrError();
  }



  @SuppressLint("CheckResult")
  private Maybe<List<Artist>> apiListOfArtists(String country) {
    return chartRequesterProvider.get().getCharts(country)
        .doOnSuccess(artist -> {
          artistList.clear();
          artistList.addAll(artist);
          saveToDb(artistList, country);
        }).toMaybe();
  }

  //todo implement database
  private Maybe<List<Artist>> dbListOfArtists(String country) {
    return null;
  }

  private void saveToDb(List<Artist> artistList, String country) {
    //todo implement database
  }


}
