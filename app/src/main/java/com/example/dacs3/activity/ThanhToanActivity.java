package com.example.dacs3.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacs3.R;
import com.example.dacs3.utils.Utils;
import com.google.gson.Gson;

import java.text.DecimalFormat;

public class ThanhToanActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txttongtien, txtemail, txtsodienthoai;
    EditText edtdiachi;
    Button btndathang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        initUI();
        initControl();
    }

    private void initControl() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        long tongtien = getIntent().getLongExtra("tongtien",0);
        txttongtien.setText(decimalFormat.format(tongtien)+"");
        txtsodienthoai.setText(Utils.user_current.getMobile());
        txtemail.setText(Utils.user_current.getEmail());
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_diachi = edtdiachi.getText().toString().trim();
                if(TextUtils.isEmpty(str_diachi)){
                    Toast.makeText(getApplicationContext(), "Ban chua nhap dia chi", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("test", new Gson().toJson(Utils.manggiohang));
                }
            }
        });
    }

    private void initUI() {
        toolbar = findViewById(R.id.toolbar);
        txtemail = findViewById(R.id.txtemail);
        txtsodienthoai = findViewById(R.id.txtsdt);
        txttongtien = findViewById(R.id.txttongtien);
        edtdiachi = findViewById(R.id.edtdiachi);
        btndathang = findViewById(R.id.btndangnhap);
    }
}