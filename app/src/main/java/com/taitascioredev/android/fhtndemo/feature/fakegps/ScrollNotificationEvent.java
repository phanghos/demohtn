package com.taitascioredev.android.fhtndemo.feature.fakegps;

import java.util.Observable;

/**
 * Created by rrtatasciore on 13/01/18.
 */

public class ScrollNotificationEvent extends Observable {

    private static final ScrollNotificationEvent INSTANCE = new ScrollNotificationEvent();

    private ScrollNotificationEvent(){}

    public static ScrollNotificationEvent getInstance() {
        return INSTANCE;
    }

    public void post() {
        setChanged();
        if (countObservers() > 0) {
            notifyObservers();
        }
    }
}
