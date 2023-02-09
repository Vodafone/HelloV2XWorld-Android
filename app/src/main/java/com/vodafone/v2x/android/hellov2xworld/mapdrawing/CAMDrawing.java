package com.vodafone.v2x.android.hellov2xworld.mapdrawing;

import android.os.Handler;
import android.os.Looper;
import com.vodafone.v2x.sdk.android.facade.V2XSDK;
import com.vodafone.v2x.sdk.android.facade.enums.StationType;
import com.vodafone.v2x.sdk.android.facade.records.cam.CAMRecord;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import java.util.Iterator;
import java.util.List;


/**
 * Class CAMDrawing is responsible for drawing the CAM (Cooperative Awareness Message) records on the map.
 * It uses {@link IconsFactory} to get the CAM icon and displays the CAM information such as StationID, StationType, Speed, and Heading.
 * It also uses {@link ITSDrawing} to get the current map bearing to rotate the CAM icon accordingly.
 */
public class CAMDrawing {

    private static final String TAG = "CAMDrawing";
    private final IconsFactory iconsFactory;
    private final ITSDrawing itsDrawing;

    private final Marker[] mCamMarkers = new Marker[50];
    private List<CAMRecord> camRecords = null;

    /**
     * Constructor of the class CAMDrawing.
     * It creates an array of markers for displaying CAM information on the map and adds them to the map overlay.
     * It also sets the default CAM icon for each marker.
     *
     * @param map        The map view object to display CAM on the map.
     * @param itsDrawing The ITSDrawing object to get the current map bearing.
     */
    public CAMDrawing(MapView map, ITSDrawing itsDrawing) {
        iconsFactory = IconsFactory.getInstance();
        this.itsDrawing = itsDrawing;
        for (int i = 0; i < mCamMarkers.length; i++) {
            mCamMarkers[i] = new Marker(map);
            mCamMarkers[i].setIcon(iconsFactory.getCAMIcon());
            mCamMarkers[i].setImage(iconsFactory.getCAMIcon());
            map.getOverlays().add(mCamMarkers[i]);
            map.invalidate();
            mCamMarkers[i].setVisible(false);
        }
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
        Iterator<CAMRecord> it = camRecords.iterator();
        int i = 0;
        CAMRecord camRecord;
        float lon;
        float lat;
        GeoPoint latLon;
        while (it.hasNext()) {
            // Exit if camMarkers table is full
            if (i >= mCamMarkers.length) {
                break;
            }
            Marker m = mCamMarkers[i];
            camRecord = it.next();
            if (camRecord.getStationID() != V2XSDK.getInstance().getSdkConfiguration().getStationID()) {
                lon = camRecord.getLongitude();
                lat = camRecord.getLatitude();
                float heading = camRecord.getHeadingInDegree();
                latLon = new GeoPoint(lat, lon);
                String stationID = Long.toString(camRecord.getStationID());
                StationType stationType = fromValue(camRecord.getStationType());
                m.setIcon(iconsFactory.getCAMIcon());

                m.setPosition(latLon);
                String title = "CAM: StationID=" + stationID;
                String snippet = "StationType=" + stationType.toString() + "\r\nSpeed=" + camRecord.getSpeedInKmH() + "km/h  \r\nHeading=" + camRecord.getHeadingInDegree() + " degree";

                m.setTitle(title);
                m.setSnippet(snippet);
                m.setAnchor(0.5f, 0.5f);
                m.setRotation(itsDrawing.getMapBearing() - heading);
                //Timber.tag(TAG).w("visible");
                m.setVisible(true);
                i++;
            }
        }

        // hide remaining markers in the table
        for (int j = i; j < mCamMarkers.length; j++) {
            Marker m = mCamMarkers[j];
            m.setVisible(false);
        }
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
