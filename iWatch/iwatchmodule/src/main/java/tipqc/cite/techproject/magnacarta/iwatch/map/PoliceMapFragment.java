package tipqc.cite.techproject.magnacarta.iwatch.map;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment;
import tipqc.cite.techproject.magnacarta.BuildConfig;
import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.ApplicationiWatch;
import tipqc.cite.techproject.magnacarta.libraries.ui.map.MCXSupportMapFragment;

public class PoliceMapFragment extends Fragment implements GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener, LocationListener,
        MCXSupportMapFragment.OnMapCreatedListener {


    static final LatLng QCPD9 = new LatLng(14.63288, 121.059328);
    static final LatLng PAPS =  new LatLng(14.636786, 121.068665);
    static final LatLng PS8 =  new LatLng(14.6226, 121.067978);
    static final LatLng RBS =  new LatLng(14.630469, 121.082928);
    static final LatLng KPS =  new LatLng(14.629779, 121.045834);
    static final LatLng UPDPSFD =  new LatLng(14.651998, 121.06544);
    static final LatLng PNPH=  new LatLng(14.609197, 121.05323);
    static final LatLng BPS=  new LatLng(14.609197, 121.05323);
    static final LatLng PCW = new LatLng(14.593183, 120.992684);

    private GoogleMap mMap;

    private LocationClient mLocationClient;

    private LocationRequest mRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        if (BuildConfig.DEBUG) {
            Log.d(ApplicationiWatch.LOG_TAG, "simpleMapFragment.onCreate");
        }

        mLocationClient = new LocationClient(this.getActivity().getApplicationContext(), this, this);
        mRequest = LocationRequest.create()
                .setInterval(20000)
                .setFastestInterval(16)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Log.d(ApplicationiWatch.LOG_TAG, "simpleMapFragment.onCreateView");
        }
        View view = container.findViewById(R.id.simplemap);
        view = inflater.inflate(R.layout.police_map_fragment, container, false);

      /* if(mMap!=null){


      }*/
        return view ;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MCXSupportMapFragment mapFragment = getMapFragment();
        if (BuildConfig.DEBUG) {
            Log.d(ApplicationiWatch.LOG_TAG, "simpleMapFragment.onViewCreated - mapFragment = " + mapFragment);
        }
        if ( mapFragment == null ) {
            mapFragment = new MCXSupportMapFragment();
            mapFragment.setOnMapCreatedListener(this);
            mapFragment.setRetainInstance(true);
            getChildFragmentManager().beginTransaction().replace(R.id.simplemap, mapFragment, null).commit();



        }
        else {
            this.mMap = mapFragment.getMap();
        }
    }

    private MCXSupportMapFragment getMapFragment() {
        return (MCXSupportMapFragment) getChildFragmentManager().findFragmentById(R.id.simplemap);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLocationClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        if ( mLocationClient.isConnected()) {
            mLocationClient.removeLocationUpdates(this);
            mLocationClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationClient.requestLocationUpdates(mRequest, this);
    }

    @Override
    public void onDisconnected() {
    }

    @Override
    public void onLocationChanged(Location location) {
        if (BuildConfig.DEBUG) {
            Log.d(ApplicationiWatch.LOG_TAG, "simpleMapFragment.onLocationChanged");
        }
        if ( mMap != null ) {

           /* LatLng loc = new LatLng(14.593183, 120.992684);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(loc, 15);
            mMap.animateCamera(cameraUpdate);*/
           LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(loc, 15);
            mMap.animateCamera(cameraUpdate);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Crouton.makeText(
                this.getActivity(),
                getString(R.string.error_connectionfailed),
                Style.ALERT).show();
    }

    @Override
    public void onMapCreated(GoogleMap googleMap) {
        if (BuildConfig.DEBUG) {
            Log.d(ApplicationiWatch.LOG_TAG, "simpleMapFragment.onMapCreated - map: " + googleMap);
        }
        if ( googleMap != null ) {
            mMap = googleMap;
            //mMap.setMyLocationEnabled(true);

            Marker qcpd = mMap.addMarker(new MarkerOptions().position(QCPD9)
                    .title("Quezon City Police District Police Station 9")
                    .snippet("Quirino 2-A Quezon City\n" +
                            "0915 258 1066")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));

            Marker pap2 = mMap.addMarker(new MarkerOptions().position(PAPS)
                    .title("Plaza Avelino Police Station")
                    .snippet(" Plaza Avelino, Manila City, Metro Manila\n" +
                            "Tel: 63 2 716 6526")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));

            Marker pps8 = mMap.addMarker(new MarkerOptions().position(PS8)
                    .title("MANILA POLICE STATION 8 – STA. MESA")
                    .snippet("Manila Police Station 8 Contact Numbers")
                    .snippet("+632 713-0522 (Office of the Station Commander), +63 929-889-5996")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));

            Marker rbs = mMap.addMarker(new MarkerOptions().position(RBS)
                    .title("River Banks Police Station")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));

            Marker kp = mMap.addMarker(new MarkerOptions().position(KPS)
                    .title("Kamuning Police Station")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));

            Marker upsf = mMap.addMarker(new MarkerOptions().position(UPDPSFD)
                    .title("UP Diliman Police Station and Fire Department")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));

            Marker pnph = mMap.addMarker(new MarkerOptions().position(PNPH)
                    .title("Philippine National Police Headquartersn")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));

            Marker rbss = mMap.addMarker(new MarkerOptions().position(BPS)
                    .title("Baler Police Station")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));

            Marker pcw = mMap.addMarker(new MarkerOptions().position(PCW)
                    .title("Philippine Commmission Women")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PCW, 15));

            // Zoom in, animating the camera.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.police_map_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.simplemap_menu_info:
                SimpleDialogFragment.createBuilder(this.getActivity(), this.getActivity().getSupportFragmentManager())
                        .setMessage(Html.fromHtml(getString(R.string.simplemap_info_details)))
                        .setTitle(R.string.simplemap_info_title)
                        .show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}