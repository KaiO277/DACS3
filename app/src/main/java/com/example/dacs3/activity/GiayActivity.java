package com.example.dacs3.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.dacs3.R;
import com.example.dacs3.adapter.GiayAdapter;
import com.example.dacs3.model.SapPhamMoi;
import com.example.dacs3.retrofit.ApiBanHang;
import com.example.dacs3.retrofit.RetrofitClient;
import com.example.dacs3.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class GiayActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page =1;
    int menu_id ;
    GiayAdapter adapterG;
    List<SapPhamMoi> sanPhamMoiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giay);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        menu_id = getIntent().getIntExtra("menu_id", 37);
        System.out.print(menu_id);
        initUI();
        ActionToolBar();
        getData();
    }

    private void getData() {
        compositeDisposable.add(apiBanHang.getSanPham(menu_id, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()){
                                sanPhamMoiList = sanPhamMoiModel.getResult();
                                adapterG = new GiayAdapter(getApplicationContext(), sanPhamMoiList);
                                recyclerView.setAdapter(adapterG);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "khong ket noi sever"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            System.out.println("Error: "+throwable.getMessage());
                        }
                ));
    }

    private void ActionToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.rcv_giay);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        sanPhamMoiList = new ArrayList<>();
    }
}