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
@ParseClassName("HashmapItem")
public class HashmapItem extends ParseObject {

    public HashmapItem() {
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

    public static ParseQuery<HashmapItem> getQuery() {
        return ParseQuery.getQuery(HashmapItem.class);
    }

    /* TODO
     * add in Relation to ParseObject Hashmap
     *
     */


}
