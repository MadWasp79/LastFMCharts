package com.mwhive.lastfmcharts.testutils;


/**
 * Created by MadWasp79 on 04-Jun-18.
 */

import com.mwhive.lastfmcharts.models.AdapterFactory;
import com.squareup.moshi.Moshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class TestUtils {

  private static TestUtils INSTANCE = new TestUtils();

  private static final Moshi TEST_MOSHI = initializeMoshi();


  private TestUtils(){}

  public static <T> T loadJson (String path, Type type) {
    try {
      String json = getFileString(path);
      //noinspection unchecked
      return (T) TEST_MOSHI.adapter(type).fromJson(json);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not deserialize: " + path + " into type: " + type);
    }
  }

  public static <T> T loadJson(String path, Class <T> clazz) {
    try {
      String json = getFileString(path);
      //noinspection unchecked
      return (T) TEST_MOSHI.adapter(clazz).fromJson(json);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not deserialize: " + path + " into type: " + clazz);
    }
  }

  private static String getFileString(String path) {
    try {
      StringBuilder sb = new StringBuilder();
      BufferedReader mReader = new BufferedReader(new InputStreamReader(
          INSTANCE.getClass().getClassLoader().getResourceAsStream(path)));
      String line;
      while ((line = mReader.readLine()) != null) {
        sb.append(line);
      }
      return sb.toString();
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not read from resource at: " + path);
    }
  }

  private static Moshi initializeMoshi() {
    Moshi.Builder mBuilder = new Moshi.Builder();
    mBuilder.add(AdapterFactory.create());
    return mBuilder.build();
  }

}