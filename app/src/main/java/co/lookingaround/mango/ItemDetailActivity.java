package co.lookingaround.mango;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;


public class ItemDetailActivity extends ActionBarActivity {

    HashmapItem hashmapItem;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        String incomingId = getIntent().getStringExtra("currentSelectedHashmapItemId");

        getItem(incomingId);
    }

    //retrieve item from local datastore
    private void getItem(String id) {

        ParseQuery<HashmapItem> query = HashmapItem.getQuery();
        query.fromLocalDatastore();
        query.getInBackground(id, new GetCallback<HashmapItem>() {
            @Override

          public void done(HashmapItem object, ParseException e) {
//                if (!isFinishing()) {
//                    hashmapItem = object;
////                    todoText.setText(todo.getTitle());
////                    deleteButton.setVisibility(View.VISIBLE);
                textView1.setText(object.getTitle());
                textView2.setText(object.getDescription());
                textView3.setText(object.getAddress());
//                }
            }
        });

    }







//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
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
