package com.teknasyon.busetekinaydin.rahatlaticisesler.features;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.teknasyon.busetekinaydin.rahatlaticisesler.R;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundModel;

public class SoundsViewHolder extends RecyclerView.ViewHolder {

    Button playButton;
    LikeButton favoriteButton;
    SeekBar soundVolumeSeekBar;
    TextView soundName;
    SoundModel sound;
    AudioManager audioManager = null;

    public SoundsViewHolder(final View itemView) {
        super(itemView);

        playButton = itemView.findViewById(R.id.favorite_sounds_list_play_button);
        favoriteButton = itemView.findViewById(R.id.star_button);
        soundVolumeSeekBar = itemView.findViewById(R.id.fragment_favorite_sound_list_seekbar);
        soundName = itemView.findViewById(R.id.favorite_sounds_list_sound_name);

        audioManager = (AudioManager) itemView.getContext().getSystemService(Context.AUDIO_SERVICE);

        soundVolumeSeekBar.setMax(audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        soundVolumeSeekBar.setProgress(audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC));


        soundVolumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void getSounds(Context context, SoundModel soundModel) {
        itemView.setTag(soundModel);
        this.sound = soundModel;
        soundName.setText(soundModel.getSoundName());
    }

}
