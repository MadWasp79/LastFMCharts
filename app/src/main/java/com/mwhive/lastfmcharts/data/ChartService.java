package com.mwhive.lastfmcharts.data;


import com.mwhive.lastfmcharts.models.album.Album;
import com.mwhive.lastfmcharts.models.artist.TopAlbums;
import com.mwhive.lastfmcharts.models.charts.Charts;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MadWasp79 on 03-Jun-18.
 */


public interface ChartService {

  //todo: remove API from queries
  String api_key = "e6ad07e029401f90f5c0314dffe370c3";

  //returns object with list of Artists
  @GET("?method=geo.gettopartists&format=json&api_key=e6ad07e029401f90f5c0314dffe370c3&limit=20")
  Single<Charts> getCharts(
      @Query("country") String country);

  //returns object with list of selected Artist's Top Albums
  @GET("?method=artist.gettopalbums&format=json&api_key=e6ad07e029401f90f5c0314dffe370c3&limit=20")
  Single<TopAlbums> getTopAlbums(
      @Query("artist") String artistName);

  //returns selected Album
  @GET("?method=album.getinfo&format=json&api_key=e6ad07e029401f90f5c0314dffe370c3")
  Single<Album> getAlbumInfo(
      @Query("artist") String artistName,
      @Query("album") String album);


}
