package com.example.weekeend2hw;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class CelebrityProviderContract { // this contains
    public static final String CONTENT_AUTHORITY = "com.example.weekeend2hw";
    public static final String table = Celebrity.class.getSimpleName();
    public static final Uri BASE_CONTENT_ID = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CELEBRITY = "celebrity";

    public static final class CelebrityEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_ID.buildUpon().appendPath(PATH_CELEBRITY).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_CELEBRITY;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_URI +"/" +PATH_CELEBRITY;

        public static Uri buildCelebrityUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

    }


}

