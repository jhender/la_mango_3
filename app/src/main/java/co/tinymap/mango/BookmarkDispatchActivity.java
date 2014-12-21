package co.tinymap.mango;

import com.parse.ui.ParseLoginDispatchActivity;

/**
 * Created by think on 12/21/14.
 */
public class BookmarkDispatchActivity extends ParseLoginDispatchActivity {
    @Override
    protected Class<?> getTargetClass() {
        return BookmarkActivity.class;
    }
}