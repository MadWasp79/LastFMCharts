package com.mwhive.lastfmcharts.models.artist;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class AlbumImage {

  @Json(name = "#text")
  public abstract String imageUrl();
  @Json(name = "size")
  public abstract String size();

   public static JsonAdapter<AlbumImage> jsonAdapter(Moshi moshi) {
    return new AutoValue_AlbumImage.MoshiJsonAdapter(moshi);
  }
}