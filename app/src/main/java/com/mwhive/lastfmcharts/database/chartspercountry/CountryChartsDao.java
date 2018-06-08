package com.mwhive.lastfmcharts.database.chartspercountry;


import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by MadWasp79 on 06-Jun-18.
 */

@Dao
public interface CountryChartsDao {

  @Query("SELECT * FROM ArtistsEntity JOIN ChartsUkraine "
      + "ON ArtistsEntity.artist_name = ChartsUkraine.artist_name "
      + "WHERE ArtistsEntity.artist_name = ChartsUkraine.artist_name "
      + "ORDER BY ChartsUkraine.chart_place")
  Flowable<List<ArtistsEntity>> getArtistForUkraine();

  @Query("SELECT * FROM ArtistsEntity JOIN ChartsIsrael "
      + "ON ArtistsEntity.artist_name = ChartsIsrael.artist_name "
      + "WHERE ArtistsEntity.artist_name = ChartsIsrael.artist_name "
      + "ORDER BY ChartsIsrael.chart_place")
  Flowable<List<ArtistsEntity>> getArtistForIsrael();

  @Query("SELECT * FROM ArtistsEntity JOIN ChartsGB "
      + "ON ArtistsEntity.artist_name = ChartsGB.artist_name "
      + "WHERE ArtistsEntity.artist_name = ChartsGB.artist_name "
      + "ORDER BY ChartsGB.chart_place")
  Flowable<List<ArtistsEntity>> getArtistForGB();

  @Insert(onConflict = IGNORE)
  void insertArtists(List<ArtistsEntity> artistsEntityList);

  @Insert(onConflict = REPLACE)
  void insertUkrainChart(List<ChartsUkraine> chartsUkraineList);

  @Insert(onConflict = REPLACE)
  void insertIsraelChart(List<ChartsUkraine> chartsUkraineList);

  @Insert(onConflict = REPLACE)
  void insertGBChart(List<ChartsUkraine> chartsUkraineList);



}
