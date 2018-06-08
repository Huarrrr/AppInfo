package com.example.huar.myapplication.Activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huar.myapplication.R;


/**
 * Created by 菜鸟先吃 on 2018/5/23.
 */

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class LoginActivity extends Activity {
    private static  String processURL="http://10.3.44.24:8080/";

    private EditText txUserName;
    private EditText txPassword;
    private Button btnLogin;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///在Android2.2以后必须添加以下代码
        //本应用采用的Android4.0
        //设置线程的策略
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        //设置虚拟机的策略
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                //.detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());


        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //设置页面布局
        setContentView(R.layout.activity_login);
        txUserName =findViewById(R.id.username);
        txPassword=findViewById(R.id.password);
        btnLogin=findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //发送请求
//                //4.0开始不支持直接访问。
//                @SuppressLint("HandlerLeak") final Handler myHandler = new Handler(){
//                    @Override
//                    public void handleMessage(Message msg) {
//                        String result = msg.obj.toString();
//                        System.out.println(result);
//                        if(!result.equals("")){
//                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                            startActivity(intent);
//                        }else{
//                            Toast.makeText(LoginActivity.this, "错误", Toast.LENGTH_SHORT).show();
//                        }
//                        super.handleMessage(msg);
//                    }
//                };
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        LogingToServer login = new LogingToServer();
//                        String result = login.doPost(txUserName.getText().toString(), txPassword.getText().toString());
//                        //发送消息
//                        Message msg = new Message();
//                        msg.obj = result;
//                        myHandler.sendMessage(msg);
//                    }
//                }).start();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
           }
       });

    }

}