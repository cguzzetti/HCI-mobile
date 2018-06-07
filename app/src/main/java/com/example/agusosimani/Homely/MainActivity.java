package com.example.agusosimani.Homely;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static SectionsPageAdapter mSectionsPagerAdapter;
    private static BottomNavigationView mNavigationBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_routines:
                    fragment = new RoutinesTab();
                    break;
                case R.id.navigation_home:
                    fragment = new HomeTab();
                    break;
                case R.id.navigation_devices:
                    fragment = new DevicesTab();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadFragment(new HomeTab());


//        mSectionsPagerAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mNavigationBar=findViewById(R.id.navigation);
        mNavigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);//this

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}
