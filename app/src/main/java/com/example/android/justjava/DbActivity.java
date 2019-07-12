package com.example.android.justjava;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.justjava.db.NotesContract;
import com.example.android.justjava.db.NotesDbHelper;
import com.example.android.justjava.map.MainActivity;
import com.example.android.justjava.ui.NotesAdapter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class DbActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private DbActivity mServer = null;
    private Socket mSocket = null;
    private static String string = "0";



    private static String numberTable = "1t";
    private static int flagTab = 0;
    private boolean flagDestroyed = false;

    private Toast toast;

    public double getLat() {
        return lat;
    }

    public double getLongit() {
        return longit;
    }

    public static double lat;
    public static double longit;

    private NotesDbHelper notesDbHelper;

    private static boolean flagUpdate = true;
    private Thread thread1;
    private Thread thread12;
    private NotesAdapter notesAdapter;
    private static String t1[] = new String[4];
    private static String t2[] = new String[4];


    public static String[] getT1() {
        return t1;
    }

    public static String[] getT2() {
        return t2;
    }


    private static LoginActivity loginActivity;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity_main);
        intent = new Intent(this, AddNewDeviceActivity.class);
        notesAdapter = new NotesAdapter(null, onNoteClickListener, onNoteClickListenerPhone, onNoteClickListenerMap);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mServer = new DbActivity();
        RecyclerView recyclerView = findViewById(R.id.notes_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notesAdapter);
