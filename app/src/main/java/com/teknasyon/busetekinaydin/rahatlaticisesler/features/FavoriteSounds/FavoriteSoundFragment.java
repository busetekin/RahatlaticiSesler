package com.teknasyon.busetekinaydin.rahatlaticisesler.features.FavoriteSounds;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teknasyon.busetekinaydin.rahatlaticisesler.R;
import com.teknasyon.busetekinaydin.rahatlaticisesler.core.BaseFragment;
import com.teknasyon.busetekinaydin.rahatlaticisesler.core.SharedPreference;
import com.teknasyon.busetekinaydin.rahatlaticisesler.features.SoundsAdapter;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundModel;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteSoundFragment extends BaseFragment implements SoundsAdapter.FavoriteSoundsListener {

    private SoundsAdapter soundsAdapter;
    private RecyclerView recyclerView;
    private  ArrayList<SoundModel> favoriteSounds;
    protected Call<SoundsResponse> call;
    SharedPreference sharedPreference;

    public FavoriteSoundFragment() {

    }

    public static FavoriteSoundFragment newInstance() {
        return new FavoriteSoundFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorite_sound, container, false);

        soundsAdapter = new SoundsAdapter((AppCompatActivity) getActivity(), this);

        recyclerView = rootView.findViewById(R.id.fragment_favorite_sound_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        if (isAdded()) {
            recyclerView.setAdapter(soundsAdapter);
        }

        loadFavoriteSounds();

        return rootView;
    }

    // update Favorilerim tab view
    @Override
    public void onResume() {
        super.onResume();
        favoriteSounds = sharedPreference.getFavorites(getContext());
        soundsAdapter.removeAllThenAddSounds(favoriteSounds);
    }

    //service call myFavorites.html
    public void loadFavoriteSounds() {


        sharedPreference = new SharedPreference();
        ArrayList<SoundModel> favoriteSoundList = sharedPreference.getFavorites(getContext());


        if(favoriteSoundList == null)
        {
            call = apiService.getMyFavoriteSounds();

            call.enqueue(new Callback<SoundsResponse>() {
                @Override
                public void onResponse(Call<SoundsResponse> call, Response<SoundsResponse> response) {
                    if (response.isSuccessful()) {
                        favoriteSounds = (ArrayList<SoundModel>) response.body().getSoundModelList();
                        soundsAdapter.addSounds(favoriteSounds);
                       sharedPreference.saveFavorites(getContext(),favoriteSounds);
                    }
                }

                @Override
                public void onFailure(Call<SoundsResponse> call, Throwable t) {

                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            favoriteSounds = favoriteSoundList;
            soundsAdapter.addSounds(favoriteSounds);
        }
    }

    @Override
    public void FavoriteSoundsListener(SoundModel favoriteSound) {

    }
}
