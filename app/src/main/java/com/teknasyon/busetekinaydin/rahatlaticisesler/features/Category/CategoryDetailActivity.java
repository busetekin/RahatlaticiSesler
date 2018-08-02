package com.teknasyon.busetekinaydin.rahatlaticisesler.features.Category;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.teknasyon.busetekinaydin.rahatlaticisesler.R;
import com.teknasyon.busetekinaydin.rahatlaticisesler.features.SoundsAdapter;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.CategoryModel;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundModel;

import java.util.ArrayList;

public class CategoryDetailActivity extends AppCompatActivity implements SoundsAdapter.FavoriteSoundsListener {

    private static final String KEY_CATEGORY = "CategoryInformation";
    private static final String KEY_LIBRARY = "LibraryInformation";

    private CategoryModel categoryModel;
    private TextView categoryName;
    private RecyclerView categoryDetailRecyclerview;
    private SoundsAdapter soundsAdapter;
    private ArrayList<SoundModel> librarySounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        librarySounds = new ArrayList<SoundModel>();

        categoryModel = getIntent().getExtras().getParcelable(KEY_CATEGORY);
        librarySounds = getIntent().getParcelableArrayListExtra(KEY_LIBRARY);

        setContentView(R.layout.activity_category_detail);
        soundsAdapter = new SoundsAdapter((AppCompatActivity) this, this);

        categoryName = findViewById(R.id.activity_category_detail_sound_name);
        categoryDetailRecyclerview = findViewById(R.id.activity_category_detail_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        categoryDetailRecyclerview.setLayoutManager(layoutManager);
        categoryName.setText(categoryModel.getCategoryName());

        categoryDetailRecyclerview.setAdapter(soundsAdapter);

        soundsAdapter.addSounds(getLibrarySounds(categoryModel.getCategoryId()));
    }


    // This method gets sounds(myLibrary.html) by category
    public ArrayList<SoundModel> getLibrarySounds(int categoryId) {

        ArrayList<SoundModel> categorySoundList = new ArrayList<>();

        for (int i = 0; i < librarySounds.size(); i++) {
            if (librarySounds.get(i).getCategoryId() == categoryId) {
                categorySoundList.add(librarySounds.get(i));
            }
        }
        return categorySoundList;
    }

    public static Intent newIntent(Context context, CategoryModel category, ArrayList<SoundModel> librarySounds) {

        Intent intent = new Intent(context, CategoryDetailActivity.class);
        intent.putExtra(KEY_CATEGORY, category);
        intent.putParcelableArrayListExtra(KEY_LIBRARY, librarySounds);
        return intent;
    }

    @Override
    public void FavoriteSoundsListener(SoundModel favoriteSound) {

    }
}
