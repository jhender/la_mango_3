package co.tinymap.mango;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/*
 * SecretPanel Activity
 */
public class NewTinyMapActivity extends Activity {

    Button saveButton;
    private Button deleteButton;
    private EditText text;
    private TinyMap tinyMap;
    private String tinymapId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tinymap);

        // Fetch the Id from the Extra data
        if (getIntent().hasExtra("ID")) {
            tinymapId = getIntent().getExtras().getString("ID");
        }

        text = (EditText) findViewById(R.id.tinymap_text);
        saveButton = (Button) findViewById(R.id.saveButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        if (tinymapId == null) {
            tinyMap = new TinyMap();
            tinyMap.setUuidString();
        } else {
            ParseQuery<TinyMap> query = TinyMap.getQuery();
            query.fromLocalDatastore();
            query.whereEqualTo("uuid", tinymapId);
            query.getFirstInBackground(new GetCallback<TinyMap>() {

                @Override
                public void done(TinyMap object, ParseException e) {
                    if (!isFinishing()) {
                        tinyMap = object;
                        text.setText(tinyMap.getTitle());
                        deleteButton.setVisibility(View.VISIBLE);
                    }
                }

            });

        }

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                tinyMap.setTitle(text.getText().toString());
                tinyMap.setDraft(false);
//                tinyMap.setAuthor(ParseUser.getCurrentUser());
                tinyMap.saveEventually();
                finish();

//                tinyMap.pinInBackground(TinyMapApplication.TINYMAP_GROUP_NAME,
//                        new SaveCallback() {
//
//                            @Override
//                            public void done(ParseException e) {
//                                if (isFinishing()) {
//                                    return;
//                                }
//                                if (e == null) {
//                                    setResult(Activity.RESULT_OK);
//                                    finish();
//                                } else {
//                                    Toast.makeText(getApplicationContext(),
//                                            "Error saving: " + e.getMessage(),
//                                            Toast.LENGTH_LONG).show();
//                                }
//                            }
//
//                        });
            }

        });

        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // The item will be deleted eventually but will
                // immediately be excluded from query results.
                tinyMap.deleteEventually();
                setResult(Activity.RESULT_OK);
                finish();
            }

        });

    }

}