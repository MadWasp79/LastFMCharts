package com.mwhive.lastfmcharts.database.chartspercountry;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;
import com.mwhive.lastfmcharts.models.charts.ArtistImage;
import com.squareup.moshi.Json;
import java.util.List;

/**
 * Created by MadWasp79 on 06-Jun-18.
 */
@Entity
public class ArtistsEntity {

  @ColumnInfo(name = "mbid")
  private String mbid;

  @PrimaryKey
  @ColumnInfo(name = "artist_name")
  private String artistName;

  @ColumnInfo(name = "listeners")
  private int listeners;

  @ColumnInfo(name = "artist_url")
  private String artistUrl;

  @ColumnInfo(name = "image_url")
  private String artistImageUrl;

  public ArtistsEntity(String mbid, String artistName, int listeners, String artistUrl, String artistImageUrl) {
    this.mbid = mbid;
    this.artistName = artistName;
    this.listeners = listeners;
    this.artistUrl = artistUrl;
    this.artistImageUrl = artistImageUrl;
  }


  public String getMbid() {
    return mbid;
  }

  public String getArtistName() {
    return artistName;
  }

  public int getListeners() {
    return listeners;
  }

  public String getArtistUrl() {
    return artistUrl;
  }

  public String getArtistImageUrl() {
    return artistImageUrl;
  }

  public void setMbid(String mbid) {
    this.mbid = mbid;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public void setListeners(int listeners) {
    this.listeners = listeners;
  }

  public void setArtistUrl(String artistUrl) {
    this.artistUrl = artistUrl;
  }

  public void setArtistImageUrl(String artistImageUrl) {
    this.artistImageUrl = artistImageUrl;
  }

}
