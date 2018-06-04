
package com.mwhive.lastfmcharts.models.album;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Artist {

  @Json(name = "name")
  public abstract String artistName();

  public static JsonAdapter<Artist> jsonAdapter(Moshi moshi) {
    return new AutoValue_Artist.MoshiJsonAdapter(moshi);
  }

}