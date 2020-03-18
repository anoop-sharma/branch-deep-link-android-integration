package com.terasoltechnologies.branchdeeplink;

import android.app.Application;

import io.branch.referral.Branch;

public class deepLinkApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Branch logging for debugging
        Branch.enableLogging();

        // Branch object initialization
        Branch.getAutoInstance(this);
    }
}
