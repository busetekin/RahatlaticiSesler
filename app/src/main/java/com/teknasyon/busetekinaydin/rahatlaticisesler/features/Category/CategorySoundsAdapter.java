package com.teknasyon.busetekinaydin.rahatlaticisesler.features.Category;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teknasyon.busetekinaydin.rahatlaticisesler.R;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.CategoryModel;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundModel;

import java.util.ArrayList;
import java.util.List;

public class CategorySoundsAdapter extends RecyclerView.Adapter<CategorySoundsViewHolder>
        implements View.OnClickListener {

    ArrayList<CategoryModel> categorySoundList;
    ArrayList<SoundModel> librarySoundList;
    private LayoutInflater layoutInflater;
    private AppCompatActivity appCompatActivity;
    private CategorySoundsListener categorySoundsListener;

    public CategorySoundsAdapter(AppCompatActivity appCompatActivity,
                                 CategorySoundsAdapter.CategorySoundsListener categorySoundsListener) {
        this.appCompatActivity = appCompatActivity;
        this.categorySoundsListener = categorySoundsListener;
        layoutInflater = appCompatActivity.getLayoutInflater();
        categorySoundList = new ArrayList<>();
        librarySoundList = new ArrayList<>();
    }

    @Override
    public CategorySoundsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootview = layoutInflater.inflate(R.layout.category_sound_list, parent, false);
        rootview.setOnClickListener(this);

        return new CategorySoundsViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(CategorySoundsViewHolder holder, int position) {

        // bind all category(category.html) and hole sounds(myLibrary.html)
        holder.getCategory(appCompatActivity, categorySoundList.get(position),librarySoundList);
    }

    @Override
    public int getItemCount() {
        return categorySoundList.size();
    }

    @Override
    public void onClick(View view) {

        if (view.getTag() instanceof CategoryModel) {
            CategoryModel category = (CategoryModel) view.getTag();
            categorySoundsListener.CategorySoundsListener(category);
        }
    }


    public interface CategorySoundsListener {

        public void CategorySoundsListener(CategoryModel category);
    }

    public void addCategorySounds(List<CategoryModel> list) {
        categorySoundList.addAll(list);
        notifyDataSetChanged();
    }

    public void addLibrarySounds(List<SoundModel> list) {
        librarySoundList.addAll(list);
        notifyDataSetChanged();
    }
}
