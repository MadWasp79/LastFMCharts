package com.mwhive.lastfmcharts.models.charts;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class ArtistImage {

  @Json(name = "#text")
  public abstract String imageUrl();
  @Json(name = "size")
  private String imageSize;

  public static JsonAdapter<ArtistImage> jsonAdapter(Moshi moshi) {
    return new AutoValue_ArtistImage.MoshiJsonAdapter(moshi);
  }
}