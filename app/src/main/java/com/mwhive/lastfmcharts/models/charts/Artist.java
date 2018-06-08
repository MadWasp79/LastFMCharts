package com.mwhive.lastfmcharts.models.charts;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;
import com.squareup.moshi.Json;

@AutoValue
public abstract class Artist {
  @Json(name = "mbid")
  public abstract String mbid();

  @Json(name = "name")
  public abstract String artistName();

  @Json(name = "listeners")
  public abstract int listeners();

  @Json(name = "url")
  public abstract String artistUrl();

  @Json(name = "image")
  public abstract List<ArtistImage> artistImages();

  public static JsonAdapter<Artist> jsonAdapter(Moshi moshi) {
    return new AutoValue_Artist.MoshiJsonAdapter(moshi);
  }

}