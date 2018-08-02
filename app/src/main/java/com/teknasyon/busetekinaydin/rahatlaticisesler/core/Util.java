package com.teknasyon.busetekinaydin.rahatlaticisesler.core;

import android.content.Context;

import com.teknasyon.busetekinaydin.rahatlaticisesler.R;
import com.teknasyon.busetekinaydin.rahatlaticisesler.model.SoundModel;

import java.util.ArrayList;
import java.util.Properties;

public final class Util {

    public static int soundResId(String soundSrc){

        int resId=0;

        if(soundSrc.equals("ambulans")){
            resId = R.raw.ambulans;
        }else if(soundSrc.equals("chopinnocturne")){
            resId = R.raw.chopinnocturne;
        }else if(soundSrc.equals("dalga")){
            resId = R.raw.dalga;
        }else if(soundSrc.equals("evgenygrinkovalse")){
            resId = R.raw.evgenygrinkovalse;
        } else if(soundSrc.equals("gece")){
            resId = R.raw.gece;
        }else if(soundSrc.equals("horoz")){
            resId = R.raw.horoz;
        }else if(soundSrc.equals("itfaiye")){
            resId = R.raw.itfaiye;
        }else if(soundSrc.equals("kanarya")){
            resId = R.raw.kanarya;
        }else if(soundSrc.equals("sivaskangal")){
            resId = R.raw.kangal;
        }else if(soundSrc.equals("labrador")){
            resId = R.raw.labrador;
        }else if(soundSrc.equals("mozartturkishmarch")){
            resId = R.raw.mozartturkishmarch;
        }else if(soundSrc.equals("polis")){
            resId = R.raw.polis;
        }else if(soundSrc.equals("simsek")){
            resId = R.raw.simsek;
        }else if(soundSrc.equals("yagmur")){
            resId = R.raw.yagmur;
        }else{
            resId = R.raw.yagmur; //default mp3
        }

        return resId;
    }
}
