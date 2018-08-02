package com.teknasyon.busetekinaydin.rahatlaticisesler.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SoundsResponse implements Parcelable {

    @SerializedName("Sounds")
    private List<SoundModel> soundModelList;

    private SoundsResponse() {

    }

    protected SoundsResponse(Parcel in) {
        soundModelList = in.createTypedArrayList(SoundModel.CREATOR);
    }

    public static final Creator<SoundsResponse> CREATOR = new Creator<SoundsResponse>() {
        @Override
        public SoundsResponse createFromParcel(Parcel in) {
            return new SoundsResponse(in);
        }

        @Override
        public SoundsResponse[] newArray(int size) {
            return new SoundsResponse[size];
        }
    };

    public List<SoundModel> getSoundModelList() {
        return soundModelList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(soundModelList);
    }
}
