package com.example.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class Scanner extends Activity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView =new ZXingScannerView(this);
        scannerView.setAspectTolerance(0.5f);
        setContentView(scannerView);
    }



    @Override
    protected void onResume() {
        super.onResume();

        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }



    @Override
    protected void onPause() {
        super.onPause();

        scannerView.stopCamera();
    }





    @Override
    public void handleResult(Result rawResult) {

        //Baris Kode yang akan dikirimkan ke halaman Activity lain.
        Intent intent= new Intent();
        intent.putExtra("result",rawResult.getText());
        setResult(RESULT_OK,intent);
        finish();

    }
}
