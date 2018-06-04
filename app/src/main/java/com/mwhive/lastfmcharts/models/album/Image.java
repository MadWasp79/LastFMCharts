
package com.mwhive.lastfmcharts.models.album;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Image {

  @Json(name = "#text")
  public abstract String albumImageUrl();
  @Json(name = "size")
  public abstract String size();

  public static JsonAdapter<Image> jsonAdapter(Moshi moshi) {
    return new AutoValue_Image.MoshiJsonAdapter(moshi);
  }
}