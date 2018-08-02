package com.teknasyon.busetekinaydin.rahatlaticisesler.features;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.teknasyon.busetekinaydin.rahatlaticisesler.R;
import com.teknasyon.busetekinaydin.rahatlaticisesler.core.SharedPreference;
import com.teknasyon.busetekinaydin.rahatlaticisesler.core.Util;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundModel;

import java.util.ArrayList;
import java.util.List;

public class SoundsAdapter extends RecyclerView.Adapter<SoundsViewHolder>
        implements View.OnClickListener {

    ArrayList<SoundModel> soundList;
    private LayoutInflater layoutInflater;
    private AppCompatActivity appCompatActivity;
    private FavoriteSoundsListener favoriteSoundsListener;
    SharedPreference sharedPreference;

    public SoundsAdapter(AppCompatActivity appCompatActivity, FavoriteSoundsListener favoriteSoundsListener) {
        this.appCompatActivity = appCompatActivity;
        this.favoriteSoundsListener = favoriteSoundsListener;
        layoutInflater = appCompatActivity.getLayoutInflater();
        soundList = new ArrayList<SoundModel>();
        sharedPreference = new SharedPreference();
    }

    @NonNull
    @Override
    public SoundsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootview = layoutInflater.inflate(R.layout.favorite_sounds_list, parent, false);
        rootview.setOnClickListener(this);

        return new SoundsViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(final SoundsViewHolder holder, final int position) {

        holder.getSounds(appCompatActivity, soundList.get(position));

        final String soundSource = soundList.get(position).getSoundSrc();
        final MediaPlayer mediaPlayer = MediaPlayer.create(appCompatActivity.getBaseContext(), Util.soundResId(soundSource));

        boolean isInFavoriteList = checkFavoriteItem(soundList.get(position));

        if(isInFavoriteList)
        {
            holder.favoriteButton.setLiked(true);
        }

        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    holder.playButton.setBackgroundResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    holder.playButton.setBackgroundResource(R.drawable.pause);
                }
            }
        });

        holder.favoriteButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                sharedPreference.addFavorite(appCompatActivity.getBaseContext(), soundList.get(position));
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                sharedPreference.removeFavorite(appCompatActivity.getBaseContext(), soundList.get(position).getSoundId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return soundList.size();
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() instanceof SoundModel) {
            SoundModel soundModel = (SoundModel) view.getTag();
            favoriteSoundsListener.FavoriteSoundsListener(soundModel);
        }
    }

    public interface FavoriteSoundsListener {
        public void FavoriteSoundsListener(SoundModel favoriteSound);
    }

    public void addSounds(List<SoundModel> list) {

        soundList.addAll(list);
        notifyDataSetChanged();
    }

    //update Favorilerim Tab source arraylist
    public void removeAllThenAddSounds(List<SoundModel> list) {
        if(list != null)
        {
            soundList = new ArrayList<>();
            soundList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public boolean checkFavoriteItem(SoundModel checkSound) {
        boolean check = false;
        List<SoundModel> favorites = sharedPreference.getFavorites(appCompatActivity.getBaseContext());
        if (favorites != null) {
            for (SoundModel sound : favorites) {
                if (sound.getSoundId() == checkSound.getSoundId()) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
}
