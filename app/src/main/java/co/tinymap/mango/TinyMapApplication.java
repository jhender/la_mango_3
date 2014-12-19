package co.tinymap.mango;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class TinyMapApplication extends Application {
	
	public static final String TINYMAP_GROUP_NAME = "ALL_TINYMAPS";
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		// add Hashmap subclass
		ParseObject.registerSubclass(TinyMap.class);
        ParseObject.registerSubclass(TinyMapItem.class);

		// enable the Local Datastore
		Parse.enableLocalDatastore(getApplicationContext());
        // Enable Crash Reporting
        // need to enable another sdk first
//        ParseCrashReporting.enable(this);

		Parse.initialize(this, "hfhVukFAkDhY90KnLStI5k8phj2gtmaau05nIo5w", "Awt2mfIyP6wXdtjRLSWuyXPngRaOsgKaFNPwljxc");
		ParseUser.enableAutomaticUser();
//		ParseACL defaultACL = new ParseACL();
//		ParseACL.setDefaultACL(defaultACL, true);
	}
	
	

}
