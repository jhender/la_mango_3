package co.tinymap.mango;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseQuery;
import com.parse.ParseException;


public class ItemDetailActivity extends ActionBarActivity {

    TinyMapItem tinyMapItem;
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

        String incomingId = getIntent().getStringExtra("currentSelectedTinyMapItemId");

        getItem(incomingId);

        button1.setOnClickListener(new View.OnClickListener()
        {   public void onClick(View v)
            {
//                String url = "http://tinymap.co/m/" + hashmapItem.getUuidString();
                String url = "http://tinymap.co/m/" + tinyMapItem.getTitle();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        //todo change this to a Share Via Intent. "Check out www.tinymap.co/m/adfoiuo-342343/"
        //todo actually i prob need to share the TinyMap name instead.

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Toast.makeText(getBaseContext(), "nothing yet" , Toast.LENGTH_SHORT).show();
            }
        });

    }

    //retrieve item from local datastore
    private void getItem(String id) {

        ParseQuery<TinyMapItem> query = TinyMapItem.getQuery();
        query.fromLocalDatastore();
        query.getInBackground(id, new GetCallback<TinyMapItem>() {
            @Override

          public void done(TinyMapItem object, ParseException e) {
//                if (!isFinishing()) {
                tinyMapItem = object;
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
