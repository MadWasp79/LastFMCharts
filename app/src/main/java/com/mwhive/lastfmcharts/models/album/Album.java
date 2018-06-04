package com.mwhive.lastfmcharts.models.album;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;
import com.squareup.moshi.Json;

@AutoValue
public abstract class Album {

  @Json(name = "name")
  public abstract String name();

  @Json(name = "artist")
  public abstract String artist();

  @Json(name = "url")
  public abstract String url();

  @Json(name = "image")
  public abstract List<Image> image();

  @Json(name = "listeners")
  public abstract String listeners();

  @Json(name = "playcount")
  public abstract String playCount();

  @Json(name = "tracks")
  public abstract List<Track> tracks();

  @Json(name = "wiki")
  public abstract Wiki wiki();

  public static JsonAdapter<Album> jsonAdapter(Moshi moshi) {
    return new AutoValue_Album.MoshiJsonAdapter(moshi);
  }
}