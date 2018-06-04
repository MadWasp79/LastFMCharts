package com.mwhive.lastfmcharts.models.artist;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;
import com.squareup.moshi.Json;

@AutoValue
public abstract class Album {

  @Json(name = "name")
  public abstract String albumName();

  @Json(name = "playcount")
  public abstract int playcount();

  @Json(name = "url")
  public abstract String url();

  @Json(name = "image")
  public abstract List<AlbumImage> albumImages();

  public static JsonAdapter<Album> jsonAdapter(Moshi moshi) {
    return new AutoValue_Album.MoshiJsonAdapter(moshi);
  }


}