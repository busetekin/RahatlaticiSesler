package com.teknasyon.busetekinaydin.rahatlaticisesler.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {
    public static final String PREFS_NAME = "SOUNDS_APP";
    public static final String FAVORITES = "Sounds_Favorite";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<SoundModel> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, SoundModel sound) {
        List<SoundModel> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<SoundModel>();
        favorites.add(sound);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, int soundId) {
        ArrayList<SoundModel> favorites = getFavorites(context);
        if (favorites != null) {
            for (int i=0; i < favorites.size(); i++)
            {
                if(favorites.get(i).getSoundId() == soundId) {
                    favorites.remove(i);
                    saveFavorites(context, favorites);
                    break;
                }
            }
        }
    }

    public ArrayList<SoundModel> getFavorites(Context context) {
        SharedPreferences settings;
        List<SoundModel> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            SoundModel[] favoriteItems = gson.fromJson(jsonFavorites,
                    SoundModel[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<SoundModel>(favorites);
        } else
            return null;

        return (ArrayList<SoundModel>) favorites;
    }
}
