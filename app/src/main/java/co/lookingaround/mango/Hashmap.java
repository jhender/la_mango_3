package co.lookingaround.mango;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.UUID;

/**
 * Created by jianhui.ho on 12/8/2014.
 * Creates Parse Object for HashMap
 */
@ParseClassName("Hashmap")
public class Hashmap extends ParseObject {

    public Hashmap() {
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

    public static ParseQuery<Hashmap> getQuery() {
        return ParseQuery.getQuery(Hashmap.class);
    }

    /* TODO Two options:
     * Add another subclass for the individual pins
     * or Add a hash of the items inside here. Easy to push and pull but super difficult to edit.
     *
      */


}
