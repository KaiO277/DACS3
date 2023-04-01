package com.example.dacs3;

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
        manquangcao.add("https://lh3.googleusercontent.com/fife/AMPSemc35jt_0qOLGT9Fl6xm8k8AaLgNcNeWBgyXlNNo7RolEix7JXeedV08gxtXzOUAc3DUIwusqwFG5z9rUgEXK_Ni7bD7ljKxiD0ijWNSc6MRXsxFI8x2OYDVqS54ulUQBHQ06amBDbvnjbNP_Dht-1bWFLbwlN6lgzUQn5PUhaHHoaNlCkoyzKyOwFv3Ls9HiUTWmLWJzTuXqv3bjVbTu60qrO_P3putjXaUgFzf3WeKy9IMFgYkxxxKYZYeikRud9OD8H_j34YiP5RCNB7w1zvxALGKvrAfCXXnREQs7kS_g6FniNGioQnol4eMtGpUQvvsmZv3V5cypKVtFl8UM3NtmyDLWcUJmUru00KsKviqTkORP67aDjyZvhbzadO8QWB1AjHCUho7Tz5LnONcogRU9LAM1CceKi9NC-dJ67zPz-mTQOXtsyzIu21k_Gi3Jd5E1Br9UFk5KvVmW1tDcZaTLAujg2VO9IticYAWjPqjgvYeC3UDn3kkJ2PCGT5oX03BcdmjvcYi3vcvt8MnUkLLpdqpOsnYC6lqcHD4rsnR5bhuRq5iMUMH8d_84w3MW1MTW9O8eDHEaiTys0si8waDmxyWO9yVzayhI7liMWfj67E2WBor-TNjAfc3clBIbKEmtZTqAqC7ZamjwuhAvB26kCMLFUrVL14xpv3iXmdh7z91HmROwiT6wreM7jh-jKlr4UWP4WqGEWIDBFQtATrlLTluurzS7YDccPS4RMEOCdDy62YaOZmfBGWXZ7NnFLsSUsQ8j34s8H9lO0e-7dKIZZtSllDnoki8T5LLtVAxPcN9zBR8BB--zO6OMPgbRIZTvI-zmIxC6HtHL8lnUrsdtV5RfTVGtZmH7ipEADMnoZ3XkSaDGVpc3oJ4LRsj2zqOpd4wTf7llm-Z4IQ0pTVeANMU60Eg6ZlIdkONRqpTH1bQzDK6ERWgnurJjsGoyMTMAVRsTg=w1920-h892");
        manquangcao.add("https://lh3.googleusercontent.com/fife/AMPSemcDN8nQ4OzhYzgLXuJpxxU7Hokm8OvVHzlx8ybmmXZOLuR0E6fq1oQ8Yq-lxH8VmO2r7o1sPv5EIyjI7ROUQakJuNXPnl8JgvK6xG3PufIhwWg6QF5fDAREwK9umHIVMmPM4T8sdoU_exH8MCrwhfhDS8n_c0skr-PrnCnRI0vaMwIoiWBLPCVNK0LGtTwih7R6xda-Snv9IcgpOUOEeuvecwj_HWyMP5cRsW0qyoP1nFJjiSZ_cszTyEbmmrVUs2TahMGqiEW5F7fpa2CKncc9V4ALVYbsvXMArsRn7t665vgUixkeO8VGygLxckpahf9Lvv89w13ZaVY6IjmgVSOu5zmYSQUDpRZqcERSOK1eovqA8fLqlOwtOU8bfh_Vv_EgvZzRN8bRjigKFBRtB_4wA7Tz8WZTBQQ1nJG3sbvLBDCcC0goa3l-NVa7WXVcm1IXIEs3OG7axdWNaDAvwxcC2tvFalmifwWkIytp824RwxlCdFSwrDiS7IFI76eDruUgcmnfZ12F-h80labyxA6D0oQZSG2jCCp4kryuFlTo_K6xyvBuoZimAixW0cgwTjh0M6JyZfyElgO_TCxBcsVMnrTfIcdasLgjY1D6CIhJYuQLzbUavmeB-POndwMi9TZPVt7YFwArnyrP7TQ7lC_9XtravRygVF50N1W-wVZ7bzL-VwIoxEfupCNrP8xguHytGsgR2Z6LSckt53WcLxkerhhWyR9rd7fFTW4wMjMf08CVY40ayC-XctYxAcYKIwC8kSlH2EiLK3CliOEhy4KpzmVjl3HRlYBkDzvgDyU4y_T_0Dw0OUaUp_6bg1_X59a8I8_ON5lD1kDZMWetqgNs5kHMqdzrxDzU0vmdkopCP-yFc6j60mH4O5KG1XAI5SMTfQbPnJV_2BOHzMDDW0Wmh_KXEGu1WdtdOQ9gYSAmghh7c2UmiLoLz6uiG3120wtCOctp9w=w1920-h892");
        manquangcao.add("https://drive.google.com/file/d/10QJgU-wZ5HX_yilSO7FSfWpcK8qpv2PB/preview");
        manquangcao.add("https://drive.google.com/file/d/10VaKpvqbGeFeubCAVYoieDGhJBIQ51ue/view");
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
    }
}