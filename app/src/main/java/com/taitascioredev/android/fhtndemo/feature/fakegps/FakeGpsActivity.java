package com.taitascioredev.android.fhtndemo.feature.fakegps;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.taitascioredev.android.fhtndemo.R;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by rrtatasciore on 13/01/18.
 */

public class FakeGpsActivity extends AppCompatActivity implements LocationAssistant.Listener {

    private static final String TAG = FakeGpsActivity.class.getSimpleName();

    private LocationAssistant locationAssistant;

    private int count = 0;

    private LocationAdapter adapter;

    private RecyclerView recyclerView;

    private ProgressWheel progressWheel;

    private Observer scrollObserver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_gps);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindViews();

        locationAssistant = new LocationAssistant(this, this, LocationAssistant.Accuracy.HIGH, 1000, false);
        locationAssistant.setVerbose(true);

        showProgress();
    }

    private void bindViews() {
        recyclerView = findViewById(R.id.list);
        progressWheel = findViewById(R.id.progress_wheel);

        adapter = new LocationAdapter();
        recyclerView.setAdapter(adapter);

        scrollObserver = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                recyclerView.smoothScrollToPosition(0);
            }
        };

        ScrollNotificationEvent.getInstance().addObserver(scrollObserver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationAssistant.start();
        count = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationAssistant.stop();
        hideProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScrollNotificationEvent.getInstance().deleteObserver(scrollObserver);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationAssistant.onPermissionsUpdated(requestCode, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        locationAssistant.onActivityResult(requestCode, resultCode);
    }

    private void showProgress() {
        progressWheel.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressWheel.setVisibility(View.GONE);
    }

    @Override
    public void onNeedLocationPermission() {
        locationAssistant.requestLocationPermission();
    }

    @Override
    public void onExplainLocationPermission() {

    }

    @Override
    public void onLocationPermissionPermanentlyDeclined(View.OnClickListener fromView,
                                                        DialogInterface.OnClickListener fromDialog) {

    }

    @Override
    public void onNeedLocationSettingsChange() {
        Log.d(TAG, "need location change");
    }

    @Override
    public void onFallBackToSystemSettings(View.OnClickListener fromView, DialogInterface.OnClickListener fromDialog) {

    }

    @Override
    public void onNewLocationAvailable(SafeLocation location) {
        count++;
        if (count > 3) {
            hideProgress();
            adapter.add(location);
        }
    }

    @Override
    public void onMockLocationsDetected(SafeLocation location) {

    }

    @Override
    public void onError(LocationAssistant.ErrorType type, String message) {

    }
}
