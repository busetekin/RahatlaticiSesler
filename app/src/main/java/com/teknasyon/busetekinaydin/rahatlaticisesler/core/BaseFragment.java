package com.teknasyon.busetekinaydin.rahatlaticisesler.core;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teknasyon.busetekinaydin.rahatlaticisesler.R;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundsResponse;
import com.teknasyon.busetekinaydin.rahatlaticisesler.network.Service;
import com.teknasyon.busetekinaydin.rahatlaticisesler.network.SoundProvider;

import retrofit2.Call;


public class BaseFragment extends Fragment {

    protected Service apiService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = SoundProvider.getClient(getContext()).create(Service.class);
    }
}
