package co.tinymap.mango;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseQuery;

/*
 *
 */

public class TinyMapItemTabActivity extends ActionBarActivity implements ActionBar.TabListener {

    private static String selectedTinyMap = "tm";
    private static String selectedTinyMapId = "id";
    static TinyMap tinyMap1;
    public static ArrayList<TinyMapItem> tinyMapItemArrayList = new ArrayList<>();
    private static boolean isNewHm = false;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinymap_item_tab);

        Log.i("TmiTabActivity", "0 selectedhmID " + selectedTinyMapId);

        //get Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String incomingId = getIntent().getStringExtra("currentSelectedTinyMapId");
            selectedTinyMapId = incomingId;
            Log.i("TmiTabActivity","0 incomingId: " + selectedTinyMapId);

            if (incomingId.equals(selectedTinyMapId)) {
                isNewHm = true;
            }
        }

        tinyMapItemArrayList = new ArrayList<>();

        Log.i("TmiTabActivity", "1 selectedhmID " + selectedTinyMapId);


//        retrieveMap();

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tinymap_item_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        //Map
        if (id == R.id.show_map) {
            Intent intent = new Intent(this, ItemMapActivity.class);
//            intent.putExtra("currentSelectedTinyMapItemId", tinyMapItem.getObjectId());
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            switch(position) {
                case 0:
                    return new TinyMapItemFragment();
                case 1:
                    return new TMIProfileFragment();

//                case 2:
//                    return new ItemFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show how many total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_tinymapitemtab_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_tinymapitemtab_section2).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tinymap_item_tab, container, false);
            return rootView;
        }
    }

    /*
     * This fragment gets ArrayList to display
     */
    public static class TinyMapItemFragment extends Fragment {
        private LayoutInflater inflater;
        private CustomParseArrayAdapter customParseArrayAdapter;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Log.i("hmitactivity", "array is empty?: " + tinyMapItemArrayList.isEmpty());
//            Log.i("hmitactivity", "custom adapter is empty?: " + customParseArrayAdapter.i);

            retrieveMap();
//            setListAdapter(customParseArrayAdapter);

            customParseArrayAdapter = new CustomParseArrayAdapter(getActivity().getBaseContext(), tinyMapItemArrayList);
//            setListAdapter(customParseItemAdapter);
//            setListShown(false);
            Log.i("hmitactivity", "array is empty?: " + tinyMapItemArrayList.isEmpty());
            Log.i("hmitactivity", "custom adapter is empty?: " + customParseArrayAdapter.isEmpty());


        }

        private void retrieveMap(){
            ParseQuery<TinyMap> query1 = ParseQuery.getQuery("TinyMap");

            if (isNewHm) {
                query1.include("hashmapItemList");

            } else {
                query1.fromLocalDatastore();
            }

            //might want to re enable this if can pre fetch. include here is too slow.
            query1.getInBackground(selectedTinyMapId, new GetCallback<TinyMap>() {
                        public void done(TinyMap object, ParseException e) {
                            if (e == null) {
                                tinyMap1 = object;
                                selectedTinyMap = tinyMap1.getTitle();
                                Log.i("hmitactivity", "2 selectedTinyMapId: " + selectedTinyMap);

                                // set activity title
                                getActivity().setTitle(selectedTinyMap);

                                // retrieve hashmap items, put into local.
                                retrieveHashmapItems();

                            } else {
                                Log.e("2 hmitactivity", "error");
                                // it's error
                                String s = "Sorry, there was an error.";
                                TextView t = (TextView) getView().findViewById(R.id.empty_item_view_text);
                                t.setText(s);
                            }
                        }
                    }
            );
        }

        private void retrieveHashmapItems(){

            //Load the related items Array
            tinyMapItemArrayList = (ArrayList<TinyMapItem>) tinyMap1.get("hashmapItemList");
//            List<HashmapItem> aList = hashmap1.getList("hashmapItemList");

            Log.i("hmitactivity","3 attempt retrieve array: " + tinyMapItemArrayList);

            if (tinyMapItemArrayList != null) {
                Log.i("hmitactivity","size: " + tinyMapItemArrayList.size());

                for (int i = 0; i < tinyMapItemArrayList.size(); i++) {
                    TinyMapItem hm = tinyMapItemArrayList.get(i);
                    Log.i("hmitactivity" , "4/ hm: " + hm + " : " + hm.getTitle());
//                    Log.i("hmitactivity" , "4/ list : " + aList.get(i).getTitle() + " : ");

                    // if hm data is not yet prefetched, fetch now. It should ideally be already
                    // fetched in the .include statement at the original hashmap query

//                    if (!hm.isDataAvailable()) {
//                        try {
//                            hm.fetch();
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                    }

//                    Log.i("hmitactivity", "4/ get item: " + hm.getTitle());

                    customParseArrayAdapter.add(hm);
                }

                //update the text on the Profile fragment
//                getActivity().getFragmentManager().findFragmentByTag("TMIProfileFragment");


            } else {

                // it's empty
                String s = "Sorry, no items found.";
                TextView t = (TextView) getView().findViewById(R.id.empty_item_view_text);
                t.setText(s);
            }


        }

        private class CustomParseArrayAdapter extends ArrayAdapter<TinyMapItem> {

            public CustomParseArrayAdapter(Context context, ArrayList<TinyMapItem> objects){
                super(context, R.layout.list_item_tinymapitem, objects);
//                this.mContext = context;
//                this.mItems = objects;
                Log.i("hmitactivity", "6 custom array adapter start");
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TinyMapItem currentHMI = tinyMapItemArrayList.get(position);
                Log.i("hmitactivity", "position: " + position);

                ViewHolder holder;
                if (convertView == null) {
                    holder = new ViewHolder();

                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.list_item_tinymapitem, parent, false);

//                    inflater = getActivity().getLayoutInflater();
                    holder.hmiTitle = (TextView) convertView.findViewById(R.id.hashmapitem_title);
                    holder.hmiDescription = (TextView) convertView.findViewById(R.id.hashmapitem_description);
                    holder.hmiAddress = (TextView) convertView.findViewById(R.id.hashmapitem_address);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                TextView titleText = holder.hmiTitle;
                TextView descriptionText = holder.hmiDescription;
                TextView addressText = holder.hmiAddress;
                titleText.setText(currentHMI.getTitle());
                descriptionText.setText(currentHMI.getDescription());
                addressText.setText(currentHMI.getAddress());

                return convertView;
            }
        }

        private static class ViewHolder {
            TextView hmiTitle;
            TextView hmiDescription;
            TextView hmiAddress;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tinymap_item_tab, container, false);

            ListView hmitemListView = (ListView) rootView.findViewById(R.id.list_view3);
            LinearLayout emptyView = (LinearLayout) rootView.findViewById(R.id.empty_item_view);
            hmitemListView.setEmptyView(emptyView);
            hmitemListView.setAdapter(customParseArrayAdapter);

            Log.i("hmitactivity", "5 " + customParseArrayAdapter.isEmpty());

