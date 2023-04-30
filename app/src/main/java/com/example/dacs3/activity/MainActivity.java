package com.example.dacs3.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
    NavigationView navigationView;
    List<LoaiSp> mList;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    List<SapPhamMoi> mangSpmoi;
    SanPhamMoiAdapter SpAdapter;
    NotificationBadge badge;
    FrameLayout frameLayout, frameSearch;
    TextView tv_name, tv_email;
    View headerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        System.out.print("link: " + Utils.URL_BASE + "  ");

        initUI();
        ActionBar();
        ActionViewFlipper();
        if (isConnected(this)) {
            ActionViewFlipper();
            getLoaiSanPham();
            getSpMoi();

        } else {
            Toast.makeText(this, "Không có internet, Vui lòng kết nối internet", Toast.LENGTH_SHORT).show();
        }
    }



    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()) {
                                mangSpmoi = sanPhamMoiModel.getResult();
                                SpAdapter = new SanPhamMoiAdapter(getApplicationContext(), mangSpmoi);
                                rcvProduct.setAdapter(SpAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(this, "Không kết nối được với Server" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanHang.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if (loaiSpModel.isSuccess()) {
                                mList = loaiSpModel.getResult();
                                loaiSpAdapter = new LoaiSpAdapter(mList, getApplicationContext());
                                listView.setAdapter(loaiSpAdapter);
                            }
                        }
                ));
    }


    private void ActionViewFlipper() {
        List<String> manquangcao = new ArrayList<>();
        manquangcao.add(Utils.URL_BASE + "do_an_co_so_2_vku/public/uploads/sliders/slider1.jpg");
        manquangcao.add(Utils.URL_BASE + "do_an_co_so_2_vku/public/uploads/sliders/slider2.jpg");
        manquangcao.add(Utils.URL_BASE + "do_an_co_so_2_vku/public/uploads/sliders/slider3.jpg");
        for (int i = 0; i < manquangcao.size(); i++) {
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
        navigationView = findViewById(R.id.navigationview);
        headerView = navigationView.getHeaderView(0);
        tv_name = headerView.findViewById(R.id.header_name);
        tv_email = headerView.findViewById(R.id.header_email);

        // Set the text of the TextView objects

        tv_name.setText(Utils.user_current.getUsername());
        tv_email.setText(Utils.user_current.getEmail());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.trangchinh:
                        // Xử lý khi người dùng chọn item trang chính
                        Intent trangchu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(trangchu);
                        break;

                    case R.id.donhang:
                        // Xử lý khi người dùng chọn item đơn hàng
                        Intent donhang = new Intent(getApplicationContext(), XemDonActivity.class);
                        startActivity(donhang);
                        break;

                    case R.id.giay:
                        // Xử lý khi người dùng chọn item giày
                        Intent giay = new Intent(getApplicationContext(), GiayActivity.class);
                        giay.putExtra("menu_id", 37);
                        startActivity(giay);
                        break;

                    case R.id.nam:
                        // Xử lý khi người dùng chọn item nam
                        Intent nam = new Intent(getApplicationContext(), GiayActivity.class);
                        nam.putExtra("menu_id", 39);
                        startActivity(nam);
                        break;

                    case R.id.nu:
                        // Xử lý khi người dùng chọn item nữ
                        Intent nu = new Intent(getApplicationContext(), GiayActivity.class);
                        nu.putExtra("menu_id", 38);
                        startActivity(nu);
                        break;

                    case R.id.lienhe:
                        // Xử lý khi người dùng chọn item liên hệ
                        Intent lienhe = new Intent(getApplicationContext(), LienHeActivity.class);
                        startActivity(lienhe);
                        break;

                    case R.id.thongtin:
                        // Xử lý khi người dùng chọn item thông tin
                        Intent thongtin = new Intent(getApplicationContext(), ThongTinActivity.class);
                        startActivity(thongtin);
                        break;

                    case R.id.dangxuat:
                        // Xử lý khi người dùng chọn item đăng xuất
                        Intent dangxuat = new Intent(getApplicationContext(), DangNhapActivity.class);
                        dangxuat.putExtra("Set", 1);
                        startActivity(dangxuat);
                        break;

                }
                return false;
            }
        });

        mList = new ArrayList<>();
        mangSpmoi = new ArrayList<>();
        if (Utils.manggiohang == null) {
            Utils.manggiohang = new ArrayList<>();
        } else {
            int totalItem = 0;
            for (int i = 0; i < Utils.manggiohang.size(); i++) {
                totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
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


    }

    @Override
    protected void onResume() {
        super.onResume();
        int totalItem = 0;
        for (int i = 0; i < Utils.manggiohang.size(); i++) {
            totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Xử lý khi người dùng nhấn nút tìm kiếm trên bàn phím
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("key", query.toString().trim());
                startActivity(intent);
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý khi người dùng nhập văn bản vào SearchView
                // Nội dung tìm kiếm được truyền vào tham số newText

                return false;
            }
        });


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                return false;

            }
        });
        return false;
    }

}

