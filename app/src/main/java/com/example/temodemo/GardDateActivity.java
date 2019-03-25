package com.example.temodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GardDateActivity extends AppCompatActivity {
    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_toolbar;
    private ImageView icon_image;
    private TextView content_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gardview_layout_date);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID,0);
        toolbar = findViewById(R.id.toolbar);
        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        icon_image = findViewById(R.id.icon_image);
        content_text = findViewById(R.id.content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsing_toolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(icon_image);
        String fruitContent = generateFruitContent(fruitName);
        content_text.setText(fruitContent);
    }

    private String generateFruitContent(String fruitName) {

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 500; i++) {
            stringBuffer.append(fruitName);
        }
        return stringBuffer.toString();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
