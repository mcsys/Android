package com.passionvirus.appiconchanger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sys on 2017. 12. 20..
 */

public class AppIconChanger {

    private Activity mActivity;
    private List<String> mActivityAliasList;

    public AppIconChanger() {
        if (mActivityAliasList == null) {
            mActivityAliasList = new ArrayList<String>();
        }
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public void addAlias(String alias) {
        mActivityAliasList.add(alias);
    }

    /*
    public removeAlias(String alias) {
        mActivityAliasList.remove(alias);
    }
    */


    public void setAlias(String activeAlias) {

        String packageName = mActivity.getPackageName();

        for (String alias : mActivityAliasList) {
            try {
                if (alias.equalsIgnoreCase(activeAlias)) {
                    mActivity.getPackageManager().setComponentEnabledSetting(
                            new ComponentName(BuildConfig.APPLICATION_ID, BuildConfig.APPLICATION_ID + "." + alias),
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                }
                else {
                    mActivity.getPackageManager().setComponentEnabledSetting(
                            new ComponentName(BuildConfig.APPLICATION_ID, BuildConfig.APPLICATION_ID + "." + alias),
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
