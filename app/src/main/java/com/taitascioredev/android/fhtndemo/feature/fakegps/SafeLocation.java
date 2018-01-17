package com.taitascioredev.android.fhtndemo.feature.fakegps;

import android.location.Location;

/**
 * Created by rrtatasciore on 13/01/18.
 */

public class SafeLocation {

    private Location location;

    private boolean isMockedLocation;

    public SafeLocation(Location location, boolean isMockedLocation) {
        this.setLocation(location);
        this.setIsMockedLocation(isMockedLocation);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isMockedLocation() {
        return isMockedLocation;
    }

    public void setIsMockedLocation(boolean mockedLocation) {
        isMockedLocation = mockedLocation;
    }
}
