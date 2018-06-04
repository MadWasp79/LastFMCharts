package com.mwhive.lastfmcharts.models.album;

import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Wiki {

  @Nullable
  @Json(name = "published")
  public abstract String published();

  @Nullable
  @Json(name = "summary")
  public abstract String summary();

  @Nullable
  @Json(name = "content")
  public abstract String content();


  public static JsonAdapter<Wiki> jsonAdapter(Moshi moshi) {
    return new AutoValue_Wiki.MoshiJsonAdapter(moshi);
  }
}