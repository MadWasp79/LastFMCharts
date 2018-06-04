package com.mwhive.lastfmcharts.models;


import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/**
 * Created by MadWasp79 on 02-Jun-18.
 */

@MoshiAdapterFactory
public abstract class AdapterFactory implements JsonAdapter.Factory {

  public static JsonAdapter.Factory create() {
    return new AutoValueMoshi_AdapterFactory();
  }

}
