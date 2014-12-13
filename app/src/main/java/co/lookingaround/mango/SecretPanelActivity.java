package co.lookingaround.mango;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.ParseObject;

import java.util.ArrayList;


public class SecretPanelActivity extends ActionBarActivity {

    Button button2;
    Button button3;

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
    }


    private void createhashmapitem() {
        //TODO temporary code
        HashmapItem hmItem;
        hmItem = new HashmapItem();
        hmItem.setUuidString();
        hmItem.setTitle("Cafe 3");
        hmItem.setAddress("10 Changi Road, Singapore");
        hmItem.setDescription("Place with great coffee and cakes.");
        hmItem.saveEventually();
        Log.i("MainTabActivity", "createHashmapItem");
    }

    private void linkHashMap() {
        //TODO temporary code
//        ParseObject hashmap1 = get something
        ArrayList<ParseObject> HashmapItems = new ArrayList<ParseObject>();
//        hashmap1.add(item1);
//        hashmap1.add(item2);
    }






//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_secret_panel, menu);
//        return true;
//    }
//
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






}
