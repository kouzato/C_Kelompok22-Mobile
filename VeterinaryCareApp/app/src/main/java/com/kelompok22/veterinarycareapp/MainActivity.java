package com.kelompok22.veterinarycareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.kelompok22.veterinarycareapp.fragment.ArticleFragment;
import com.kelompok22.veterinarycareapp.fragment.AskFragment;
import com.kelompok22.veterinarycareapp.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_nav);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_ask));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_article));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //inisiasi fragment
                Fragment fragment = null;

                //cek konodisi
                switch (item.getId()){
                    case 1:
                        //saat id = 1
                        //inisiasi Ask fragment
                        fragment = new AskFragment();
                        break;
                    case 2:
                        //saat id = 1
                        //inisiasi Home fragment
                        fragment = new HomeFragment();
                        break;
                    case 3:
                        //saat id = 1
                        //inisiasi Article fragment
                        fragment = new ArticleFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        //set notifikasi count
//        bottomNavigation.setCount(1,"10");
        //set home fragmen initially selected
        bottomNavigation.show(2,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
    }

    private void loadFragment(Fragment fragment) {
        //mengganti fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}