package com.example.android.justjava;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.justjava.db.NotesContract;
import com.example.android.justjava.db.NotesDbHelper;
import com.example.android.justjava.map.MainActivity;
import com.example.android.justjava.ui.NotesAdapter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DbActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private DbActivity mServer = null;
    private Socket mSocket = null;

    private ObjectInputStream object = null;


    private static String numberTable = LoginActivity.getString()+"db";
    private boolean flagDestroyed = false;

    private Toast toast;

    public static String[] getNumberDevice() {
        return numberDevice;
    }

    private static String numberDevice[] = new String[20];
    public static double[] lat = new double[20];
    public static double[] longit = new double[20];


    public static double lat1;
    public static double longit1;
    public static double lat2;
    public static double longit2;
    public static double lat3;
    public static double longit3;
    public static double lat4;
    public static double longit4;
    public static double lat5;
    public static double longit5;
    public static double lat6;
    public static double longit6;
    public static double lat7;
    public static double longit7;
    public static double lat8;
    public static double longit8;
    public static double lat9;
    public static double longit9;
    public static double lat10;
    public static double longit10;
    public static double lat11;
    public static double longit11;
    public static double lat12;
    public static double longit12;
    public static double lat13;
    public static double longit13;
    public static double lat14;
    public static double longit14;
    public static double lat15;
    public static double longit15;
    public static double lat16;
    public static double longit16;
    public static double lat17;
    public static double longit17;
    public static double lat18;
    public static double longit18;
    public static double lat19;
    public static double longit19;
    public static double lat20;
    public static double longit20;

    private Thread thread1;

    public static NotesAdapter getNotesAdapter() {
        return notesAdapter;
    }

    private static NotesAdapter notesAdapter;



    private static String t1[] = new String[4];
    private static String fullT1[] = new String[7];

    private static String t2[] = new String[4];
    private static String fullT2[] = new String[7];

    private static String t3[] = new String[4];
    private static String fullT3[] = new String[7];

    private static String t4[] = new String[4];
    private static String fullT4[] = new String[7];

    private static String t5[] = new String[4];
    private static String fullT5[] = new String[7];

    private static String t6[] = new String[4];
    private static String fullT6[] = new String[7];

    private static String t7[] = new String[4];
    private static String fullT7[] = new String[7];

    private static String t8[] = new String[4];
    private static String fullT8[] = new String[7];

    private static String t9[] = new String[4];
    private static String fullT9[] = new String[7];

    private static String t10[] = new String[4];
    private static String fullT10[] = new String[7];

    private static String t11[] = new String[4];
    private static String fullT11[] = new String[7];

    private static String t12[] = new String[4];
    private static String fullT12[] = new String[7];

    private static String t13[] = new String[4];
    private static String fullT13[] = new String[7];

    private static String t14[] = new String[4];
    private static String fullT14[] = new String[7];

    private static String t15[] = new String[4];
    private static String fullT15[] = new String[7];

    private static String t16[] = new String[4];
    private static String fullT16[] = new String[7];

    private static String t17[] = new String[4];
    private static String fullT17[] = new String[7];

    private static String t18[] = new String[4];
    private static String fullT18[] = new String[7];

    private static String t19[] = new String[4];
    private static String fullT19[] = new String[7];

    private static String t20[] = new String[4];
    private static String fullT20[] = new String[7];


    private static int countDevice=0;

    private static HashMap<String, String[]> arrayAxisTemp = new HashMap<>();

    public static HashMap<String, String[]> getArrayAxisTemp() {
        return arrayAxisTemp;
    }

    public static String[] getT1() {
        return t1;
    }

    public static String[] getT2() {
        return t2;
    }

    public static String[] getT3() {
        return t3;
    }

    public static String[] getT4() {
        return t4;
    }

    public static String[] getT5() {
        return t5;
    }

    public static String[] getT6() {
        return t6;
    }

    public static String[] getT7() {
        return t7;
    }

    public static String[] getT8() {
        return t8;
    }

    public static String[] getT9() {
        return t9;
    }

    public static String[] getT10() {
        return t10;
    }

    public static String[] getT11() {
        return t11;
    }

    public static String[] getT12() {
        return t12;
    }

    public static String[] getT13() {
        return t13;
    }

    public static String[] getT14() {
        return t14;
    }

    public static String[] getT15() {
        return t15;
    }

    public static String[] getT16() {
        return t16;
    }

    public static String[] getT17() {
        return t17;
    }

    public static String[] getT18() {
        return t18;
    }

    public static String[] getT19() {
        return t19;
    }

    public static String[] getT20() {
        return t20;
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity_main);

        intent = new Intent(this, CreateNoteActivity.class);
        notesAdapter = new NotesAdapter(null, onNoteClickListener, onNoteClickListenerPhone, onNoteClickListenerMap);

        swipeRefreshLayout = findViewById(R.id.db_activity_main);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);



        mServer = new DbActivity();
        RecyclerView recyclerView = findViewById(R.id.notes_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notesAdapter);
