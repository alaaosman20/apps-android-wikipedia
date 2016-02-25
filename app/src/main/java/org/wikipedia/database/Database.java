package org.wikipedia.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.wikipedia.history.HistoryEntry;
import org.wikipedia.savedpages.SavedPage;
import org.wikipedia.editing.summaries.EditSummary;
import org.wikipedia.pageimages.PageImage;
import org.wikipedia.search.RecentSearch;
import org.wikipedia.useroption.database.UserOptionRow;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wikipedia.db";
    private static final int DATABASE_VERSION = 9;

    private final DatabaseTable[] databaseTables = {
            HistoryEntry.DATABASE_TABLE,
            PageImage.DATABASE_TABLE,
            RecentSearch.DATABASE_TABLE,
            SavedPage.DATABASE_TABLE,
            EditSummary.DATABASE_TABLE,
            UserOptionRow.DATABASE_TABLE
    };

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (DatabaseTable table : databaseTables) {
            table.createTables(sqLiteDatabase, DATABASE_VERSION);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int from, int to) {
        for (DatabaseTable table : databaseTables) {
            table.upgradeSchema(sqLiteDatabase, from, to);
        }
    }
}