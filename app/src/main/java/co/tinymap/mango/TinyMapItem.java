package co.tinymap.mango;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.UUID;

/**
 * Created by jianhui.ho on 12/8/2014.
 * Creates Parse Object for TinyMapItem
 */
@ParseClassName("TinyMapItem")
public class TinyMapItem extends ParseObject {

    public TinyMapItem() {
        //A default constructor is required.
    }
    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public ParseUser getAuthor() {
        return getParseUser("author");
    }

    public void setAuthor(ParseUser currentUser) {
        put("author", currentUser);
    }

    public boolean isDraft() {
        return getBoolean("isDraft");
    }

    public void setDraft(boolean isDraft) {
        put("isDraft", isDraft);
    }

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<TinyMapItem> getQuery() {
        return ParseQuery.getQuery(TinyMapItem.class);
    }

    public String getAddress() {
        return getString("address");
    }

    public void setAddress(String address) {
        put("address", address);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public ParseGeoPoint getGeopoint() {
        return getParseGeoPoint("geopoint");
    }

    public void setGeoPoint(ParseGeoPoint gp) {
        put("geopoint", gp);
    }


}
