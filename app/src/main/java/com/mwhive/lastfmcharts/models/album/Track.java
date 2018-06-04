package com.mwhive.lastfmcharts.models.album;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Track {

  @Json(name = "name")
  public abstract String name();
  @Json(name = "url")
  public abstract  String url();
  @Json(name = "duration")
  public abstract String duration();


  public static JsonAdapter<Track> jsonAdapter(Moshi moshi) {
    return new AutoValue_Track.MoshiJsonAdapter(moshi);
  }
}