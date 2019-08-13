package com.example.android.justjava.log;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.justjava.DbActivity;
import com.example.android.justjava.LoginActivity;
import com.example.android.justjava.R;
import com.example.android.justjava.db.NotesContract;
import com.example.android.justjava.map.MainActivity;
import com.example.android.justjava.ui.NotesAdapter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TableLogActivity extends AppCompatActivity {
    private static HashMap<String, ArrayList<String>> arrayAxisTemp1 = null;

    private static ArrayList<Double> lat = new ArrayList<>();
    private static ArrayList<Double> lon = new ArrayList<>();

    public static ArrayList<Double> getLat() {
        return lat;
    }

    public static ArrayList<Double> getLon() {
        return lon;
    }



    private boolean flagDestroyed = false;
    public static HashMap<String, ArrayList<String>> getArrayAxisTemp1() {
        return arrayAxisTemp1;
    }
    private Thread thread1;
    private Socket mSocket = null;
    private TableLogActivity mServer = null;
    private SwipeRefreshLayout swipeRefreshLayout;



    private static LogAdapter logAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mServer = new TableLogActivity();
        thread1.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_table_log);

            RecyclerView recyclerView = findViewById(R.id.log_rv);

            swipeRefreshLayout = findViewById(R.id.log_refresh_rv);
            swipeRefreshLayout.setOnRefreshListener(onRefreshListener);

            logAdapter = new LogAdapter(null, onLogClickListener);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(logAdapter);
            logAdapter.notifyDataSetChanged();
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
//                while (true) {
//                    long date = System.currentTimeMillis()+500;
//                    while (date > System.currentTimeMillis()) {
//                        if (date == System.currentTimeMillis()) {
                            try {
                                try {
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==1) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==2) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==3) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==4) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==5) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==6) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==7) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==8) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==9) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==10) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==11) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==12) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==13) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==14) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==15) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==16) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==17) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==18) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==19) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==20) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

//                    }
//                    if (flagDestroyed) {
//                        break;
//                    }
//                }
//            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        arrayAxisTemp1=null;
        mServer.closeConnection();
        thread1.interrupt();
    }

    private final LogAdapter.OnLogClickListener onLogClickListener = new LogAdapter.OnLogClickListener() {
        @Override
        public void onLogClick(long noteId) {
            DbActivity.setFlagGPS("timeDev");
            Intent intent = new Intent(TableLogActivity.this, MainActivity.class);
            Log.d("a6666", "a6666 "+getLat().get((int) noteId)+" "+getLon().get((int) noteId));
            intent.putExtra("timeDev", (int) noteId);
            startActivity(intent);

        }
    };


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
            arrayAxisTemp1 = (HashMap<String, ArrayList<String>>) in.readObject();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        НАЧАЛО!-----------------------------------------------------------------------------

        int position = 0;
            for (Map.Entry x :arrayAxisTemp1.entrySet()
            ) {

//                arrayAxisTemp1.put(String.valueOf(x.getKey()),((ArrayList<String>) x.getValue()));
                if (x.getKey().equals("latitude")) {
                    for (int i = 0; i < ((ArrayList<String>) x.getValue()).size(); i++) {
                        lat.add(Double.parseDouble(((ArrayList<String>) x.getValue()).get(i)));
                    }
                }
                if (x.getKey().equals("longitude")) {
                    for (int i = 0; i < ((ArrayList<String>) x.getValue()).size(); i++) {
                        lon.add(Double.parseDouble(((ArrayList<String>) x.getValue()).get(i)));
                    }
                }
//                position++;
            }
        Log.d("###444", "####444" + " " + arrayAxisTemp1.size()+" "+lat.size()+" "+lon.size()+" ");





//--------------------------------------------------------------------



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                                try {
                                    lat.clear();
                                    lon.clear();
                                    for (String x: NotesAdapter.getListDevice()
                                         ) {
                                        Log.d("a77777", "a77777 "+x);
                                    }
//                                    Log.d("a666677", "a666677 "+(getIntent().getIntArrayExtra("numDevLog")[0]+"t"));
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==1) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==2) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==3) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==4) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==5) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==6) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==7) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==8) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==9) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==10) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==11) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==12) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==13) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==14) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==15) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==16) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==17) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==18) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==19) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }
                                    if (Integer.parseInt(getIntent().getStringExtra("numDevLog"))==20) {
                                        mServer.sendData((getIntent().getStringExtra("numDevLog")+"t").getBytes());
                                    }

            } catch (Exception e) {
                e.printStackTrace();
            }
                }
            }).start();
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    logAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 500);

        }
    };

}
