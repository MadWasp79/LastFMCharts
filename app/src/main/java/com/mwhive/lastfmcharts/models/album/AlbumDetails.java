package com.mwhive.lastfmcharts.models.album;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;
import com.squareup.moshi.Json;

@AutoValue
public abstract class AlbumDetails {

  @Json(name = "album")
  public abstract Album album();

  public static JsonAdapter<AlbumDetails> jsonAdapter(Moshi moshi) {
    return new AutoValue_AlbumDetails.MoshiJsonAdapter(moshi);
  }
}