//        notesAdapter.notifyDataSetChanged();

        toast = Toast.makeText(getApplicationContext(),
                "На данный момент можно подключится только к одному девасу",
                Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.BLUE);
        v.setBackgroundColor(Color.GREEN);


        thread1.start();
                getLoaderManager().initLoader(
                        0, // Идентификатор загрузчика
                        null, // Аргументы
                        this // Callback для событий загрузчика
                );
    }

    {
        thread1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mServer.openConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                while (true) {
                    long date = System.currentTimeMillis()+500;
                    while (date > System.currentTimeMillis()) {
                        if (date == System.currentTimeMillis()) {
                            try {

                                mServer.sendData(numberTable.getBytes());

//                                if (flagUpdate) {
//                                    onNoteClickListener.onNoteClick(1);
//                                    flagUpdate = false;
//                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    if (flagDestroyed) {
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flagDestroyed = true;
    }

    public void openConnection() throws Exception {

        /* Освобождаем ресурсы */
        closeConnection();

        try {
        /*
            Создаем новый сокет. Указываем на каком компютере и порту запущен наш процесс,
            который будет принамать наше соединение.
        */
            mSocket = new Socket(InetAddress.getByName("kh.zamax.info"), 7777);
//            52.59.227.6 192.168.0.106
        } catch (IOException e) {
            throw new Exception("Невозможно создать сокет: " + e.getMessage());
        }
    }

    public void closeConnection() {

        /* Проверяем сокет. Если он не зарыт, то закрываем его и освобдождаем соединение.*/
        if (mSocket != null && !mSocket.isClosed()) {

            try {
                mSocket.close();
            } catch (IOException e) {

            } finally {
                mSocket = null;
            }

        }
        mSocket = null;
    }


    public void sendData(byte[] data) throws Exception {

        /* Проверяем сокет. Если он не создан или закрыт, то выдаем исключение */
        if (mSocket == null || mSocket.isClosed()) {
            throw new Exception("Невозможно отправить данные. Сокет не создан или закрыт");
        }

        /* Отправка данных */
        byte[] buffer = new byte[1024 * 4];
        try {
            mSocket.getOutputStream().write(data);
            mSocket.getOutputStream().flush();
            int count = mSocket.getInputStream().read(buffer, 0, buffer.length);
            string = new String(buffer, 0, count);
            Log.d("###$$","##$"+string);
            int i = -1;
            for (String retval : string.split(" ")) {
                i = i + 1;
                if (i==4) {
                    String[] f= retval.split("\\.");
                    String str = "";
                    int q=0;
                    int q1=0;
                    int q2=0;
                    int q3=0;
                    lat = Double.parseDouble(retval);
                    q = (int)lat;
                    q1 = q/100;
                    q2 = q%100;
//                    q3 = Integer.parseInt(f[1]);
                    str = q2+"."+f[1];
//                    lat =  Math.signum(q1) * (Math.abs(q1) + (q2 / 60.0) + (q3 / 3600.0));
                    lat = q1+Double.parseDouble(str)/60;
                    Log.d("###$$","###$$"+string+"------"+lat);
                }
                if (i==5) {

                    String[] f= retval.split("\\.");
                    String str = "";
                    int q=0;
                    int q1=0;
                    int q2=0;
                    int q3=0;
                    longit = Double.parseDouble(retval);
                    q = (int)longit;
                    q1 = q/100;
                    q2 = q%100;
                    str = q2+"."+f[1];

                    longit = q1+Double.parseDouble(str)/60;
//                    q3 = Integer.parseInt(f[1]);
//                    longit =  Math.signum(q1) * (Math.abs(q1) + (q2 / 60.0) + (q3 / 3600.0));
                    Log.d("###$$2", string+"-----"+longit);
                }

            }


//


        } catch (IOException e) {
            throw new Exception("Невозможно отправить данные: " + e.getMessage());
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mServer.closeConnection();
        thread1.interrupt();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.add_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showing:


                return true;

            case R.id.setting:


                return true;

            case R.id.notifications:


                return true;

            case R.id.add_device:
                NotesDbHelper db = new NotesDbHelper(this);
//                if (DatabaseUtils.queryNumEntries(db.getReadableDatabase(), NotesContract.Notes.TABLE_NAME) == 0) {
                    startActivity(intent);
//                }
//                else {
//                    toast.setGravity(Gravity.CENTER, 0, 0);
//                    toast.show();
//                }
                return true;

            case R.id.delete_devices:

                for (int i = 0; i<10;i++) {
                    getContentResolver().delete(ContentUris.withAppendedId(NotesContract.Notes.URI, i),
                            null,
                            null);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,  // Контекст
                NotesContract.Notes.URI, // URI
                NotesContract.Notes.LIST_PROJECTION, // Столбцы
                null, // Параметры выборки
                null, // Аргументы выборки
                null // Сортировка по умолчанию
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {


        cursor.setNotificationUri(getContentResolver(), NotesContract.Notes.URI);

        notesAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /**
     * Listener для клика по заметке
     */
    private final NotesAdapter.OnNoteClickListener onNoteClickListener = new NotesAdapter.OnNoteClickListener() {
        @Override
        public void onNoteClick(final long noteId) {
//                    while (true) {
//                        long date = System.currentTimeMillis() + 500;
//                        while (date > System.currentTimeMillis()) {
//                            if (date == System.currentTimeMillis()) {
                                String title = "a";
                                int i = -1;
            Log.d("##", "rrr1 "+string +string.startsWith("1t"));
            if (noteId == 1) {
                numberTable = "1t";
                ContentValues contentValues = new ContentValues();
                contentValues.put(NotesContract.Notes.COLUMN_AXIS1, 1);
                contentValues.put(NotesContract.Notes.COLUMN_AXIS2, 2);
                contentValues.put(NotesContract.Notes.COLUMN_AXIS3, 3);
                contentValues.put(NotesContract.Notes.COLUMN_AXIS4, 4);
                getContentResolver().update(ContentUris.withAppendedId(NotesContract.Notes.URI, noteId),
                        contentValues,
                        null,
                        null);

            }
            else if (noteId == 2) {
                numberTable = "2t";
                ContentValues contentValues = new ContentValues();
                contentValues.put(NotesContract.Notes.COLUMN_AXIS1, 1);
                contentValues.put(NotesContract.Notes.COLUMN_AXIS2, 2);
                contentValues.put(NotesContract.Notes.COLUMN_AXIS3, 3);
                contentValues.put(NotesContract.Notes.COLUMN_AXIS4, 4);
                getContentResolver().update(ContentUris.withAppendedId(NotesContract.Notes.URI, noteId),
                        contentValues,
                        null,
                        null);
            }
            if (string.startsWith("1t")) {
                for (String retval : string.split(" ")) {
                    Log.d("##", "rrr2 "+retval);
                    i = i + 1;
                    Log.d("##", "rrr7 "+i%4);
                    if (retval.startsWith("1t")) {
                        retval = retval.replace("1t", "");
                        Log.d("##", "rrr3 "+retval);
                    }
                    if (i == 4) {
                        Log.d("##", "rrr4 "+retval);
                        break;
                    }
                    Log.d("##", "rrr5 "+retval);
                    t1[i] = retval;
                    Log.d("##", "rrr6 "+t1[i]);

                }

//                axis1 = t1[0];
//                axis2 = t1[1];
//                axis3 = t1[2];
//                axis4 = t1[3];
            }
            if (string.startsWith("2t")) {
                for (String retval : string.split(" ")) {
                    i = i + 1;
                    if (retval.startsWith("2t")) {
                        retval =  retval.replace("2t", "");
                    }
                    if (i == 4) {
                        break;
                    }
                    t2[i] = retval;
                }
//                axis1 = t1[0];
//                axis2 = t1[1];
//                axis3 = t1[2];
//                axis4 = t1[3];
            }
                String text = "a";
                long currentTime = System.currentTimeMillis();

//                ContentValues contentValues = new ContentValues();
//                                contentValues.put(NotesContract.Notes.COLUMN_TITLE, title);

//                                contentValues.put(NotesContract.Notes.COLUMN_AXIS1, 1);
//                                contentValues.put(NotesContract.Notes.COLUMN_AXIS2, 2);
//                                contentValues.put(NotesContract.Notes.COLUMN_AXIS3, 3);
//                                contentValues.put(NotesContract.Notes.COLUMN_AXIS4, 4);

//                                contentValues.put(NotesContract.Notes.COLUMN_NOTE, text);


//                                if (noteId == -1) {
//                                    contentValues.put(NotesContract.Notes.COLUMN_CREATED_TS, currentTime);
//                                }

//                contentValues.put(NotesContract.Notes.COLUMN_UPDATED_TS, currentTime);
//                if (noteId == -1) {
//                    getContentResolver().insert(NotesContract.Notes.URI, contentValues);
//                } else {
//                    getContentResolver().update(ContentUris.withAppendedId(NotesContract.Notes.URI, noteId),
//                            contentValues,
//                            null,
//                            null);
//                }

//                    }
//                }


//        }
        }
    };


    private final NotesAdapter.OnNoteClickListenerPhone onNoteClickListenerPhone = new NotesAdapter.OnNoteClickListenerPhone() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onNoteClickPhone(final long noteId) {

            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+getAppCategoryDetail())));
//            if (DatabaseUtils.queryNumEntries(db.getReadableDatabase(), NotesContract.Notes.TABLE_NAME) == 0) {
//                startActivity(intent);
//            }
        }

    };

    private final NotesAdapter.OnNoteClickListenerMap onNoteClickListenerMap = new NotesAdapter.OnNoteClickListenerMap() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onNoteClickMap(final long noteId) {

            Intent intent = new Intent(DbActivity.this, MainActivity.class);

            startActivity(intent);
//            }
        }

    };

    public String getAppCategoryDetail() {

        final String TABLE_NAME = "notes";
        NotesDbHelper db = new NotesDbHelper(DbActivity.this);
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase d  = db.getReadableDatabase();
        Cursor cursor      = d.rawQuery(selectQuery, null);
        byte[] data1      = null;

        if (cursor.moveToFirst()) {
            do {
                data1 = cursor.getBlob(6);
            } while (cursor.moveToNext());
        }
        cursor.close();
        String str = new String(data1);
        str = str.replaceAll("\\D+","");
        return str;
    }
}
