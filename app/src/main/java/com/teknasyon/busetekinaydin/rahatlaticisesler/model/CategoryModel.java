package com.teknasyon.busetekinaydin.rahatlaticisesler.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CategoryModel implements Parcelable {

    @SerializedName("CategoryId")
    private int categoryId;

    @SerializedName("CategoryName")
    private String categoryName;

    @SerializedName("ImgPath")
    private String imgPath;

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public CategoryModel() {

    }

    protected CategoryModel(Parcel in) {
        categoryId = in.readInt();
        categoryName = in.readString();
        imgPath = in.readString();
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(categoryId);
        parcel.writeString(categoryName);
        parcel.writeString(imgPath);
    }
}
