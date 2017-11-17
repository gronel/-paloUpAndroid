package biz.fastgroup.apps.paloup.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import biz.fastgroup.apps.paloup.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
