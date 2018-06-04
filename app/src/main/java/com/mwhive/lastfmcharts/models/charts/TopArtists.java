package com.mwhive.lastfmcharts.models.charts;


import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

/**
 * Created by MadWasp79 on 04-Jun-18.
 */
@AutoValue
public abstract class TopArtists {

  @Json(name = "artist")
  public abstract List<Artist> artist();

  public static JsonAdapter<TopArtists> jsonAdapter(Moshi moshi) {
    return new AutoValue_TopArtists.MoshiJsonAdapter(moshi);
  }

}
