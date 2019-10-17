package com.lsw.carmanager;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.lsw.carmanager.utils.FileUtils;

import jp.wasabeef.takt.Seat;
import jp.wasabeef.takt.Takt;

/**
 * Created by sweeneyliu on 2019/8/13.
 */
public class CarManagerApplication  extends Application {
    private static Context mContext;
    public final static String ROM_TEST_PATH = "romtest";
    String romTestPath;
    String readBigFile;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
        mContext = getApplicationContext();
        romTestPath = getApplicationContext().getExternalCacheDir().getPath() + "/romtest";
        readBigFile = getApplicationContext().getExternalCacheDir().getPath() + "/romtest/big_file.txt";
        if(!FileUtils.isFileExists(readBigFile)){
            FileUtils.copyFilesFromAssets(this,ROM_TEST_PATH,romTestPath);
        }
        Takt.stock(this)
                .seat(Seat.TOP_RIGHT)
                .interval(250)
                .color(Color.BLUE)
                .size(16f);
    }

    // 获取Application
    public static Context getContext() {
        return mContext;
    }

}
