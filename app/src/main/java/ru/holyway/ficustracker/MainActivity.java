package ru.holyway.ficustracker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import ru.holyway.ficustracker.adapter.FlowersAdapter;
import ru.holyway.ficustracker.client.RestClient;
import ru.holyway.ficustracker.entity.FlowerData;
import ru.holyway.ficustracker.security.UserService;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FlowersAdapter.OnItemClickListener {

    public static final String SHARED_PREF_DATA_SET_CHANGED = "com.holyway.android.justswipeit.datasetchanged";

    private FlowersAdapter flowersAdapter;

    private MainActivity mainActivity = this;

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
                Intent intent = new Intent(getApplicationContext(), AddFlower.class);
                startActivity(intent);

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final RecyclerView rv = findViewById(R.id.rv);
        new LoadFlowersTask(rv, this).execute((Void) null);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(-1)) {
                    new LoadFlowersTaskAway(rv, mainActivity).execute((Void) null);
                    Snackbar.make(rv, "Список обновлен", Snackbar.LENGTH_SHORT).show();
                } else if (!recyclerView.canScrollVertically(1)) {
                    //
                } else if (dy < 0) {
                    //
                } else if (dy > 0) {
                    //
                }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.flowers_item) {
            // Handle the camera action
        } else if (id == R.id.advice_item) {

        } else if (id == R.id.settings_item) {

        } else if (id == R.id.about_item) {

        } else if (id == R.id.logout_item) {
            UserService.getInstance().registerUser(null, null, null);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(View view, FlowerData flower) {
        Intent intent = new Intent(this, FlowerActivity.class);
        intent.putExtra("ru.holyway.ficustracker.flower_id", flower.getId());
        startActivity(intent);
    }


    public class LoadFlowersTask extends AsyncTask<Void, Void, List<FlowerData>> {

        private final RecyclerView rv;
        private final MainActivity activity;

        LoadFlowersTask(RecyclerView rv, MainActivity activity) {
            this.rv = rv;
            this.activity = activity;
        }

        @Override
        protected List<FlowerData> doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                return RestClient.getInstance().getMyFlowers();
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<FlowerData> flowers) {

            if (flowers != null) {
                LinearLayoutManager llm = new LinearLayoutManager(activity);
                rv.setLayoutManager(llm);
                flowersAdapter = new FlowersAdapter(flowers);
                rv.setAdapter(flowersAdapter);
                flowersAdapter.setOnItemClickListener(activity);
            } else {
                Snackbar.make(activity.findViewById(R.id.addLayout), "Проблема подключения к серверу", Snackbar.LENGTH_LONG)
                        .setAction("Ок", null).setActionTextColor(activity.getResources().getColor(R.color.colorAccent)).show();
            }
        }

        @Override
        protected void onCancelled() {
        }
    }

    public class LoadFlowersTaskAway extends LoadFlowersTask {

        LoadFlowersTaskAway(RecyclerView rv, MainActivity activity) {
            super(rv, activity);
        }

        @Override
        protected List<FlowerData> doInBackground(Void... params) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                return null;
            }
            return super.doInBackground(params);
        }
    }
}
