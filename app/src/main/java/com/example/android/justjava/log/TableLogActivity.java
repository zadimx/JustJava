package com.example.android.justjava.log;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
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
    private static HashMap<String, ArrayList<String>> arrayAxisTemp1 = new HashMap<>();

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
        setContentView(R.layout.activity_table_log);
        RecyclerView recyclerView = findViewById(R.id.log_rv);

        mServer = new TableLogActivity();

        thread1.start();

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
                                    mServer.sendData("hour".getBytes());
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
        mServer.closeConnection();
        thread1.interrupt();
    }

    private final LogAdapter.OnLogClickListener onLogClickListener = new LogAdapter.OnLogClickListener() {
        @Override
        public void onLogClick(long noteId) {
            DbActivity.setFlagGPS("timeDev");
            Intent intent = new Intent(TableLogActivity.this, MainActivity.class);
            Log.d("a6666", "a6666 "+LogAdapter.getLat()[(int) noteId]+" "+LogAdapter.getLon()[(int) noteId]);
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

            for (Map.Entry x :arrayAxisTemp1.entrySet()
            ) {

                arrayAxisTemp1.put(String.valueOf(x.getKey()),((ArrayList<String>) x.getValue()));
                Log.d("###444", "####444" + " " + arrayAxisTemp1.size()+" "+x.getKey()+" "+x.getValue()+" "+ ((ArrayList<String>) x.getValue()).size());
            }




//--------------------------------------------------------------------



    }



    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                                try {
                mServer.sendData("hour".getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
                }
            }).start();

            logAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    };

}
