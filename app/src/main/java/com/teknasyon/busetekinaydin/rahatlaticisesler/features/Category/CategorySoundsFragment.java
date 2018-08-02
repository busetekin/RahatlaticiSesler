package com.teknasyon.busetekinaydin.rahatlaticisesler.features.Category;

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
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.CategoryModel;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.CategoryResponse;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundModel;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorySoundsFragment extends BaseFragment implements CategorySoundsAdapter.CategorySoundsListener {

    private ArrayList<CategoryModel> categoryList;
    private ArrayList<SoundModel> soundList;
    protected Call<CategoryResponse> categoryCall;
    protected Call<SoundsResponse> soundCall;
    private RecyclerView categoryRecyclerView;
    private CategorySoundsAdapter categorySoundsAdapter;
    SharedPreference sharedPreference;

    public CategorySoundsFragment() {

    }

    public static CategorySoundsFragment newInstance() {

        return new CategorySoundsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadCategories();
        loadSounds();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sounds_category, container, false);

        categorySoundsAdapter = new CategorySoundsAdapter((AppCompatActivity) getActivity(), this);

        categoryRecyclerView = rootView.findViewById(R.id.fragment_sound_category_recyclerview);
        categoryRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);

        if (isAdded()) {
            categoryRecyclerView.setAdapter(categorySoundsAdapter);
        }

        return rootView;
    }

    // service call category.html
    public void loadCategories() {

        sharedPreference = new SharedPreference();
        ArrayList<SoundModel> favorites = sharedPreference.getFavorites(getContext());

        categoryCall = apiService.getCategories();

        categoryCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    categoryList = (ArrayList<CategoryModel>) response.body().getCategoryModelList();

                    categorySoundsAdapter.addCategorySounds(categoryList);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //service call myLibrary.html
    private void loadSounds() {

        soundCall = apiService.getMyLibrarySounds();

        soundCall.enqueue(new Callback<SoundsResponse>() {
            @Override
            public void onResponse(Call<SoundsResponse> call, Response<SoundsResponse> response) {
                if (response.isSuccessful()) {
                    soundList = (ArrayList<SoundModel>) response.body().getSoundModelList();
                    categorySoundsAdapter.addLibrarySounds(soundList);
                }
            }

            @Override
            public void onFailure(Call<SoundsResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void CategorySoundsListener(CategoryModel category) {

    }
}
