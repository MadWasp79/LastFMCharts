
package com.mwhive.lastfmcharts.models.album;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;
import com.squareup.moshi.Json;

@AutoValue
public abstract class Tracks {

  @Json(name = "track")
  public abstract List<Track> trackList();

  public static JsonAdapter<Tracks> jsonAdapter(Moshi moshi) {
    return new AutoValue_Tracks.MoshiJsonAdapter(moshi);
  }

}