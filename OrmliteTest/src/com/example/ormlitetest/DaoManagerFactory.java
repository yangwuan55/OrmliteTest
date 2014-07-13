package com.example.ormlitetest;

import android.content.Context;
import com.example.ormlitetest.beans.Child;

/**
 * Created by ymr on 14-7-13.
 */
public class DaoManagerFactory {
    public static MyDAOManager<Child> getChildManager(Context context) {
        return new MyDAOManager<Child>(context,Child.class);
    }
}
