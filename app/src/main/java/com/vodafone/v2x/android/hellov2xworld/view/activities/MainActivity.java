package com.vodafone.v2x.android.hellov2xworld.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.vodafone.v2x.android.hellov2xworld.BuildConfig;
import com.vodafone.v2x.android.hellov2xworld.databinding.ActivityMainBinding;
import com.vodafone.v2x.android.hellov2xworld.mapdrawing.MapManager;
import com.vodafone.v2x.android.hellov2xworld.utils.Parameters;
import com.vodafone.v2x.sdk.android.facade.exception.InvalidConfigException;
import com.vodafone.v2x.sdk.android.facade.SDKConfiguration;
import com.vodafone.v2x.sdk.android.facade.V2XSDK;
import com.vodafone.v2x.sdk.android.facade.enums.LogLevel;
import com.vodafone.v2x.sdk.android.facade.enums.MqttClientKind;
import com.vodafone.v2x.sdk.android.facade.enums.ServiceMode;
import com.vodafone.v2x.sdk.android.facade.events.BaseEvent;
import com.vodafone.v2x.sdk.android.facade.events.EventCamListChanged;
import com.vodafone.v2x.sdk.android.facade.events.EventITSLocationListChanged;
import com.vodafone.v2x.sdk.android.facade.events.EventListener;
import com.vodafone.v2x.sdk.android.facade.events.EventType;
import com.vodafone.v2x.sdk.android.facade.events.EventV2XConnectivityStateChanged;
import com.vodafone.v2x.sdk.android.facade.records.ITSLocationRecord;
import com.vodafone.v2x.sdk.android.facade.records.cam.CAMRecord;
import org.osmdroid.config.Configuration;
import java.util.List;
import timber.log.Timber;


/**
 MainActivity is the main activity class of the application which is responsible for handling the main functionality
 of the application, such as initialization of the V2XSDK, starting V2X service, subscribing to events, etc.
 */
public class MainActivity extends AppCompatActivity implements EventListener {
    /**
     Object that holds the UI elements and provides access to them
     */
    private ActivityMainBinding binding;
    /**
     Boolean to check the device orientation
     */
    private boolean isOrientationLandscape;
    /**
     Object to manage the map in the activity
     */
    private MapManager mMapManager;
    /**
     SDK Configuration instance
     */
    private SDKConfiguration sdkConfig;
    /**
     Method that is called when the activity is created.
     It initializes the UI and checks the device orientation.
     @param savedInstanceState a Bundle containing the data it most recently supplied in
     onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isOrientationLandscape = getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE;
        Timber.d("Lifecycle : onCreate() isOrientationLandscape: %s", isOrientationLandscape);
        if (isOrientationLandscape) {
            Configuration.getInstance().setUserAgentValue(this.getApplicationContext().getPackageName());
            Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.rootView);
            mMapManager = new MapManager(binding.map, this);
        }

    }

    /**
     Method that is called when the activity becomes visible.
     It initializes the V2X service and starts it if necessary.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("Lifecycle : onStart() isOrientationLandscape: %s", isOrientationLandscape);
        if (isOrientationLandscape) {
            if (!V2XSDK.getInstance().isV2XServiceInitialized()) {
                initV2XService();
            }
            if (!V2XSDK.getInstance().isV2XServiceStarted()) {
                startV2XService();
            }
        }
    }

    /**
     Method that is called when the activity is resumed.
     It loads the map configuration and initializes/starts the V2X service if necessary.
     */
    @Override
    public void onResume() {
        super.onResume();
        Timber.d("Lifecycle : onResume() isOrientationLandscape: %s", isOrientationLandscape);
        if (isOrientationLandscape) {
            binding.map.onResume();
            Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
            if (!V2XSDK.getInstance().isV2XServiceInitialized()) {
                initV2XService();
            }
            if (!V2XSDK.getInstance().isV2XServiceStarted()) {
                startV2XService();
            }
            if (V2XSDK.getInstance().isV2XServiceInitialized() && V2XSDK.getInstance().isV2XServiceStarted()) {
                V2XSDK.getInstance().subscribe(this,
                        EventType.CAM_LIST_CHANGED,
                        EventType.ITS_LOCATION_LIST_CHANGED,
                        EventType.V2X_CONNECTIVITY_STATE_CHANGED
                );
                if (!V2XSDK.getInstance().isCAMServiceRunning() && sdkConfig.isCAMServiceEnabled()) {
                    try {
                        V2XSDK.getInstance().startCAMService();
                    } catch (IllegalStateException e) {
                        Timber.e(e, "E1018");
                    }
                }
            }
        }
    }


