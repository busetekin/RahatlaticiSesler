package com.teknasyon.busetekinaydin.rahatlaticisesler.network;

import com.teknasyon.busetekinaydin.rahatlaticisesler.model.CategoryResponse;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("myFavoriteList.html")
    Call<SoundsResponse> getMyFavoriteSounds();

    @GET("category.html")
    Call<CategoryResponse> getCategories();


    @GET("mylibrary.html")
    Call<SoundsResponse> getMyLibrarySounds();
}
