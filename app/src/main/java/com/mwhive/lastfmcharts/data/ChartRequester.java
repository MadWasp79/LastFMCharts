package com.mwhive.lastfmcharts.data;


import com.mwhive.lastfmcharts.models.album.AlbumDetails;
import com.mwhive.lastfmcharts.models.artist.Album;
import com.mwhive.lastfmcharts.models.artist.TopAlbums;
import com.mwhive.lastfmcharts.models.charts.Artist;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        .map(artists -> {
        Collections.sort(artists, (a1, a2) -> a1.artistName().compareTo(a2.artistName()));
        return artists;})
        .subscribeOn(Schedulers.io());
  }

  public Single<List<Album>> getAlbums(String artistName) {
    return service.getTopAlbums(artistName)
        .map(albumsResult -> albumsResult.topalbum().albums())
        .subscribeOn(Schedulers.io());
  }

  public Single<AlbumDetails> getAlbumInfo(String artistName, String albumName){
    return service.getAlbumInfo(artistName, albumName)
        .subscribeOn(Schedulers.io());
  }


}
