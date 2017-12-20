package com.passionvirus.appiconchanger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppIconChanger appIconChanger = new AppIconChanger();
        appIconChanger.setActivity(this);
        appIconChanger.addAlias("cloudy");
        appIconChanger.addAlias("drizzle");
        appIconChanger.setAlias("drizzle");
    }


}
