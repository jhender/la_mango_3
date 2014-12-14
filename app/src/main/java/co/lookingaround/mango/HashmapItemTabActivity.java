package co.lookingaround.mango;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;


public class HashmapItemTabActivity extends ActionBarActivity implements ActionBar.TabListener {

    private static String selectedHashmap = "Hashmap";
    private static String selectedHashmapId = "id";
    Hashmap hashmap;

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
        setContentView(R.layout.activity_hashmap_item_tab);

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

        selectedHashmapId = getIntent().getStringExtra("currentSelectedHashmapId");
        Toast.makeText(getApplicationContext(), "Intent reads as: " + selectedHashmapId, Toast.LENGTH_SHORT).show();
        Log.i("hmitactivity", "Intent reads as: " + selectedHashmapId);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_hashmap_item_tab, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

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
                    return new HashmapItemFragment();
                case 1:
                    return new HashmapItemFragment();
            }
            return null;        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_hashmapitemtab_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_hashmapitemtab_section2).toUpperCase(l);
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
            View rootView = inflater.inflate(R.layout.fragment_hashmap_item_tab, container, false);
            return rootView;
        }
    }

    public static class HashmapItemFragment extends Fragment {

        private ParseQueryAdapter<HashmapItem> hashmapItemListAdapter;
        private LayoutInflater inflater;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Set up the Parse query to use in the adapter
            ParseQueryAdapter.QueryFactory<HashmapItem> factory = new ParseQueryAdapter.QueryFactory<HashmapItem>() {
                public ParseQuery<HashmapItem> create() {
                    ParseQuery<HashmapItem> query = HashmapItem.getQuery();
                    query.orderByDescending("createdAt");
                    query.fromLocalDatastore();
                    return query;
                }
            };



            loadFromParse();

//            popularListAdapter = new ParseQueryAdapter<>(getActivity(), factory);
            hashmapItemListAdapter = new hashmapItemListAdapter(getActivity(), factory);

            Log.i("hashmapitemListActivity", "on activity created" + hashmapItemListAdapter);
        }

        private class hashmapItemListAdapter extends ParseQueryAdapter<HashmapItem> {

            public hashmapItemListAdapter(Context context, QueryFactory<HashmapItem> queryFactory) {
                super(context, queryFactory);
            }

            @Override
            public View getItemView(HashmapItem hashmapitem, View view, ViewGroup parent) {
                ViewHolder holder;
                if (view == null) {
                    inflater = getActivity().getLayoutInflater();
                    view = inflater.inflate(R.layout.list_item_hashmap, parent, false);
                    holder = new ViewHolder();
                    holder.hashmapTitle = (TextView) view
                            .findViewById(R.id.hashmap_title);
                    view.setTag(holder);
                } else {
                    holder = (ViewHolder) view.getTag();
                }
                TextView hashmapTitle = holder.hashmapTitle;
                hashmapTitle.setText(hashmapitem.getTitle());
                if (hashmapitem.isDraft()) {
                    hashmapTitle.setTypeface(null, Typeface.ITALIC);
                } else {
                    hashmapTitle.setTypeface(null, Typeface.NORMAL);
                }
                return view;
            }
        }

        private static class ViewHolder {
            TextView hashmapTitle;
        }

        private void loadFromParse() {
            ParseQuery<HashmapItem> query = HashmapItem.getQuery();
//            query.whereEqualTo("isDraft", false);
            query.findInBackground(new FindCallback<HashmapItem>() {
                public void done(List<HashmapItem> hashmapitems, ParseException e) {
                    if (e == null) {
                        ParseObject.pinAllInBackground(hashmapitems,
                                new SaveCallback() {
                                    public void done(ParseException e) {
                                        if (e == null) {
//                                            if (!isFinishing()) {
                                            hashmapItemListAdapter.loadObjects();
                                            Log.i(
                                                    "hmitemListactivity", "retrieved, loadobjects"
                                            );

                                            Log.i("hmitemListactivity", "after loadobjects" + hashmapItemListAdapter);

//                                            }
                                        } else {
                                            Log.i("hmitemListactivity",
                                                    "Error pinning hashmaps: " + e.getMessage());
                                        }
                                    }
                                });
                    } else {
                        Log.i("popularListActivity",
                                "loadFromParse: Error finding pinned hashmaps: "
                                        + e.getMessage());
                    }
                }
            });
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_tab_hashmapitemlist, container, false);

            ListView hmitemListView = (ListView) rootView.findViewById(R.id.hashmap_item_list_view);
            LinearLayout emptyView = (LinearLayout) rootView.findViewById(R.id.empty_item_view);
            hmitemListView.setEmptyView(emptyView);
            hmitemListView.setAdapter(hashmapItemListAdapter);

            hmitemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    HashmapItem hashmapitem = hashmapItemListAdapter.getItem(position);
//                    currentSelectedHashmapTitle = hashmapitem.getTitle();
//                    Toast.makeText(getActivity(), "Item clicked " + currentSelectedHashmapTitle, Toast.LENGTH_SHORT).show();

                }
            });


            //Load the related items
//            ArrayList<ParseObject> hashmapItemArrayList = (ArrayList<ParseObject>) currentSelectedHashmap.get("hashmapItemList");
//            Log.i("MainTabActivity","attempt retrieve array" + hashmapItemArrayList);
//
//            if (hashmapItemArrayList != null) {
//                HashmapItem hmitem1 = (HashmapItem) hashmapItemArrayList.get(0);
//                Log.i("MainTabActivity", "get first item " + hmitem1.getTitle());
//            }

            Log.i("hashmapitemListActivity", "return view" + hashmapItemListAdapter);
            return rootView;


        }
    }

}
