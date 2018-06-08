package com.mwhive.lastfmcharts.models.artist;


import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by MadWasp79 on 07-Jun-18.
 */
@AutoValue
public abstract class AlbumsResult {

  @Json(name = "topalbums")
  public abstract TopAlbums topalbum();

  public static JsonAdapter<AlbumsResult> jsonAdapter(Moshi moshi) {
    return new AutoValue_AlbumsResult.MoshiJsonAdapter(moshi);
  }
}
