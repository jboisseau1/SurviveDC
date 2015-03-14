package com.example.jacob.survivedc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    private NavigationDrawerFragment mNavigationDrawerFragment;
    private GoogleMap mMap;



    private CharSequence mTitle;
        //all of this is the same -- jacob
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));



    }
        //starts modifications here -- jacob
    @Override
    public void onNavigationDrawerItemSelected(int position) {

            //i think it starts at zero(0) but breaks the app -- jacob
        switch (position){
            case 1:
                    //Home fragment-> this makes the first activity named 'Home'
                    //these are based off of the original code ie. the 'Placeholder' class -- jacob
                FragmentManager fragmentManagerHome = getSupportFragmentManager();
                fragmentManagerHome.beginTransaction()

                        .replace(R.id.container, HomeFragment.newInstance(position))
                        .commit();
                break;


            case 2:


        // inserts the new content by replacing fragments
                //Map fragment -> replaces the current fragment with the map fragment -- jacob
        FragmentManager fragmentManagerMap = getSupportFragmentManager();
        fragmentManagerMap.beginTransaction()

                .replace(R.id.container, MapFragment.newInstance(position))
                .commit();
                break;

            //the following is a test and should be left commented out. needs to be debugged (the problem with the numbers being offset)
            //might want to try removing the '+1' added to position i will try this as well -- jacob
                //fitness
//            default:
//
//                FragmentManager fragmentManagerFitness = getSupportFragmentManager();
//                fragmentManagerFitness.beginTransaction()
//
//                        .replace(R.id.container, FitnessFragment.newInstance(1))
//                        .commit();
//                break;
        }

    }



        //runs when pressed in drawer
        //this gives the listview names to display. more may be added by making a new case -- jacob
    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }



        //these methods(3) are stock they seem to do nothing. don't need to be changed -- jacob

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {http://geck1.blogspot.com/2014/05/google-map-in-navigation-drawer-fragment.html
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
        //the following three methods are the backbone of the app.
        //based on the 'Placeholder' class they switch the current fragment with a new one that has been selected.
        //i will go into detail on how they work in the method -- jacob

    public static class MapFragment extends Fragment {
            //this gives the fragment a name so the app knows which fragment is open -- jacob
        private static final String ARG_SECTION_NUMBER = "Map";

            //called when the fragment is being switched too.
            // creates a new instance of the fragment -- jacob
        public static MapFragment newInstance(int sectionNumber) {
            MapFragment fragment = new MapFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
            //once the fragment is switched this method is called to start the activity fragment -- jacob
        public MapFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_map, container, false);
            return rootView;                        //here is where each of the fragments knows which xml file to use -- jacob
        }
            //not sure what this does but i'd leave it in -- jacob
        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
        //the following(2) are the same as the one above just calling different xml files -- jacob

    public static class HomeFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "Home";


        public static HomeFragment newInstance(int sectionNumber) {
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public HomeFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

   /* public static class FitnessFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "Fitness";


        public static FitnessFragment newInstance(int sectionNumber) {
            FitnessFragment fragment = new FitnessFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public FitnessFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_fitness, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
    */
        //Pasted this over into it's own class --Fahad
        //this isnt used but should be kept because it has info we will need later on -- jacob
    public void setUpMap() {
        //adds maker named DC and the lat and lng of the location --Jacob
        //Changed marker to Dupont Circle which is the starting point of the game
        mMap.addMarker(new MarkerOptions().position(new LatLng(38.909669, -77.043385)).title("Starting Point"));

        //sets the lat and lng
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(38.909669, -77.043385));
        CameraUpdate zoom= CameraUpdateFactory.zoomTo(13);

        //set the scope of the map to DC
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);

        //lets the user see where they are with the blue dot
        mMap.setMyLocationEnabled(true);
    }
}
/**
 the app works great switching between fragments BUT it is offset by one button.

            ie. if 'Home' was selected it would display 'Map'

                i think the order starts at zero but the app never launches when the switch statement starts at zero.

                    i taged my comments with '-- jacob'

 */
