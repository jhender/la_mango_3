package co.tinymap.mango;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;


public class SecretPanelActivity extends ActionBarActivity {

    Button button2;
    Button button3;
    Button button4;
    TextView textView1;
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_panel);

        textView1 = (TextView)findViewById(R.id.textView2);
        textView1.setText("Current User: " + ParseUser.getCurrentUser().toString());

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View v)
            {
                createhashmapitem();
            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View v)
            {
                linkHashMap();
            }
        });

        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View v)
            {
                addHashMap();
            }
        });

        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View v)
            {
                addCustomHashMap();
            }
        });
    }


    private void createhashmapitem() {
        TinyMapItem hmItem;
            hmItem = new TinyMapItem();
            hmItem.setUuidString();
            hmItem.setTitle("Cafe 5");
            hmItem.setAddress("10 Changi Road, Singapore");
            hmItem.setDescription("Place with great coffee and cakes.");
        hmItem.saveEventually();
        Log.i("secretActivity", "createHashmapItem");
    }

    private void addHashMap() {
        TinyMap hm;
        hm = new TinyMap();
        hm.setUuidString();
        hm.setTitle("#FaveShops");
        hm.saveEventually();
        Log.i("SecretActivity", "addHashmap");
    }

    private void linkHashMap() {

        //Get Main Object

        ParseQuery<TinyMap> query = ParseQuery.getQuery("TinyMap");
        query.getInBackground("IMrgX3KPxo", new GetCallback<TinyMap>() {
                    public void done(final TinyMap tinyMap1, ParseException e) {
                        if (e == null) {
                            Log.i("retrieve hashmap", "retrieved");

                            final ArrayList<TinyMapItem> tinyMapItemsArray = (ArrayList<TinyMapItem>) tinyMap1.get("hashmapItemList");

                            //Get subobjects
                            ParseQuery<TinyMapItem> query2 = ParseQuery.getQuery("TinyMapItem");
                            query2.getInBackground("AXfhaoEkm0", new GetCallback<TinyMapItem>() {
                                public void done(TinyMapItem tinyMapItem, ParseException e) {
                                    if (e == null) {
                                        Log.i("retrieve hashmapitem", "retrieved");

                                        //Build array
                                        tinyMapItemsArray.add(tinyMapItem);

                                        //Link to Main Object
                                        tinyMap1.put("hashmapItemList", tinyMapItemsArray);
                                        tinyMap1.saveEventually();
                                        Log.i("retrieve hashmap", "end");

                                    } else {
                                        Log.i("retrieve hashmapitem", "failed");
                                    }
                                }
                            });





                        } else {
                            Log.i("retrieve hashmap", "failed");
                        }
                    }
                });





    }

    private void addCustomHashMap() {
        Intent intent = new Intent(this, NewTinyMapActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_secret_panel, menu);
        return true;
    }


}

