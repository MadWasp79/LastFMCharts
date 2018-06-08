package com.mwhive.lastfmcharts.ui;


import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

/**
 * Created by MadWasp79 on 02-Jun-18.
 * Interface for navigation logic. I made it as abstract as possible to ease UI testing
 */

public interface ScreenNavigator {

  void initWithRouter(Router router, Controller rootController);

  boolean pop();

  void goToAlbums(String artistName, String artistPlaycount, String artistImageUrl);

  void goToAlbumDetails(String artistName, String albumName);

  void clear();

}
