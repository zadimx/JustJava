package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class LoginActivity extends AppCompatActivity {

    private ImageView imageView;
    private boolean flag = true;
    private boolean flagCount = true;
    private Intent intent;

    private Toast toast;

    private LoginActivity mServer = null;
    private static String string = "";
    private static String string1;
    private Thread thread1;
    private Socket mSocket = null;

    private EditText editTextIdDevice;
    private EditText editTextPassDevice;

    private static String autorization;

    public static String getString() {
        return string;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageView = findViewById(R.id.myButton);
        editTextIdDevice = findViewById(R.id.editTextIdDevice);
        editTextPassDevice = findViewById(R.id.editTextPassDevice);
        intent = new Intent(this, DbActivity.class);
        mServer = new LoginActivity();

        toast = Toast.makeText(getApplicationContext(),
                "Неправильный логин или пароль",
                Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextSize(20);
        v.setTextColor(Color.BLUE);
        v.setBackgroundColor(Color.GREEN);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasConnection(LoginActivity.this)) {

                    autorization = editTextIdDevice.getText().toString() + editTextPassDevice.getText().toString();
                    if (thread1.getState().equals(Thread.State.NEW)) {
                        thread1.start();
                    }
                    while (true) {
                        long date = System.currentTimeMillis() + 500;
                        while (date > System.currentTimeMillis()) {
                            if (date == System.currentTimeMillis()) {
                                if (string != "") {
                                    if (autorization.equals(string)) {
                                        imageView.setImageResource(R.drawable.button_create_push);
                                        mServer.closeConnection();
                                        startActivity(intent);
                                        break;
                                    }
                                    else {
                                        toast.setText("Неправильный логин или пароль");
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                        break;
                                    }
                                }
                                else {
                                    toast.setText("Сервер не отвечает");
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();
                                    break;
                                }
                            }
                        }
break;
                    }

                }
                else {
                    toast.setText("Нет подключения к интернету");
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });


            thread1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {

//                    mServer.openConnection();

                    try {
                        try {
                            mServer.openConnection();
                            mServer.sendData(autorization.getBytes());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        while (true) {
//
//                            mServer.sendData(String.valueOf(editTextIdDevice.getText()).getBytes());
//                            Thread.sleep(2000);
//                            Log.i("^^^P",""+string);
//                            String axis = String.valueOf(string.replace(" ",""));
////                            if(flagCount) {
//////                                if (axis.matches("[0-9]+") && flagCount) {
////                                    startActivity(intent);
////                                    flagCount = false;
//////                                }
////                            }
//                        }
                    } catch (Exception e) {

                    }
                    /*
                        устанавливаем активные кнопки для отправки данных
                        и закрытия соедиения. Все данные по обновлению интерфеса должны
                        обрабатывается в Ui потоке, а так как мы сейчас находимся в
                        отдельном потоке, нам необходимо вызвать метод  runOnUiThread()
                    */

//                            tree.start();
//                            choose.start();
                } catch (Exception e) {
                    mServer = null;
                }
            }
        });
    }

    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume(){
        super.onResume();

    }
    @Override
    protected void onRestart(){
        super.onRestart();
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



        } catch (IOException e) {
            throw new Exception("Невозможно отправить данные: " + e.getMessage());
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        imageView.setImageResource(R.drawable.button);
    }
}
