package com.taitascioredev.android.fhtndemo.feature.animations;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.BounceInterpolator;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.taitascioredev.android.fhtndemo.R;

/**
 * Created by rrtatasciore on 13/01/18.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Marker marker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animations, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.item_90_degrees) {
            final ValueAnimator valueAnimator = ValueAnimator
                    .ofFloat(0f, 90f)
                    .setDuration(2000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    marker.setRotation((Float) animation.getAnimatedValue());
                }
            });
            valueAnimator.setInterpolator(new BounceInterpolator());
            valueAnimator.start();
        } else if (item.getItemId() == R.id.item_full_circle) {
            final ValueAnimator valueAnimator = ValueAnimator
                    .ofFloat(0f, 360f)
                    .setDuration(3000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    marker.setRotation((Float) animation.getAnimatedValue());
                }
            });
            valueAnimator.setInterpolator(new BounceInterpolator());
            valueAnimator.start();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        final Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        this.marker = marker;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13f));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MarkerAnimation.animateMarkerToGB(marker, 1500);
            }
        }, 50);
    }
}
