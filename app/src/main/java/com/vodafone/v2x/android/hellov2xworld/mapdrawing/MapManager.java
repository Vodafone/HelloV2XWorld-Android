package com.vodafone.v2x.android.hellov2xworld.mapdrawing;

import static android.content.Context.LOCATION_SERVICE;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import androidx.core.app.ActivityCompat;
import com.vodafone.v2x.sdk.android.facade.records.ITSLocationRecord;
import com.vodafone.v2x.sdk.android.facade.records.cam.CAMRecord;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import java.util.List;

/**
 The MapManager class is responsible for managing the map and initializing settings for the map and map camera.

 It contains instances of the ITSDrawing and CAMDrawing classes for drawing ITS and CAM locations on the map.
 */
public class MapManager {
    /**

     Instance of the ITSDrawing class for drawing ITS locations on the map
     */
    private final ITSDrawing mITSDrawing;
    /**

     Instance of the CAMDrawing class for drawing CAM locations on the map
     */
    private final CAMDrawing mCAMDrawing;
    /**

     Context of the application
     */
    private final Context mContext;

    /**
     Constructor that initializes the MapManager by initializing the map and camera settings and creating instances of the ITSDrawing and CAMDrawing classes.
     @param mapView The MapView to be managed
     @param context The Context of the application
     */
    public MapManager(MapView mapView, Context context) {
        mContext = context;
        initMapAndCamera(mapView);
        mITSDrawing = new ITSDrawing(mapView);
        mCAMDrawing = new CAMDrawing(mapView, mITSDrawing);
    }

    /**
     Initializes the settings for the map and map camera, including visibility, tile source,
     enabling, multi-touch controls, zoom level, and setting the center of the map to the last known GPS location.
     If the GPS location is not available, it defaults to a predefined location.
     @param mapView The MapView to be initialized
     */
    private void initMapAndCamera(MapView mapView) {
        mapView.setVisibility(MapView.VISIBLE);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setEnabled(true);
        mapView.setMultiTouchControls(true);
        IMapController mapController = mapView.getController();
        mapController.setZoom(16D);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) mContext
                .getSystemService(LOCATION_SERVICE);
        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        GeoPoint currentLocation;
        if (loc != null) {
            currentLocation = new GeoPoint(loc.getLatitude(), loc.getLongitude());
        } else {
            currentLocation = new GeoPoint(45.83695542096091f, 8.79079528247762f);
        }
        mapController.setCenter(currentLocation);
    }

    /**
     * The entry point to draw an ITS location.
     *
     * @param itsLocationRecord The ITSLocationRecord instance to be drawn on the map.
     */
    public void onITSUpdate(ITSLocationRecord itsLocationRecord) {
        if (mITSDrawing != null) {
            mITSDrawing.draw(itsLocationRecord);
        }
    }

    /**
     * The entry point to draw CAM locations.
     *
     * @param camRecords The list of CAMRecord instances to be drawn on the map.
     */
    public void onCAMUpdate(List<CAMRecord> camRecords) {
        if (camRecords != null) {
            mCAMDrawing.draw(camRecords);
        }
    }
}
