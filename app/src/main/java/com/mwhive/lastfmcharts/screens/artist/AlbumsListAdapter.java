package com.mwhive.lastfmcharts.screens.artist;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MadWasp79 on 04-Jun-18.
 */

public class AlbumsListAdapter extends RecyclerView.Adapter<AlbumsListAdapter.AlbumViewHolder> {


  @NonNull
  @Override
  public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class AlbumViewHolder extends RecyclerView.ViewHolder{

    public AlbumViewHolder(View itemView) {
      super(itemView);
    }
  }
}
