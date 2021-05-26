package com.blueweidy.scanner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanAct extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);
    }

    @Override
    public void handleResult(Result result) {
        MainActivity.resultText.setText(result.getText());
        MainActivity.resultText.setVisibility(View.VISIBLE);
        MainActivity.havResult = true;
        MainActivity.scanBttn.setVisibility(View.INVISIBLE);
        MainActivity.copyBttn.setVisibility(View.VISIBLE);
        MainActivity.againBttn.setVisibility(View.VISIBLE);
        onBackPressed();
    }

    @Override
    protected void onPause(){
        super.onPause();
        ScannerView.stopCamera();
    }

    @Override
    protected void onResume(){
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slice_in_left, R.anim.slice_out_right);
    }
}
