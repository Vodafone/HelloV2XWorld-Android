package com.vodafone.v2x.android.hellov2xworld.mapdrawing;

import android.os.Handler;
import android.os.Looper;

import com.vodafone.v2x.sdk.android.AndroidV2XSDK;
import com.vodafone.v2xsdk4javav2.facade.models.GnssLocation;
import com.vodafone.v2xsdk4javav2.facade.records.ITSLocationRecord;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import timber.log.Timber;

/**
 * The ITSDrawing class is responsible for drawing the ITS (Intelligent Transport System) on the map.
 * It creates a marker for the ITS location and sets the title, snippet and image for the marker.
 * It also updates the marker position and angle on the map based on the ITS location information.
 */
public class ITSDrawing {

    private static final String TAG = "ITSDrawing";
    private final Marker mITSLocationMarker;
    private final MapView mMap;
    private final IconsFactory iconsFactory;
    private ITSLocationRecord lastRecord = null;
    private float mapBearing;

    /**
     * Creates an instance of ITSDrawing class by setting the ITS location marker on the map view.
     *
     * @param map the MapView to be used to display the ITS marker.
     */
    public ITSDrawing(MapView map) {
        mMap = map;
        iconsFactory = IconsFactory.getInstance();
        GeoPoint startPoint = new GeoPoint(0.0, 0.0);
        mITSLocationMarker = new Marker(mMap);
        mITSLocationMarker.setIcon(iconsFactory.getITSIcon());
        mITSLocationMarker.setImage(iconsFactory.getITSIcon());
        mITSLocationMarker.setTitle("ITS Location");
        mMap.getOverlays().add(mITSLocationMarker);
        mMap.invalidate();
        mITSLocationMarker.setPosition(startPoint);
        mITSLocationMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
    }

    /**
     * Draws the ITS location marker on the map based on the ITSLocationRecord received.
     *
     * @param itsLocationRecord the ITSLocationRecord to be used to update the ITS location marker.
     */
    public void draw(ITSLocationRecord itsLocationRecord) {
        if (itsLocationRecord != null) {
            lastRecord = itsLocationRecord;
            new Handler(Looper.getMainLooper()).post(ITSDrawing.this::drawInUIThread);
        }
    }

    private void drawInUIThread() {
        if (lastRecord != null) {
            Timber.tag(TAG).d("onITSUpdate");
            GnssLocation location = lastRecord.getLocation();
            float lat = (float) location.getLatitude();
            float lon = (float) location.getLongitude();
            GeoPoint latitudeLongitude = new GeoPoint(lat, lon);

            float itsBearing = location.getBearingInDegree() != null ?
                    location.getBearingInDegree() : 0.0f;
            float speedKmh = location.getSpeedInKmPerHour();
            mapBearing = itsBearing;   // forcing mapBearing to 0 if speed <=1km/h has been removed by JLD
            Timber.tag(TAG).d("Map bearing %s", mapBearing);
            String title = "ITS: StationID=" + AndroidV2XSDK.getInstance().getStationId();
            String snippet = "StationType=" + AndroidV2XSDK.getInstance().getStationType() + "\r\nSpeed:" + speedKmh + " km/h \r\nHeading:" + itsBearing + " degree";
            mITSLocationMarker.setTitle(title);
            mITSLocationMarker.setSnippet(snippet);
            mITSLocationMarker.setPosition(latitudeLongitude);
            mITSLocationMarker.setAlpha(0.8f);
            mITSLocationMarker.setVisible(true);
            mITSLocationMarker.setRotation(0);
            mITSLocationMarker.setAnchor(0.5f, 0.5f);
            mMap.getController().animateTo(latitudeLongitude, null, null, -mapBearing);
        } else {
            mITSLocationMarker.setIcon(iconsFactory.getITSUnavailableIcon());
        }
    }

    /**
     * Returns the current bearing of the map.
     *
     * @return the current map bearing.
     */
    public float getMapBearing() {
        return mapBearing;
    }

}
