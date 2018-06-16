package com.example.agusosimani.Homely;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.agusosimani.Homely.device.DevicesTab;

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
        API.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeTab());
        mNavigationBar=findViewById(R.id.navigation);
        mNavigationBar.setSelectedItemId(R.id.navigation_home);
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


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.help_id:
                        Intent helpIntent = new Intent(getApplicationContext(), Help.class);
                        startActivity(helpIntent);
                        return true;
                    case R.id.settings_id:
//                        Intent settingsIntent = new Intent(getApplicationContext(), NotificationSettings.class);
//                        startActivity(settingsIntent);
//                        return true;
                }
                return true;
            }
        });
    }


}
