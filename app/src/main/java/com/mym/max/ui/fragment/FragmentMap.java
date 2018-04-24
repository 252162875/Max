package com.mym.max.ui.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * fragment集合管理类
 */
public class FragmentMap {

    private final HashMap<Integer, Fragment> mSavedFragment;
    private final ArrayList<Integer> keys;

    @SuppressLint("UseSparseArrays")
    public FragmentMap() {
        mSavedFragment = new HashMap<>();
        keys = new ArrayList<>();
    }

    public void addFragment(int po, Fragment fragment) {
        if (fragment != null) {
            mSavedFragment.put(po, fragment);
            keys.add(po);
        }
    }

    public Fragment getFragment(int key) {
        return mSavedFragment.get(key);
    }

    public void showRightToTargt(int key, View view, FragmentManager manage) {
        FragmentTransaction transaction = manage.beginTransaction();
        Fragment fm = getFragment(key);
        if (fm.isVisible()) {
            return;
        }
        if (fm.isAdded()) {
            fm.onResume();
        } else {
            transaction.add(view.getId(), fm);
        }
        for (int i = 0; i < keys.size(); i++) {
            Integer tempKey = keys.get(i);
            Fragment fragment = mSavedFragment.get(tempKey);
            if (fragment != null) {
                FragmentTransaction ft = manage.beginTransaction();
                if (key == tempKey) {
                    ft.show(fragment);
                    fragment.setUserVisibleHint(true);
                } else {
                    ft.hide(fragment);
                    fragment.setUserVisibleHint(false);
                }
                ft.commit();
            }
        }
        transaction.commit();
    }

}