package com.terasoltechnologies.branchdeeplink;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.branch.referral.Branch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected Branch.BranchReferralInitListener branchReferralInitListener = ((referringParams, error) -> {
        //TODO Implement Branch
        if (error == null) {
            if (referringParams != null) {
                Log.i("BRANCH SDK", referringParams.toString());
                Toast.makeText(getApplicationContext(), referringParams.toString(), Toast.LENGTH_LONG).show();
            } else {
                Log.i("BRANCH SDK", "ParamError");
                Toast.makeText(getApplicationContext(), "ParamError", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.i("BRANCH SDK", error.getMessage());
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
        }
    });

    @Override
    protected void onStart() {
        super.onStart();
        Branch.sessionBuilder(this).withCallback(branchReferralInitListener).withData(getIntent() != null ? getIntent().getData() : null).init();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//         if activity is in foreground (or in backstack but partially visible) launching the same
//         activity will skip onStart, handle this case with reInitSession
        Branch.sessionBuilder(this).withCallback(branchReferralInitListener).reInit();
    }
}