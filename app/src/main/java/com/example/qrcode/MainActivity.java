package com.example.qrcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button tombolScan;
    private TextView textshow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tombolScan = findViewById(R.id.btnscan);
        textshow = findViewById(R.id.textnya);


        tombolScan.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CAMERA)){
                    mulaiScan();

                }else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},0);
                }


            }else {
                mulaiScan();

            }

        });
    }

    private void mulaiScan(){
        Intent intent =new Intent(getApplicationContext(),Scanner.class);
        startActivityForResult(intent,120422);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==120422){
            if (resultCode==RESULT_OK && data!=null){
                String code = data.getStringExtra("result");
                textshow.setText(code);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==0){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                mulaiScan();
            }else {
                Toast.makeText(this,"Gagal Membuka Kamera.",Toast.LENGTH_LONG).show();
            }
        }
    }
}