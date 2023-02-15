package com.vodafone.v2x.android.hellov2xworld.view.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.processphoenix.ProcessPhoenix;
import com.vodafone.v2x.android.hellov2xworld.R;
import com.vodafone.v2x.android.hellov2xworld.databinding.ActivitySettingsBinding;
import com.vodafone.v2x.android.hellov2xworld.utils.JavaMapUtils;
import com.vodafone.v2x.android.hellov2xworld.utils.Parameters;
import com.vodafone.v2x.sdk.android.facade.enums.StationType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * This class represents the activity for settings configuration for the app.
 * It provides an interface for setting up the station type, application ID, and application token.
 */
public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;
    private Parameters parameters;
    private Map<String, StationType> stationTypeMap;
    private ArrayAdapter<String> stationTypeSpinnerAdapter;
    private String preSavedStationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        parameters = Parameters.getInstance(this);
        setContentView(binding.getRoot());
        initTypeMap();
        setupStationTypeSpinner();
        setupPreloadedValues();
        setupButtons();
    }

    private void initTypeMap() {
        stationTypeMap = new HashMap<>();
        stationTypeMap.put(getString(R.string.pedestrian), StationType.PEDESTRIAN);
        stationTypeMap.put(getString(R.string.cyclist), StationType.CYCLIST);
        stationTypeMap.put(getString(R.string.moped), StationType.MOPED);
        stationTypeMap.put(getString(R.string.motorcycle), StationType.MOTORCYCLE);
        stationTypeMap.put(getString(R.string.passenger_car), StationType.PASSENGER_CAR);
        stationTypeMap.put(getString(R.string.bus), StationType.BUS);
        stationTypeMap.put(getString(R.string.light_truck), StationType.LIGHT_TRUCK);
        stationTypeMap.put(getString(R.string.heavy_truck), StationType.HEAVY_TRUCK);
        stationTypeMap.put(getString(R.string.trailer), StationType.TRAILER);
        stationTypeMap.put(getString(R.string.special_vehicles), StationType.SPECIAL_VEHICLES);
        stationTypeMap.put(getString(R.string.tram), StationType.TRAM);
        stationTypeMap.put(getString(R.string.road_side_units), StationType.ROAD_SIDE_UNITS);
        stationTypeMap.put(getString(R.string.animal), StationType.ANIMAL);
        stationTypeMap.put(getString(R.string.unknown), StationType.UNKNOWN);
    }

    private void setupStationTypeSpinner() {
        stationTypeSpinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, new ArrayList<>(stationTypeMap.keySet()));
        binding.spStationType.setAdapter(stationTypeSpinnerAdapter);
    }

    private void setupPreloadedValues() {
        StationType stationType = parameters.getStationType();
        preSavedStationType = JavaMapUtils.getKeyByValue(stationTypeMap, stationType);
        if (preSavedStationType == null) {
            preSavedStationType = getString(R.string.passenger_car);
        }
        int preSavedStationTypePosition = stationTypeSpinnerAdapter.getPosition(preSavedStationType);
        binding.spStationType.post(() -> binding.spStationType.setSelection(preSavedStationTypePosition));

        binding.tvApplicationIDValue.setText(parameters.getApplicationID());

        binding.tvApplicationTokenValue.setText(parameters.getApplicationToken());
    }

    private void setupButtons() {
        binding.btCancel.setOnClickListener(v -> SettingsActivity.this.finish());

        binding.btApply.setOnClickListener(v -> {
            String inputStationType = binding.spStationType.getSelectedItem().toString().trim();
            if (!preSavedStationType.equals(inputStationType)) {
                boolean isStationTypeSet = parameters.setStationType(Objects.requireNonNull(stationTypeMap.get(inputStationType)));
                if (isStationTypeSet) {
                    ProcessPhoenix.triggerRebirth(SettingsActivity.this);
                }
            } else {
                finish();
            }
        });

    }

}