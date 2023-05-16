package com.android.testmvvm.test12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.testmvvm.R;
import com.android.testmvvm.test12.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}