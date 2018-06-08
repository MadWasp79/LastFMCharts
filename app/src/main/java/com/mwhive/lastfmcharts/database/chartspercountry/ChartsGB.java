package com.mwhive.lastfmcharts.database.chartspercountry;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by MadWasp79 on 06-Jun-18.
 */

@Entity
public class ChartsGB {

  @PrimaryKey
  @ColumnInfo(name = "artist_name")
  private String artistName;

  @ColumnInfo(name = "chart_place")
  private int chartPlace;

  public ChartsGB(String artistName, int chartPlace) {
    this.artistName = artistName;
    this.chartPlace = chartPlace;
  }

    public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public int getChartPlace() {
    return chartPlace;
  }

  public void setChartPlace(int chartPlace) {
    this.chartPlace = chartPlace;
  }
}