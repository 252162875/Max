package com.mym.max.app;

import com.mym.max.ui.activity.LiveActivity;

/**
 *
 */

public class Constants {
    private LiveActivity liveActivity = null;

    private static class Builder {
        private static Constants instance = new Constants();
    }

    public static Constants getConstants() {
        return Builder.instance;
    }

    public LiveActivity getLiveActivity() {
        return liveActivity;
    }

    public void setLiveActivity(LiveActivity liveActivity) {
        this.liveActivity = liveActivity;
    }



//    public Constants getInstance() {
//        if (instance == null) {
//            instance = new Constants();
//        }
//        return instance;
//    }
//
//
//    public class Singleton {
//        private static class Holder {
//            private static Singleton singleton = new Singleton();
//        }
//
//        private Singleton(){}
//
//        public static Singleton getSingleton(){
//            return Holder.singleton;
//        }
//    }
}