//        notesAdapter.notifyDataSetChanged();


        toast = Toast.makeText(getApplicationContext(),
                "Вы больше не имеете устройств.",
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
        mServer.closeConnection();
        LoginActivity.setString("");
        thread1.interrupt();
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
        } catch (IOException e) {
            throw new Exception("Невозможно отправить данные: " + e.getMessage());
        }
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(mSocket.getInputStream()));
            arrayAxisTemp = (HashMap<String, String[]>)in.readObject();
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
//            ObjectInputStream object = new ObjectInputStream(mSocket.getInputStream());
//            arrayAxisTemp = (HashMap<String, String[]>) object.readObject();
        if (Arrays.asList(numberDevice).contains(null)) {
            for (Map.Entry x : arrayAxisTemp.entrySet()) {
                numberDevice[countDevice] = ((String[]) x.getValue())[6];
                countDevice++;
            }
        }
            for (Map.Entry x: arrayAxisTemp.entrySet()
                 ) {

                if (x.getKey().equals(numberDevice[0])) {
                    fullT1 = (String[]) x.getValue();
                    for (int i = 0; i < t1.length; i++) {
                        t1[i] = fullT1[i];
                    }

                    for (int j = 4; j < fullT1.length-1 && fullT1[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT1[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT1[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat1 = q1+Double.parseDouble(str)/60;
                            lat[0] = lat1;
                        }
                        if (j==5) {
                            String[] f= fullT1[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT1[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit1 = q1+Double.parseDouble(str)/60;
                            longit[0] = longit1;
                        }
                    }
                }
                if (x.getKey().equals(numberDevice[1])) {

                    fullT2 = (String[]) x.getValue();
                    for (int i = 0; i < t2.length; i++) {
                        t2[i] = fullT2[i];
                    }

                    for (int j = 4; j < fullT2.length-1 && fullT2[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT2[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT2[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat2 = q1+Double.parseDouble(str)/60;
                            lat[1] = lat2;
                        }
                        if (j==5) {
                            String[] f= fullT2[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT2[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit2 = q1+Double.parseDouble(str)/60;
                            longit[1] = longit2;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[2])) {

                    fullT3 = (String[]) x.getValue();
                    for (int i = 0; i < t3.length; i++) {
                        t3[i] = fullT3[i];
                    }

                    for (int j = 4; j < fullT3.length-1 && fullT3[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT3[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT3[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat3 = q1+Double.parseDouble(str)/60;
                            lat[2] = lat3;
                        }
                        if (j==5) {
                            String[] f= fullT3[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT3[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit3 = q1+Double.parseDouble(str)/60;
                            longit[2] = longit3;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[3])) {
                    fullT4 = (String[]) x.getValue();
                    for (int i = 0; i < t4.length; i++) {
                        t4[i] = fullT4[i];
                    }

                    for (int j = 4; j < fullT4.length-1 && fullT4[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT4[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT4[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat4 = q1+Double.parseDouble(str)/60;
                            lat[3] = lat4;
                        }
                        if (j==5) {
                            String[] f= fullT4[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT4[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit4 = q1+Double.parseDouble(str)/60;
                            longit[3] = longit4;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[4])) {
                    fullT5 = (String[]) x.getValue();
                    for (int i = 0; i < t5.length; i++) {
                        t5[i] = fullT5[i];
                    }

                    for (int j = 4; j < fullT5.length-1 && fullT5[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT5[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT5[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat5 = q1+Double.parseDouble(str)/60;
                            lat[4] = lat5;
                        }
                        if (j==5) {
                            String[] f= fullT5[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT5[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit5 = q1+Double.parseDouble(str)/60;
                            longit[4] = longit5;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[5])) {
                    numberDevice[5]= ((String[]) x.getValue())[6];
                    fullT6 = (String[]) x.getValue();
                    for (int i = 0; i < t6.length; i++) {
                        t6[i] = fullT6[i];
                    }

                    for (int j = 4; j < fullT6.length-1 && fullT6[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT6[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT6[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat6 = q1+Double.parseDouble(str)/60;
                            lat[5] = lat6;
                        }
                        if (j==5) {
                            String[] f= fullT6[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT6[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit6 = q1+Double.parseDouble(str)/60;
                            longit[5] = longit6;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[6])) {
                    fullT7 = (String[]) x.getValue();
                    for (int i = 0; i < t7.length; i++) {
                        t7[i] = fullT7[i];
                    }

                    for (int j = 4; j < fullT7.length-1 && fullT7[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT7[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT7[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat7 = q1+Double.parseDouble(str)/60;
                            lat[6] = lat7;
                        }
                        if (j==5) {
                            String[] f= fullT7[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT7[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit7 = q1+Double.parseDouble(str)/60;
                            longit[6] = longit7;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[7])) {
                    fullT8 = (String[]) x.getValue();
                    for (int i = 0; i < t8.length; i++) {
                        t8[i] = fullT8[i];
                    }

                    for (int j = 4; j < fullT8.length-1 && fullT8[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT8[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT8[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat8 = q1+Double.parseDouble(str)/60;
                            lat[7] = lat8;
                        }
                        if (j==5) {
                            String[] f= fullT8[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT8[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit8 = q1+Double.parseDouble(str)/60;
                            longit[7] = longit8;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[8])) {
                    fullT9 = (String[]) x.getValue();
                    for (int i = 0; i < t9.length; i++) {
                        t9[i] = fullT9[i];
                    }

                    for (int j = 4; j < fullT9.length-1 && fullT9[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT9[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT9[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat9 = q1+Double.parseDouble(str)/60;
                            lat[8] = lat9;
                        }
                        if (j==5) {
                            String[] f= fullT9[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT9[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit9 = q1+Double.parseDouble(str)/60;
                            longit[8] = longit9;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[9])) {
                    fullT10 = (String[]) x.getValue();
                    for (int i = 0; i < t10.length; i++) {
                        t10[i] = fullT10[i];
                    }

                    for (int j = 4; j < fullT10.length-1 && fullT10[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT10[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT10[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat10 = q1+Double.parseDouble(str)/60;
                            lat[9] = lat10;
                        }
                        if (j==5) {
                            String[] f= fullT10[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT10[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit10 = q1+Double.parseDouble(str)/60;
                            longit[9] = longit10;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[10])) {
                    fullT11 = (String[]) x.getValue();
                    for (int i = 0; i < t11.length; i++) {
                        t11[i] = fullT11[i];
                    }

                    for (int j = 4; j < fullT11.length-1 && fullT11[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT11[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT11[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat11 = q1+Double.parseDouble(str)/60;
                            lat[10] = lat11;
                        }
                        if (j==5) {
                            String[] f= fullT11[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT11[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit11 = q1+Double.parseDouble(str)/60;
                            longit[10] = longit11;
                        }
                    }
                }


                if (x.getKey().equals(numberDevice[11])) {
                    fullT12 = (String[]) x.getValue();
                    for (int i = 0; i < t12.length; i++) {
                        t12[i] = fullT12[i];
                    }

                    for (int j = 4; j < fullT12.length-1 && fullT12[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT12[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT12[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat12 = q1+Double.parseDouble(str)/60;
                            lat[11] = lat12;
                        }
                        if (j==5) {
                            String[] f= fullT12[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT12[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit12 = q1+Double.parseDouble(str)/60;
                            longit[11] = longit12;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[12])) {
                    fullT13 = (String[]) x.getValue();
                    for (int i = 0; i < t13.length; i++) {
                        t13[i] = fullT13[i];
                    }

                    for (int j = 4; j < fullT13.length-1 && fullT13[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT13[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT13[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat13 = q1+Double.parseDouble(str)/60;
                            lat[12] = lat13;
                        }
                        if (j==5) {
                            String[] f= fullT13[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT13[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit13 = q1+Double.parseDouble(str)/60;
                            longit[12] = longit13;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[13])) {
                    fullT14 = (String[]) x.getValue();
                    for (int i = 0; i < t14.length; i++) {
                        t14[i] = fullT14[i];
                    }

                    for (int j = 4; j < fullT14.length-1 && fullT14[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT14[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT14[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat14 = q1+Double.parseDouble(str)/60;
                            lat[13] = lat14;
                        }
                        if (j==5) {
                            String[] f= fullT14[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT14[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit14 = q1+Double.parseDouble(str)/60;
                            longit[13] = longit14;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[14])) {
                    fullT15 = (String[]) x.getValue();
                    for (int i = 0; i < t15.length; i++) {
                        t15[i] = fullT15[i];
                    }

                    for (int j = 4; j < fullT15.length-1 && fullT15[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT15[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT15[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat15 = q1+Double.parseDouble(str)/60;
                            lat[14] = lat15;
                        }
                        if (j==5) {
                            String[] f= fullT15[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT15[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit15 = q1+Double.parseDouble(str)/60;
                            longit[14] = longit15;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[15])) {
                    fullT16 = (String[]) x.getValue();
                    for (int i = 0; i < t16.length; i++) {
                        t16[i] = fullT16[i];
                    }

                    for (int j = 4; j < fullT16.length-1 && fullT16[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT16[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT16[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat16 = q1+Double.parseDouble(str)/60;
                            lat[15] = lat16;
                        }
                        if (j==5) {
                            String[] f= fullT16[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT16[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit16 = q1+Double.parseDouble(str)/60;
                            longit[15] = longit16;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[16])) {
                    fullT17 = (String[]) x.getValue();
                    for (int i = 0; i < t17.length; i++) {
                        t17[i] = fullT17[i];
                    }

                    for (int j = 4; j < fullT17.length-1 && fullT17[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT17[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT17[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat17 = q1+Double.parseDouble(str)/60;
                            lat[16] = lat17;
                        }
                        if (j==5) {
                            String[] f= fullT17[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT17[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit17 = q1+Double.parseDouble(str)/60;
                            longit[16] = longit17;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[17])) {
                    fullT18 = (String[]) x.getValue();
                    for (int i = 0; i < t18.length; i++) {
                        t18[i] = fullT18[i];
                    }

                    for (int j = 4; j < fullT18.length-1 && fullT18[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT18[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT18[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat18 = q1+Double.parseDouble(str)/60;
                            lat[17] = lat18;
                        }
                        if (j==5) {
                            String[] f= fullT18[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT18[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit18 = q1+Double.parseDouble(str)/60;
                            longit[17] = longit18;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[18])) {
                    fullT19 = (String[]) x.getValue();
                    for (int i = 0; i < t19.length; i++) {
                        t19[i] = fullT19[i];
                    }

                    for (int j = 4; j < fullT19.length-1 && fullT19[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT19[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT19[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat19 = q1+Double.parseDouble(str)/60;
                            lat[18] = lat19;
                        }
                        if (j==5) {
                            String[] f= fullT19[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT19[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit19 = q1+Double.parseDouble(str)/60;
                            longit[18] = longit19;
                        }
                    }
                }

                if (x.getKey().equals(numberDevice[19])) {
                    fullT20 = (String[]) x.getValue();
                    for (int i = 0; i < t20.length; i++) {
                        t20[i] = fullT20[i];
                    }

                    for (int j = 4; j < fullT20.length-1 && fullT20[j] != null; j++) {
                        if (j==4) {
                            String[] f= fullT20[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLat = Double.parseDouble(fullT20[j]);
                            q = (int)tmpLat;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            lat20 = q1+Double.parseDouble(str)/60;
                            lat[19] = lat20;
                        }
                        if (j==5) {
                            String[] f= fullT20[j].split("\\.");
                            String str = "";
                            int q=0;
                            int q1=0;
                            int q2=0;
                            double tmpLong = Double.parseDouble(fullT20[j]);
                            q = (int)tmpLong;
                            q1 = q/100;
                            q2 = q%100;
                            str = q2+"."+f[1];
                            longit20 = q1+Double.parseDouble(str)/60;
                            longit[19] = longit20;
                        }
                    }
                }

            }


//



    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mServer.closeConnection();
        LoginActivity.setString("");
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
                if (DatabaseUtils.queryNumEntries(db.getReadableDatabase(), NotesContract.Notes.TABLE_NAME) < arrayAxisTemp.size()) {
                    startActivity(intent);
                }
                else {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                return true;

            case R.id.delete_devices:
                AlertDialog.Builder builder = new AlertDialog.Builder(DbActivity.this);
                builder.setTitle("Важное сообщение!")
                        .setMessage("Вы действительно хотите удалить все устройства?")
                        .setCancelable(false)
                        .setPositiveButton("Да",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        for (int i = 0; i<30;i++) {
                                            getContentResolver().delete(ContentUris.withAppendedId(NotesContract.Notes.URI, i),
                                                    null,
                                                    null);
                                        }

                                    }
                                })
                        .setNegativeButton("Нет",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        dialog.cancel();

                                    }
                        });

                AlertDialog alert = builder.create();
                alert.show();
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

        }
    };


    private final NotesAdapter.OnNoteClickListenerPhone onNoteClickListenerPhone = new NotesAdapter.OnNoteClickListenerPhone() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onNoteClickPhone(final long noteId) {

            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+noteId)));
        }

    };

    private final NotesAdapter.OnNoteClickListenerMap onNoteClickListenerMap = new NotesAdapter.OnNoteClickListenerMap() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onNoteClickMap(final long noteId) {

            Intent intent = new Intent(DbActivity.this, MainActivity.class);
            if (noteId == 1000) {
                intent.putExtra("numDev", 0);
            }
            if (noteId == 2000) {
                intent.putExtra("numDev", 1);
            }
            if (noteId == 3000) {
                intent.putExtra("numDev", 2);
            }
            if (noteId == 4000) {
                intent.putExtra("numDev", 3);
            }
            if (noteId == 5000) {
                intent.putExtra("numDev", 4);
            }
            if (noteId == 6000) {
                intent.putExtra("numDev", 5);
            }
            if (noteId == 7000) {
                intent.putExtra("numDev", 6);
            }
            if (noteId == 8000) {
                intent.putExtra("numDev", 7);
            }
            if (noteId == 9000) {
                intent.putExtra("numDev", 8);
            }
            if (noteId == 10000) {
                intent.putExtra("numDev", 9);
            }
            if (noteId == 11000) {
                intent.putExtra("numDev", 10);
            }
            if (noteId == 12000) {
                intent.putExtra("numDev", 11);
            }
            if (noteId == 13000) {
                intent.putExtra("numDev", 12);
            }
            if (noteId == 14000) {
                intent.putExtra("numDev", 13);
            }
            if (noteId == 15000) {
                intent.putExtra("numDev", 14);
            }
            if (noteId == 16000) {
                intent.putExtra("numDev", 15);
            }
            if (noteId == 17000) {
                intent.putExtra("numDev", 16);
            }
            if (noteId == 18000) {
                intent.putExtra("numDev", 17);
            }
            if (noteId == 19000) {
                intent.putExtra("numDev", 18);
            }
            if (noteId == 20000) {
                intent.putExtra("numDev", 19);
            }


            startActivity(intent);
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



    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            for (int j = 1; j <= notesAdapter.getItemCount(); j++) {
                ContentValues contentValues = new ContentValues();

                contentValues.put(NotesContract.Notes.COLUMN_AXIS1, 1);
                contentValues.put(NotesContract.Notes.COLUMN_AXIS2, 2);
                contentValues.put(NotesContract.Notes.COLUMN_AXIS3, 3);
                contentValues.put(NotesContract.Notes.COLUMN_AXIS4, 4);
                getContentResolver().update(ContentUris.withAppendedId(NotesContract.Notes.URI, j),
                        contentValues,
                        null,
                        null);
            }
                swipeRefreshLayout.setRefreshing(false);
        }
    };
}
