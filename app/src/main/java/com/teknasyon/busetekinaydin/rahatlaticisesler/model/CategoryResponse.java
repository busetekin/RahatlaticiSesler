package com.teknasyon.busetekinaydin.rahatlaticisesler.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse implements Parcelable{

    @SerializedName("Categories")
    private List<CategoryModel> categoryModelList;

    public List<CategoryModel> getCategoryModelList() {
        return categoryModelList;
    }

    protected CategoryResponse(Parcel in) {
        categoryModelList = in.createTypedArrayList(CategoryModel.CREATOR);
    }

    public static final Creator<CategoryResponse> CREATOR = new Creator<CategoryResponse>() {
        @Override
        public CategoryResponse createFromParcel(Parcel in) {
            return new CategoryResponse(in);
        }

        @Override
        public CategoryResponse[] newArray(int size) {
            return new CategoryResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(categoryModelList);
    }
}
