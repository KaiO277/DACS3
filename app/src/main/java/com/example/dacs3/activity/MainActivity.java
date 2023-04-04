package com.example.dacs3.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.dacs3.R;
import com.example.dacs3.adapter.LoaiSpAdapter;
import com.example.dacs3.model.LoaiSp;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvProduct;
    private Toolbar toolbar;
    private ViewFlipper flipper;
    private NavigationView view;
    private ListView listView;
    private DrawerLayout drawerLayout;
    LoaiSpAdapter loaiSpAdapter;
    List<LoaiSp> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        ActionBar();
        ActionViewFlipper();
    }

    private void ActionViewFlipper() {
        List<String> manquangcao = new ArrayList<>();
        manquangcao.add("http://192.168.195.12/do_an_co_so_2_vku/public/uploads/sliders/slider1.jpg");
        manquangcao.add("http://192.168.195.12/do_an_co_so_2_vku/public/uploads/sliders/slider2.jpg");
        manquangcao.add("http://192.168.195.12/do_an_co_so_2_vku/public/uploads/sliders/slider3.jpg");
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
        toolbar = findViewById(R.id.toolbar);
        flipper = findViewById(R.id.flipper);
        view = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        mList = new ArrayList<>();
        loaiSpAdapter = new LoaiSpAdapter(mList, getApplicationContext());
        listView.setAdapter(loaiSpAdapter);
    }

}