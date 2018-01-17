package com.taitascioredev.android.fhtndemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.taitascioredev.android.fhtndemo.feature.animations.MapActivity;
import com.taitascioredev.android.fhtndemo.feature.fakegps.FakeGpsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MapActivity.class.getSimpleName();

    private static final int INVITE_REQUEST_CODE = 001;

    private Button btnDemoAnimations;

    private Button btnDemoFakeGps;

    private Button btnDemoInvites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        if (getIntent().getData() != null) {
            Log.e(TAG, getIntent().getDataString());
            Uri data = getIntent().getData();
            String invitationId = data.getQueryParameter("invitation_id");
            Toast.makeText(this, "Has accedido a traves de la invitacion con ID [" + invitationId + "]", Toast.LENGTH_LONG).show();
        }
    }

    private void bindViews() {
        btnDemoAnimations = findViewById(R.id.btn_demo_animations);
        btnDemoFakeGps = findViewById(R.id.btn_demo_fake_gps);
        btnDemoInvites = findViewById(R.id.btn_demo_invites);

        btnDemoAnimations.setOnClickListener(this);
        btnDemoFakeGps.setOnClickListener(this);
        btnDemoInvites.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_demo_animations) {
            launchActivity(MapActivity.class);
        } else if (v.getId() == R.id.btn_demo_fake_gps) {
            launchActivity(FakeGpsActivity.class);
        } else {
            onInviteClicked();
        }
    }

    private void launchActivity(Class<? extends AppCompatActivity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    private void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder("Invita a colaborar")
                .setMessage("Invitacion a FHTN")
                .setDeepLink(Uri.parse("demo://menu/invite"))
                .setCallToActionText("call to action")
                .build();
        startActivityForResult(intent, INVITE_REQUEST_CODE);
    }
}
