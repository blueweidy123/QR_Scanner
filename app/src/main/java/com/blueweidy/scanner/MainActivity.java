package com.blueweidy.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String TAG = this.getClass().getName();

    public static boolean havResult = false;
    public static TextView resultText;
    public static Button scanBttn, copyBttn, againBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        resultText = (TextView)findViewById(R.id.result_Text);
        scanBttn = (Button)findViewById(R.id.bttn_Scan);
        copyBttn = (Button)findViewById(R.id.bttn_Copy);
        againBttn = (Button)findViewById(R.id.bttn_Scan_Again);


        if(!havResult){
            scanBttn.setVisibility(View.VISIBLE);
            copyBttn.setVisibility(View.INVISIBLE);
            againBttn.setVisibility(View.INVISIBLE);
        }else{
            scanBttn.setVisibility(View.INVISIBLE);
            copyBttn.setVisibility(View.VISIBLE);
            againBttn.setVisibility(View.VISIBLE);
        }
        scanBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ScanAct.class));
                overridePendingTransition(R.anim.slice_in_right, R.anim.slice_out_left);
            }
        });

        againBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ScanAct.class));
                overridePendingTransition(R.anim.slice_in_right, R.anim.slice_out_left);
            }
        });

        copyBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = resultText.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                clipboardManager.setText(txt);
                Toast.makeText(MainActivity.this, "copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean exit = false;
    @Override
    public void onBackPressed(){

        if(exit){
            finish();
        }else {
            Toast.makeText(MainActivity.this, "press BACK again to exit", Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            },3000);
        }
    }


}
