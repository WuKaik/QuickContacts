package com.sasincomm.quickcontacts.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 作者：wk on 2018/12/12.
 */
public class PrefUtil {

    private static SharedPreferences getSharedPreferences(Context paramContext)
    {
        return PreferenceManager.getDefaultSharedPreferences(paramContext);
    }

}
