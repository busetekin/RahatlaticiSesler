package com.teknasyon.busetekinaydin.rahatlaticisesler.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SoundModel implements Parcelable {

    @SerializedName("CategoryId")
    private int categoryId;

    @SerializedName("SoundId")
    private int soundId;

    @SerializedName("SoundName")
    private String soundName;

    @SerializedName("Src")
    private String soundSrc;


    public SoundModel() {

    }

    public SoundModel(String voiceName) {
        this.soundName = voiceName;
    }

    protected SoundModel(Parcel in) {
        categoryId = in.readInt();
        soundId = in.readInt();
        soundName = in.readString();
        soundSrc = in.readString();
    }

    public static final Creator<SoundModel> CREATOR = new Creator<SoundModel>() {
        @Override
        public SoundModel createFromParcel(Parcel in) {
            return new SoundModel(in);
        }

        @Override
        public SoundModel[] newArray(int size) {
            return new SoundModel[size];
        }
    };

    public int getCategoryId() {
        return categoryId;
    }

    public String getSoundName() {
        return soundName;
    }

    public int getSoundId() {
        return soundId;
    }

    public String getSoundSrc() {
        return soundSrc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(categoryId);
        parcel.writeInt(soundId);
        parcel.writeString(soundName);
        parcel.writeString(soundSrc);
    }
}