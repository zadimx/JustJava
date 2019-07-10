package com.example.android.justjava.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Контракт БД заметок
 */
public final class NotesContract {

    public static final String DB_NAME = "notes.db";
    public static final int DB_VERSION = 3;

    public static final String AUTHORITY = "com.example.android.justjava.provider";
    public static final String URI = "content://" + AUTHORITY;

    public static final String[] CREATE_DATABASE_QUERIES = {
            Notes.CREATE_TABLE,
            Notes.CREATE_UPDATED_TS_INDEX
    };

    private NotesContract() {
    }

    /**
     * Описание таблицы с заметками
     */
    public static abstract class Notes implements BaseColumns {

        public static final String TABLE_NAME = "notes";

        public static final Uri URI = Uri.parse(NotesContract.URI + "/" + TABLE_NAME);

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_NOTE = "note";
        public static final String COLUMN_AXIS1 = "axis1";
        public static final String COLUMN_AXIS2 = "axis2";
        public static final String COLUMN_AXIS3 = "axis3";
        public static final String COLUMN_AXIS4 = "axis4";
        public static final String COLUMN_NUMBER_PHONE = "number_phone";
        public static final String COLUMN_CREATED_TS = "created_ts";
        public static final String COLUMN_UPDATED_TS = "updated_ts";

        public static final String CREATE_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT , " +
                        "%s TEXT, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s INTEGER NOT NULL);",
                TABLE_NAME,
                _ID,
                COLUMN_TITLE,
                COLUMN_AXIS1,
                COLUMN_AXIS2,
                COLUMN_AXIS3,
                COLUMN_AXIS4,
                COLUMN_NUMBER_PHONE,
                COLUMN_NOTE,
                COLUMN_CREATED_TS,
                COLUMN_UPDATED_TS);

        public static final String CREATE_UPDATED_TS_INDEX = String.format("CREATE INDEX updated_ts_index " +
                        "ON %s (%s);",
                TABLE_NAME,
                COLUMN_UPDATED_TS);

        /**
         * Столбцы, которые будем выбирать
         */

        public static final String[] LIST_PROJECTION = {
                _ID,
                COLUMN_TITLE,
                COLUMN_AXIS1,
                COLUMN_AXIS2,
                COLUMN_AXIS3,
                COLUMN_AXIS4,
                COLUMN_NUMBER_PHONE,
                COLUMN_NOTE,
                COLUMN_CREATED_TS,
                COLUMN_UPDATED_TS
        };

        public static final String[] SINGLE_PROJECTION = {
                _ID,
                COLUMN_TITLE,
                COLUMN_AXIS1,
                COLUMN_AXIS2,
                COLUMN_AXIS3,
                COLUMN_AXIS4,
                COLUMN_NUMBER_PHONE,
                COLUMN_NOTE,
                COLUMN_CREATED_TS,
                COLUMN_UPDATED_TS
        };

        /**
         * Типы данных
         */

        // Список заметок
        public static final String URI_TYPE_NOTE_DIR = "vnd.android.cursor.dir/vnd.example.android.justjava.note";

        // Одна заметка
        public static final String URI_TYPE_NOTE_ITEM = "vnd.android.cursor.item/vnd.example.android.justjava.note";

    }

}
