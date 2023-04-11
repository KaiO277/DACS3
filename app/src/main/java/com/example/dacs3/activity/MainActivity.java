package com.example.dacs3.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.dacs3.R;
import com.example.dacs3.adapter.LoaiSpAdapter;
import com.example.dacs3.adapter.SanPhamMoiAdapter;
import com.example.dacs3.model.LoaiSp;
import com.example.dacs3.model.SanPhamMoiModel;
import com.example.dacs3.model.SapPhamMoi;
import com.example.dacs3.retrofit.ApiBanHang;
import com.example.dacs3.retrofit.RetrofitClient;
import com.example.dacs3.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvProduct;
    private Toolbar toolbar;
    private ViewFlipper flipper;
    private NavigationView view;
    private ListView listView;
    private DrawerLayout drawerLayout;

    LoaiSpAdapter loaiSpAdapter;
    List<LoaiSp> mList;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    List<SapPhamMoi> mangSpmoi;
    SanPhamMoiAdapter SpAdapter;
    NotificationBadge badge;
    FrameLayout frameLayout, frameSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        System.out.print("link: "+Utils.URL_BASE+"  ");

        initUI();
        ActionBar();
        ActionViewFlipper();
        if (isConnected(this)){

            ActionViewFlipper();
            getLoaiSanPham();
            getSpMoi();
            getEventClick();
        }else {
            Toast.makeText(this, "Không có internet, Vui lòng kết nỗi internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEventClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent trangchu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(trangchu);
                        break;
                    case 1:
                        Intent giay = new Intent(getApplicationContext(), GiayActivity.class);
                        giay.putExtra("menu_id", 37);
                        startActivity(giay);
                        break;
                    case 2:
                        Intent nu = new Intent(getApplicationContext(), GiayActivity.class);
                        nu.putExtra("menu_id", 38);
                        startActivity(nu);
                        break;
                    case 3:
                        Intent nam = new Intent(getApplicationContext(), GiayActivity.class);
                        nam.putExtra("menu_id", 39);
                        startActivity(nam);
                        break;
                    case 4:
                        Intent lienhe = new Intent(getApplicationContext(), LienHeActivity.class);
                        startActivity(lienhe);
                        break;
                    case 5:
                        Intent thongtin = new Intent(getApplicationContext(), ThongTinActivity.class);
                        startActivity(thongtin);
                        break;
                    case 6:
                        Intent dangxuat = new Intent(getApplicationContext(), DangNhapActivity.class);
                        dangxuat.putExtra("Set", 1);
                        startActivity(dangxuat);
                        break;
                }
            }
        });
    }

    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                   sanPhamMoiModel -> {
                        if(sanPhamMoiModel.isSuccess()){
                            mangSpmoi = sanPhamMoiModel.getResult();
                            SpAdapter = new SanPhamMoiAdapter(getApplicationContext(), mangSpmoi);
                            rcvProduct.setAdapter(SpAdapter);
                        }
                   },
                        throwable -> {
                            Toast.makeText(this, "Khong ket noi duoc voi Server"+ throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void getLoaiSanPham() {
       compositeDisposable.add(apiBanHang.getLoaiSp()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       loaiSpModel -> {
                           if (loaiSpModel.isSuccess()){
                               mList = loaiSpModel.getResult();
                               loaiSpAdapter = new LoaiSpAdapter(mList, getApplicationContext());
                               listView.setAdapter(loaiSpAdapter);
                           }
                       }
               ));
    }


    private void ActionViewFlipper() {
        List<String> manquangcao = new ArrayList<>();
        manquangcao.add(Utils.URL_BASE+"do_an_co_so_2_vku/public/uploads/sliders/slider1.jpg");
        manquangcao.add(Utils.URL_BASE+"do_an_co_so_2_vku/public/uploads/sliders/slider2.jpg");
        manquangcao.add(Utils.URL_BASE+"do_an_co_so_2_vku/public/uploads/sliders/slider3.jpg");
        for (int i =0; i<manquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(manquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            flipper.addView(imageView);
        }
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        flipper.setInAnimation(slide_in);
        flipper.setOutAnimation(slide_out);

        System.out.println("Thuc Phung changed successfully!");
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void initUI() {
        listView = findViewById(R.id.listview);
        rcvProduct = findViewById(R.id.rcvProductNew);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rcvProduct.setLayoutManager(layoutManager);
        rcvProduct.setHasFixedSize(true);
        toolbar = findViewById(R.id.toolbar);
        flipper = findViewById(R.id.flipper);
        view = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        badge = findViewById(R.id.menu_sl);
        frameLayout = findViewById(R.id.framegiohang);
        frameSearch = findViewById(R.id.search);

        mList = new ArrayList<>();
        mangSpmoi = new ArrayList<>();
        if (Utils.manggiohang == null){
            Utils.manggiohang = new ArrayList<>();
        }else {
            int totalItem = 0;
            for (int i=0; i<Utils.manggiohang.size(); i++){
                totalItem = totalItem+ Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(giohang);
            }
        });

        frameSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int totalItem = 0;
        for (int i=0; i<Utils.manggiohang.size(); i++){
            totalItem = totalItem+ Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi!= null && wifi.isConnected()) || (mobile!= null && mobile.isConnected())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}