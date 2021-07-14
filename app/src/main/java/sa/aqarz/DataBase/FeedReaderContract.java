package sa.aqarz.DataBase;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_city_id = "city_id";
        public static final String COLUMN_name_ar = "name_ar";
        public static final String COLUMN_lat = "lat";
        public static final String COLUMN_long = "long";








    }
}