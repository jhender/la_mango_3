package co.lookingaround.mango;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;


public class SecretPanelActivity extends ActionBarActivity {

    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_panel);

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
    }


    private void createhashmapitem() {
        //TODO temporary code
        HashmapItem hmItem;
            hmItem = new HashmapItem();
            hmItem.setUuidString();
            hmItem.setTitle("Cafe 5");
            hmItem.setAddress("10 Changi Road, Singapore");
            hmItem.setDescription("Place with great coffee and cakes.");
        hmItem.saveEventually();
        Log.i("secretActivity", "createHashmapItem");
    }

    private void addHashMap() {
        //TODO temporary code
        Hashmap hm;
        hm = new Hashmap();
        hm.setUuidString();
        hm.setTitle("#FaveShops");
        hm.saveEventually();
        Log.i("SecretActivity", "addHashmap");
    }

    private void linkHashMap() {
        //TODO temporary code

        //Get Main Object
        final Hashmap hashmap1;

        ParseQuery<Hashmap> query = ParseQuery.getQuery("Hashmap");
        query.getInBackground("IMrgX3KPxo", new GetCallback<Hashmap>() {
                    public void done(final Hashmap hashmap1, ParseException e) {
                        if (e == null) {
//                            objectWasRetrievedSuccessfully(object);

//                            hashmap1 = object;
                            Log.i("retrieve hashmap", "retrieved");


                            //Get subobjects
                            HashmapItem hashmapItem;
                            ParseQuery<HashmapItem> query2 = ParseQuery.getQuery("HashmapItem");
                            query2.getInBackground("AXfhaoEkm0", new GetCallback<HashmapItem>() {
                                public void done(HashmapItem hashmapItem, ParseException e) {
                                    if (e == null) {
                                        Log.i("retrieve hashmapitem", "retrieved");

                                        //Build array
                                        ArrayList<ParseObject> HashmapItemsArray = new ArrayList<ParseObject>();
                                        HashmapItemsArray.add(hashmapItem);

                                        //Link to Main Object
                                        hashmap1.put("hashmapItemList", HashmapItemsArray);
                                        hashmap1.saveEventually();
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



}

//[{"__type":"Pointer","className":"HashmapItem","objectId":"iAnN0VmuKD"}]
//[{"__type":"Pointer","className":"HashmapItem","objectId":"hQTfvXur56"}]
//[{"__type":"Pointer","className":"HashmapItem","objectId":"AXfhaoEkm0"}]