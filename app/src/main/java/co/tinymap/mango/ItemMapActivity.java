package co.tinymap.mango;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ItemMapActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_map);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        LatLng latLng1 = new LatLng(0,0);
        //add markers from array
        for (int i=0; i < TinyMapItemTabActivity.tinyMapItemArrayList.size(); i++){
            TinyMapItem tinyMapItem1 = TinyMapItemTabActivity.tinyMapItemArrayList.get(i);

            //check if there is a geopoint on this item
            if (tinyMapItem1.getParseGeoPoint("geopoint") != null) {

                latLng1 = new LatLng(
                        tinyMapItem1.getParseGeoPoint("geopoint").getLatitude(),
                        tinyMapItem1.getParseGeoPoint("geopoint").getLongitude()
                );

                //add marker to map
                mMap.addMarker(new MarkerOptions().position(latLng1).title(tinyMapItem1.getTitle()));
                Log.i("itemmapactivity", "added " + i);

            } else {
                Log.i("itemmapactivity", "null");
            }

        }
        // move map to last marker
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 14));
    }
}
