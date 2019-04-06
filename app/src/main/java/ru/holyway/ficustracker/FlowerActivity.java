package ru.holyway.ficustracker;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ru.holyway.ficustracker.adapter.FlowersAdapter;
import ru.holyway.ficustracker.client.RestClient;
import ru.holyway.ficustracker.entity.FlowerData;

public class FlowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public class LoadFlowerTask extends AsyncTask<Void, Void, FlowerData> {

        private final Activity activity;
        private final Integer flowerId;

        LoadFlowerTask(Integer flowerId, Activity activity) {
            this.flowerId = flowerId;
            this.activity = activity;
        }

        @Override
        protected FlowerData doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                return RestClient.getInstance().getFlowerById(flowerId);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final FlowerData flower) {

            if (flower != null) {
                TextView textView = activity.findViewById(R.id.flower_name);
                textView.setText(flower.getName());
            } else {
                Snackbar.make(activity.findViewById(R.id.addLayout), "Проблема подключения к серверу", Snackbar.LENGTH_LONG)
                        .setAction("Ок", null).setActionTextColor(activity.getResources().getColor(R.color.colorAccent)).show();
            }
        }

        @Override
        protected void onCancelled() {
        }
    }

}
