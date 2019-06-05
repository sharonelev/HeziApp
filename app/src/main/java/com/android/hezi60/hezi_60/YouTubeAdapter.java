package com.android.hezi60.hezi_60;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.hezi60.hezi_60.YouTubeAdapter.videoViewHolder;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import java.util.List;

public class YouTubeAdapter extends Adapter<videoViewHolder> {

  List<YoutubeVid> youtubeVidList;
  Context context;

  public YouTubeAdapter(Context mContext, List<YoutubeVid> mYouTubeVidList){

    youtubeVidList = mYouTubeVidList;
    context = mContext;

  }


  @NonNull
  @Override
  public videoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    int layout = R.layout.video_view;
    LayoutInflater inflater = LayoutInflater.from(context);
    boolean shouldAttachToParentImmediately = false;
    final View view = inflater.inflate(layout, parent, shouldAttachToParentImmediately);
    videoViewHolder viewHolder = new videoViewHolder(view);

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull final videoViewHolder holder, final int position) {

    if(youtubeVidList.get(position) == null)
      return;


    holder.thumbnail.setImageResource(youtubeVidList.get(position).thumbnail);
    holder.thumbnail.bringToFront();
    holder.thumbnail.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        holder.thumbnail.setVisibility(View.GONE);
        holder.youTubePlayerView.setVisibility(View.VISIBLE);
        holder.youTubePlayerView.initialize(PlayerConfig.API_KEY, holder.onInitializedListener);
      }
    });

    holder.onInitializedListener = new OnInitializedListener() {
      @Override
      public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer,
          boolean b) {
        youTubePlayer.loadVideo(youtubeVidList.get(position).url);
        youTubePlayer.play();
      }

      @Override
      public void onInitializationFailure(Provider provider,
          YouTubeInitializationResult youTubeInitializationResult) {

      }
    };

  }

  @Override
  public int getItemCount() {
    return youtubeVidList.size();
  }

  public class videoViewHolder extends RecyclerView.ViewHolder {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    ImageView thumbnail;

    public videoViewHolder(View itemView) {
      super(itemView);

      youTubePlayerView = itemView.findViewById(R.id.youTubePlayer);
      thumbnail = itemView.findViewById(R.id.thumbnail);



    }
  }

}
