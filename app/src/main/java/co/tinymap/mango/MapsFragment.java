package co.tinymap.mango;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends SupportMapFragment {

    final LatLng mTestLatLng = new LatLng(40.782865,-73.965355);
    private static GoogleMap mMap;

    public MapsFragment() {
        super();

    }

    // newInstance is called from the Tab Activity.
    public static MapsFragment newInstance(){
        MapsFragment frag = new MapsFragment();
//        frag.mTestLatLng = position;
        Log.i("mapsfragment", "new instance");

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
        View v = super.onCreateView(arg0, arg1, arg2);
        Log.i("mapsfragment", "initView");

        initMap();

        return v;
    }

    private void initMap(){
        UiSettings settings = getMap().getUiSettings();
        settings.setAllGesturesEnabled(false);
        settings.setMyLocationButtonEnabled(true);

        Log.i("mapsfragment", "initMap");

        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.782865,-73.965355),16));
        getMap().addMarker(new MarkerOptions().position(mTestLatLng).title("Here lies teh treasure."));
    }
}


//        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("My Home").snippet("Home Address"));



//
//    private static View view;
//    /**
//     * Note that this may be null if the Google Play services APK is not
//     * available.
//     */
//
//    private static GoogleMap mMap;
//    private static Double latitude, longitude;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        if (container == null) {
//            return null;
//        }
//        view = (RelativeLayout) inflater.inflate(R.layout.activity_maps, container, false);
//        // Passing harcoded values for latitude & longitude. Please change as per your need. This is just used to drop a Marker on the Map
//        latitude = 26.78;
//        longitude = 72.56;
//
//        setUpMapIfNeeded(); // For setting up the MapFragment
//
//        return view;
//    }
//
//    /***** Sets up the map if it is possible to do so *****/
//    public static void setUpMapIfNeeded() {
//        // Do a null check to confirm that we have not already instantiated the map.
//        if (mMap == null) {
////            // Try to obtain the map from the SupportMapFragment.
//            mMap = ((SupportMapFragment) HashmapItemTabActivity.fragmentManager
//                    .findFragmentById(R.id.location_map)).getMap();
////            // Check if we were successful in obtaining the map.
//            if (mMap != null)
//                setUpMap();
//        }
//    }
//
//    /**
//     * This is where we can add markers or lines, add listeners or move the
//     * camera.
//     * <p>
//     * This should only be called once and when we are sure that {@link #mMap}
//     * is not null.
//     */
//    private static void setUpMap() {
//        // For showing a move to my loction button
//        mMap.setMyLocationEnabled(true);
//        // For dropping a marker at a point on the Map
//        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("My Home").snippet("Home Address"));
//        // For zooming automatically to the Dropped PIN Location
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,
//                longitude), 12.0f));
//    }
//
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        if (mMap != null)
//            setUpMap();
//
////        if (mMap == null) {
////            // Try to obtain the map from the SupportMapFragment.
////            mMap = ((SupportMapFragment) MainActivity.fragmentManager
////                    .findFragmentById(R.id.location_map)).getMap();
////            // Check if we were successful in obtaining the map.
////            if (mMap != null)
////                setUpMap();
////        }
//    }
//
//    /**** The mapfragment's id must be removed from the FragmentManager
//     **** or else if the same it is passed on the next time then
//     **** app will crash ****/
////    @Override
////    public void onDestroyView() {
////        super.onDestroyView();
////        if (mMap != null) {
////            MainActivity.fragmentManager.beginTransaction()
////                    .remove(MainActivity.fragmentManager.findFragmentById(R.id.location_map)).commit();
////            mMap = null;
////        }
////    }
//}
////}
////
////    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_maps);
////        setUpMapIfNeeded();
////    }
////
////    @Override
////    protected void onResume() {
////        super.onResume();
////        setUpMapIfNeeded();
////    }
//
//    /**
//     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
//     * installed) and the map has not already been instantiated.. This will ensure that we only ever
//     * call {@link #setUpMap()} once when {@link #mMap} is not null.
//     * <p/>
//     * If it isn't installed {@link SupportMapFragment} (and
//     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
//     * install/update the Google Play services APK on their device.
//     * <p/>
//     * A user can return to this FragmentActivity after following the prompt and correctly
//     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
//     * have been completely destroyed during this process (it is likely that it would only be
//     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
//     * method in {@link #onResume()} to guarantee that it will be called.
//     */
//    private void setUpMapIfNeeded() {
//        // Do a null check to confirm that we have not already instantiated the map.
//        if (mMap == null) {
//            // Try to obtain the map from the SupportMapFragment.
//            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
//                    .getMap();
//            // Check if we were successful in obtaining the map.
//            if (mMap != null) {
//                setUpMap();
//            }
//        }
//    }
//
//    /**
//     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
//     * just add a marker near Africa.
//     * <p/>
//     * This should only be called once and when we are sure that {@link #mMap} is not null.
//     */
//    private void setUpMap() {
//        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
//    }
//}
