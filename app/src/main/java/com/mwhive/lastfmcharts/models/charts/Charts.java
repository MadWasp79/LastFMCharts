package com.mwhive.lastfmcharts.models.charts;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;
import com.squareup.moshi.Json;


@AutoValue
public abstract class Charts {

  @Json(name = "topartists")
  public abstract TopArtists topArtists();

  public static JsonAdapter<Charts> jsonAdapter(Moshi moshi) {
    return new AutoValue_Charts.MoshiJsonAdapter(moshi);
  }

}