//            setListAdapter(customParseArrayAdapter);
//            customParseArrayAdapter.notifyDataSetChanged();
            //onItemClickListener

            hmitemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    TinyMapItem tinyMapItem = customParseArrayAdapter.getItem(position);

                    ParseAnalytics.trackEventInBackground("Select-TinyMapItem");

                    tinyMapItem.increment("clicks");
                    tinyMapItem.saveEventually();

                    Intent intent = new Intent(view.getContext(), ItemDetailActivity.class);
                    intent.putExtra("currentSelectedTinyMapItemId", tinyMapItem.getObjectId());
//                    intent.putExtra("currentSelectedTinyMapItemId", tinyMapItem.);

                    startActivity(intent);

                }
            });

            return rootView;
        }
    }

    /*
     * This fragment to display the "profile" page of the TinyMap
     */

    public static class TMIProfileFragment extends Fragment {

        public TMIProfileFragment(){
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                    Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tmiprofile, container, false);

            final TextView tv4 = (TextView) rootView.findViewById(R.id.textView4);
            final TextView tv5 = (TextView) rootView.findViewById(R.id.textView5);

            ParseQuery<TinyMap> query = TinyMap.getQuery();
            query.fromLocalDatastore();
            query.include("author");
            query.getInBackground(selectedTinyMapId, new GetCallback<TinyMap>() {
                @Override
                public void done(TinyMap tinyMap, ParseException e) {
                    tv4.setText(tinyMap.getTitle());
                    tv5.setText(tinyMap.getAuthor().getUsername());
                }
            });


            //todo freaking need to retrieve tinyMap1 at the start of this darn Activity

            return rootView;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

//            ((TinyMapItemTabActivity)getActivity()).testMethod();



        }

        private void updateText() {
            Log.i("TmiTabActivity","updateText");
            Log.i("TmiTabActivity","updateText: " + tinyMap1.getTitle());

            TextView tv = (TextView) getView().findViewById(R.id.textView4);
            tv.setText(tinyMap1.getTitle());

        }

        //code to display some buttons, onclick listeners, name, author, stats, description?

        //1 share this
        //2 author by
        //3 bookmark



    }


}
