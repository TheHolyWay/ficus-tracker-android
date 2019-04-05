package ru.holyway.ficustracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import ru.holyway.ficustracker.security.UserService;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREF_DATA_SET_CHANGED = "com.holyway.android.justswipeit.datasetchanged";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUser();

        if (UserService.getInstance().getUserName() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (UserService.getInstance().getUserName() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initUser() {
        final String username = getSharedPreferences(SHARED_PREF_DATA_SET_CHANGED, MODE_PRIVATE).getString("username", null);
        final String password = getSharedPreferences(SHARED_PREF_DATA_SET_CHANGED, MODE_PRIVATE).getString("password", null);

        if (username != null && password != null) {
            UserService.getInstance().registerUser(username, password, null);
        }
    }
}
