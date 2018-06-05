package com.mwhive.lastfmcharts.screens.charts;


import android.support.v7.util.DiffUtil;
import com.mwhive.lastfmcharts.models.charts.Artist;
import java.util.List;

/**
 * Created by MadWasp79 on 04-Jun-18.
 */

public class ChartDiffCallback extends DiffUtil.Callback {

  private final List<Artist> oldList;
  private final List<Artist> newList;

  public ChartDiffCallback(List<Artist> oldList, List<Artist> newList) {
    this.oldList = oldList;
    this.newList = newList;
  }


  @Override
  public int getOldListSize() {
    return oldList.size();
  }

  @Override
  public int getNewListSize() {
    return newList.size();
  }

  @Override
  public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return oldList.get(oldItemPosition).artistName().equals(newList.get(newItemPosition).artistName());
  }

  @Override
  public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
  }

}