    /**
     The onPause method in the class pauses the binding.map and calls super.onPause().
     */
    @Override
    public void onPause() {
        super.onPause();
        binding.map.onPause();
    }

    /**
     The onDestroy method in the class logs the state of isOrientationLandscape and stops the V2XSDK services if the device is in landscape orientation.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("Lifecycle : onDestroy() isOrientationLandscape: %s", isOrientationLandscape);
        if (isOrientationLandscape) {
            if (V2XSDK.getInstance().isV2XServiceInitialized() && V2XSDK.getInstance().isV2XServiceStarted()) {
                if (V2XSDK.getInstance().isCAMServiceRunning()) {
                    V2XSDK.getInstance().stopCAMService();
                }
                V2XSDK.getInstance().unsubscribe(this);
                V2XSDK.getInstance().stopV2XService();
            }
        }
    }

    /**
     * Initialize and configure the V2X Service
     * Note: Only the CAM SuBService is enabled.
     */
    private void initV2XService() {
        try {
            Parameters parameters = Parameters.getInstance(this);
            MqttClientKind mqttClient = MqttClientKind.HiveMQv3;
            SDKConfiguration.SDKConfigurationBuilder cfg = new SDKConfiguration.SDKConfigurationBuilder()
                    .withMqttClientKind(mqttClient);
            cfg.withMqttUsername(parameters.getApplicationID());
            cfg.withMqttPassword(parameters.getApplicationToken());
            cfg.withStationType(parameters.getStationType());
            cfg.withCAMServiceEnabled(true);
            cfg.withCamServiceMode(ServiceMode.TxAndRx);
            cfg.withCAMPublishGroup(parameters.getCamPublishGroup());
            cfg.withCAMSubscribeGroup(parameters.getCamSubscribeGroup());
            sdkConfig = cfg.build();
            if(BuildConfig.BUILD_TYPE.equals("debug")) {
                V2XSDK.getInstance().setLogLevel(LogLevel.LEVEL_DEBUG);
            }else {
                V2XSDK.getInstance().setLogLevel(LogLevel.LEVEL_NONE);
            }
            V2XSDK.getInstance().initV2XService(this.getApplicationContext(), sdkConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Start the V2XService.
     */
    private void startV2XService() {
        try {
            if (!V2XSDK.getInstance().isV2XServiceStarted()) {
                V2XSDK.getInstance().startV2XService(0, null);
            }
        } catch (InvalidConfigException e) {
            e.printStackTrace();
        }
    }


    /**
     The onClickSettingsLogo method in the class starts the SettingsActivity when the settings logo is clicked.
     @param view The view associated with the click event.
     */
    public void onClickSettingsLogo(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    /**
     * This method handles events that occur on the message bus.
     *
     * @param event The event that has occurred on the message bus.
     */
    @Override
    public void onMessageBusEvent(BaseEvent event) {
        if (event.getEventType() == EventType.CAM_LIST_CHANGED) {
            onCAMListChanged((EventCamListChanged) event);
        } else if (event.getEventType() == EventType.ITS_LOCATION_LIST_CHANGED) {
            onITSLocationListChanged((EventITSLocationListChanged) event);
        } else if (event.getEventType() == EventType.V2X_CONNECTIVITY_STATE_CHANGED) {
            EventV2XConnectivityStateChanged v2XConnectivityStateChanged = (EventV2XConnectivityStateChanged) event;
            setOnUIThread(binding.v2xConnectivityStateTextView, v2XConnectivityStateChanged.getConnectivityState().toString());
        }
    }

    private void onCAMListChanged(EventCamListChanged event) {
        EventCamListChanged eclc = event;
        Timber.d("RX Event CAM_LIST_CHANGED");

        List<CAMRecord> camRecords = eclc.getList();
        if (mMapManager != null) {
            mMapManager.onCAMUpdate(camRecords);
        }
    }

    private void onITSLocationListChanged(EventITSLocationListChanged event) {
        EventITSLocationListChanged eillc = event;
        Timber.d("RX Event ITS_LOCATION_LIST_CHANGED");

        ITSLocationRecord lastRecord = null;
        List<ITSLocationRecord> itsRecords = eillc.getList();
        int nbRecords = itsRecords.size();
        if (nbRecords > 0) {
            lastRecord = itsRecords.get(0);
        }
        final ITSLocationRecord itsLocationRecord = lastRecord;
        if (itsLocationRecord != null) {
            if (mMapManager != null) {
                mMapManager.onITSUpdate(itsLocationRecord);
            } else {
                Timber.e("mMapManager is null");
            }
        }
    }

    private void setOnUIThread(TextView view, String text) {
        runOnUiThread(() -> view.setText(text));
    }

}