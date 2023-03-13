package com.vodafone.v2x.android.hellov2xworld.mapdrawing;

import android.os.Handler;
import android.os.Looper;
import com.vodafone.v2x.sdk.android.facade.V2XSDK;
import com.vodafone.v2x.sdk.android.facade.enums.StationType;
import com.vodafone.v2x.sdk.android.facade.records.cam.CAMRecord;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import timber.log.Timber;


/**
 * Class CAMDrawing is responsible for drawing the CAM (Cooperative Awareness Message) records on the map.
 * It uses {@link IconsFactory} to get the CAM icon and displays the CAM information such as StationID, StationType, Speed, and Heading.
 * It also uses {@link ITSDrawing} to get the current map bearing to rotate the CAM icon accordingly.
 */
public class CAMDrawing {

    private static final String TAG = "CAMDrawing";
    private final MapView mapView;
    private final IconsFactory iconsFactory;
    private final ITSDrawing itsDrawing;
    private final HashMap<Long, Marker> listCAMMarkers = new HashMap<>();

    private static final int maxNumberOfCAM = 50;
    private List<CAMRecord> camRecords = null;

//    /**
//     * Constructor of the class CAMDrawing.
//     * It creates an array of markers for displaying CAM information on the map and adds them to the map overlay.
//     * It also sets the default CAM icon for each marker.
//     *
//     * @param map        The map view object to display CAM on the map.
//     * @param itsDrawing The ITSDrawing object to get the current map bearing.
//     */
    public CAMDrawing(MapView mapView, ITSDrawing itsDrawing) {
        iconsFactory = IconsFactory.getInstance();
        this.itsDrawing = itsDrawing;
        this.mapView = mapView;
    }

    /**
     * The method draw is responsible for drawing CAM information on the map.
     * It filters the CAM records and updates the marker position, title, snippet, rotation, and visibility based on the CAM information.
     * The method also handles the visibility of the remaining markers in the array that are not used.
     *
     * @param records The list of CAMRecord objects to be displayed on the map.
     */
    public void draw(List<CAMRecord> records) {
        if (records != null) {
            camRecords = records;

            new Handler(Looper.getMainLooper()).post(CAMDrawing.this::drawInUIThread);
        }
    }

    private void drawInUIThread() {
        for (int i=0; i<camRecords.size(); i++) {
            if (listCAMMarkers.containsKey(camRecords.get(i).getStationID())) {
                updateMarkerInfo(Objects.requireNonNull(listCAMMarkers.get(camRecords.get(i).getStationID())), camRecords.get(i));
            } else {
                if (listCAMMarkers.size() <= maxNumberOfCAM) {
                    createMarker(camRecords.get(i));
                }
            }
        }
    }

    private void createMarker(CAMRecord camRecord) {
        Timber.d("createMarker");

        String title = "CAM: StationID=" + camRecord.getStationID();
        Marker marker = new Marker(mapView);
        marker.setTitle(title);
        marker.setIcon(iconsFactory.getCAMIcon());
        marker.setImage(iconsFactory.getCAMIcon());
        updateMarkerInfo(marker, camRecord);
        listCAMMarkers.put(camRecord.getStationID(), marker);
        mapView.getOverlays().add(marker);
        marker.setVisible(true);
    }

    private void updateMarkerInfo(Marker marker, CAMRecord camRecord) {
        Timber.d("updateMarker");
        double lon = camRecord.getLongitude();
        double lat = camRecord.getLatitude();
        float heading = camRecord.getHeadingInDegree();
        GeoPoint latLon = new GeoPoint(lat, lon);
        StationType stationType = fromValue(camRecord.getStationType());
        marker.setPosition(latLon);
        String snippet = "StationType=" + stationType.toString() + "\r\nSpeed=" + camRecord.getSpeedInKmH() + "km/h  \r\nHeading=" + camRecord.getHeadingInDegree() + " degree";
        marker.setSnippet(snippet);
        marker.setAnchor(0.5f, 0.5f);
        marker.setRotation(itsDrawing.getMapBearing() - heading);
    }

    private StationType fromValue(int value) {
        for (StationType type : StationType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return StationType.UNKNOWN;
    }
}
