package com.example.temodemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawerLayoutActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton floating;
    private RecyclerAdapter adapter;
    private GardBean[] gardBeans={new GardBean(R.mipmap.timg,"timg"),new GardBean(R.mipmap.timg_e,"timg_e")
    ,new GardBean(R.mipmap.timg_b,"timg_b"),new GardBean(R.mipmap.timg_c,"timg_c"),
            new GardBean(R.mipmap.timg_f,"timg_f")};
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<GardBean> gardBeanList=new ArrayList<>();
    private RecyclerView recycler_view;
    @SuppressLint({"WrongViewCast", "ResourceAsColor"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.navigation);
        floating=findViewById(R.id.floating);
        recycler_view= findViewById(R.id.recycler_view);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorAccent);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.more_z);
        }
        navigationView.setCheckedItem(R.id.nav_all);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();//将滑动菜单关闭
                switch (item.getItemId()){
                    case R.id.nav_task:
                        Toast.makeText(DrawerLayoutActivity.this,"Data deleted",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Data deleted",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(DrawerLayoutActivity.this,"Data deleted",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        initGardBean();
        adapter=new RecyclerAdapter(gardBeanList,DrawerLayoutActivity.this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(DrawerLayoutActivity.this,2);
        recycler_view.setLayoutManager(gridLayoutManager);
        recycler_view.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshGardBean();
            }
        });
    }

    private void initGardBean(){
        gardBeanList.clear();
        for (int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(gardBeans.length);
            gardBeanList.add(gardBeans[index]);
        }
    }

    private void refreshGardBean(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initGardBean();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

        }
        return true;
    }
}
