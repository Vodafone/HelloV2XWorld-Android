package com.vodafone.v2x.android.hellov2xworld.view.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.link.DefaultLinkHandler;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.vodafone.v2x.android.hellov2xworld.R;
import com.vodafone.v2x.android.hellov2xworld.databinding.ActivitySplashBinding;
import com.vodafone.v2x.android.hellov2xworld.utils.Parameters;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;
import timber.log.Timber;
/**

 The SplashActivity class is the starting activity of the app. It displays the terms and conditions of using the app, and prompts the user to accept them before accessing the main app.
 The class also checks the necessary permissions required for the app to function and requests for them if necessary.
 */
public class SplashActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, OnPageChangeListener {

    private static final String fineLocation = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final int REQUIRED_PERMISSIONS_CODE_FINE = 15;
    private ActivitySplashBinding binding;
    private Parameters parameters;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("Lifecycle : onCreate()");
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        parameters = Parameters.getInstance(getApplicationContext());
        if (parameters.getTermsAndConditionsAccepted()) {
            checkPermissions();
        } else {
            setupPdfViewer();
        }
        setupButtons();

    }

    public void checkPermissions() {
        Timber.d("checkPermissions()");
        ArrayList<String> permissions = new ArrayList<>();
        boolean hasFineLocation = hasPermission(fineLocation);
        if (!hasFineLocation) {
            permissions.add(fineLocation);
        }
        requestPermissions(permissions);
    }

    public boolean hasPermission(String permission) {
        Timber.d("hasPermission() ,permission=%s", permission);
        return EasyPermissions.hasPermissions(this, permission);
    }

    public void requestPermissions(List<String> permissions) {
        Timber.d("requestPermissions()");
        String[] permissionsToRequire = permissions.toArray(new String[0]);
        if (!permissions.isEmpty()) {
            PermissionRequest request = new PermissionRequest.Builder(this, REQUIRED_PERMISSIONS_CODE_FINE, permissionsToRequire)
                    .setPositiveButtonText("OK")
                    .setNegativeButtonText("")
                    .setRationale("The application needs this permission to work properly")
                    .build();
            EasyPermissions.requestPermissions(request);
        } else {
            launchMainActivity();
        }
    }

    private void setupButtons() {
        binding.btAccept.setOnClickListener(v -> {
            boolean areTermsAndCondSet = parameters.setTermsAndConditionsAccepted(true);
            if (areTermsAndCondSet) {
                checkPermissions();
            }
        });
        binding.btExit.setOnClickListener(v -> exitApp());
    }

    private void setupPdfViewer() {
        binding.pdfViewTermsAndConditions.fromAsset("terms_and_conditions.pdf")
                .fitEachPage(true)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .linkHandler(new DefaultLinkHandler(binding.pdfViewTermsAndConditions))
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                .load();
    }

    private void launchMainActivity() {
        Timber.d("launchMainActivity()");
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void exitApp() {
        Timber.d("exitApp()");
        this.finish();
        System.exit(0);
    }

    @Override
    protected void onStart() {
        Timber.d("Lifecycle : onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Timber.d("Lifecycle : onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Timber.d("Lifecycle : onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Timber.d("Lifecycle : onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Timber.d("Lifecycle : onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Timber.d("onRequestPermissionsResult()");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Timber.d("onPermissionsGranted()");
        if (requestCode == REQUIRED_PERMISSIONS_CODE_FINE) {
            if (hasPermission(fineLocation)
            ) {
                launchMainActivity();
            }
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Timber.d("onPermissionsDenied()");
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        } else {
            requestPermissions(perms);
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        binding.tvPageCounter.setText(String.format(getString(R.string.page_counter), page + 1, pageCount));
        if (page == pageCount - 1) {
            // User arrived to the last page
            binding.btAccept.setEnabled(true);
            binding.btAccept.setAlpha(0.8F);
        }
    }
}
