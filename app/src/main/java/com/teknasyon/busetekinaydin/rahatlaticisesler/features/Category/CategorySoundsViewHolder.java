package com.teknasyon.busetekinaydin.rahatlaticisesler.features.Category;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.teknasyon.busetekinaydin.rahatlaticisesler.R;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.CategoryModel;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundModel;

import java.util.ArrayList;
import java.util.List;


public class CategorySoundsViewHolder extends RecyclerView.ViewHolder {

    private CardView categoryCardview;
    private TextView categoryName;
    private CategoryModel category;
    private ArrayList<SoundModel> soundList;
    private LinearLayout cardviewLayout;

    public CategorySoundsViewHolder(View itemView) {
        super(itemView);

        categoryCardview = itemView.findViewById(R.id.category_sound_list_cardview);
        categoryName = itemView.findViewById(R.id.category_sound_list_categoryname);
        cardviewLayout = itemView.findViewById(R.id.category_sound_list_cardview_linearlayout);

        categoryCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCategoryDetail();
            }
        });
    }

    private void openCategoryDetail() {

        int position = getAdapterPosition();

        if (position != RecyclerView.NO_POSITION) {

            final Intent intent = CategoryDetailActivity.newIntent(itemView.getContext(), category, soundList);
            itemView.getContext().startActivity(intent);
        }
    }

    public void getCategory(final Context context, CategoryModel category, List<SoundModel> soundModel) {
        itemView.setTag(category);

        this.category = category;
        this.soundList = (ArrayList<SoundModel>) soundModel;
        categoryName.setText(category.getCategoryName());

        //set category background image use with picasso library
        Picasso.get().load(category.getImgPath()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                cardviewLayout.setBackground(new BitmapDrawable(context.getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
}
