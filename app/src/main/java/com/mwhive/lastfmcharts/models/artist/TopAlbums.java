package com.mwhive.lastfmcharts.models.artist;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;
import com.squareup.moshi.Json;

@AutoValue
public abstract class TopAlbums {

  @Json(name = "album")
  public abstract List<Album> albums();

  public static JsonAdapter<TopAlbums> jsonAdapter(Moshi moshi) {
    return new AutoValue_TopAlbums.MoshiJsonAdapter(moshi);
  }
}