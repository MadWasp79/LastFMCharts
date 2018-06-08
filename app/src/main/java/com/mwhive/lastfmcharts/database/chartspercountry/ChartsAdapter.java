package com.mwhive.lastfmcharts.database.chartspercountry;


import com.mwhive.lastfmcharts.models.charts.Artist;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MadWasp79 on 06-Jun-18.
 */

public class ChartsAdapter {

  private List<Artist> chartsFromApi;
  private List<Artist> chartsFromDb;
  private List<ChartsGB> gBChart = new ArrayList<>();
  private List<ChartsIsrael> israelChart = new ArrayList<>();
  private List<ChartsUkraine> ukrChart = new ArrayList<>();
  private List<ArtistsEntity> artistsEntities = new ArrayList<>();

  public ChartsAdapter(List<Artist> chartsFromApi) {
    this.chartsFromApi = chartsFromApi;
  }

  public List<ArtistsEntity> getArtistsEntities() {
    return artistsEntities;
  }

  public List<ChartsGB> getgBChart() {
    return gBChart;
  }

  public List<ChartsIsrael> getIsraelChart() {
    return israelChart;
  }

  public List<ChartsUkraine> getUkrChart() {
    return ukrChart;
  }

  public List<Artist> getChartsFromDb() {
    return chartsFromDb;
  }

  public void setgBChart(List<ChartsGB> gBChart) {
    this.gBChart = gBChart;
  }

  public void setIsraelChart(List<ChartsIsrael> israelChart) {
    this.israelChart = israelChart;
  }

  public void setUkrChart(List<ChartsUkraine> ukrChart) {
    this.ukrChart = ukrChart;
  }

  public void setArtistsEntities(
      List<ArtistsEntity> artistsEntities) {
    this.artistsEntities = artistsEntities;
  }

  public void apiToDb(String _country) {
    String country = _country;
    int idx = 0;
    for (Artist artist : chartsFromApi) {
      ++idx;
      ArtistsEntity entity = new ArtistsEntity(
          artist.mbid(),
          artist.artistName(),
          artist.listeners(),
          artist.artistUrl(),
          artist.artistImages().get(3).imageUrl());
      switch (country) {
        case "ukraine": {
          ChartsUkraine chart = new ChartsUkraine(artist.artistName(), idx);
          ukrChart.add(chart);
          break;
        }
        case "israel": {
          ChartsIsrael chart = new ChartsIsrael(artist.artistName(), idx);
          israelChart.add(chart);
          break;
        }
        default: {
          ChartsGB chart = new ChartsGB(artist.artistName(), idx);
          gBChart.add(chart);
          break;
        }
      }

    artistsEntities.add(entity);
  }




}


}
