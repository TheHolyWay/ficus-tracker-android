package ru.holyway.ficustracker;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ru.holyway.ficustracker.adapter.WarnAdapter;
import ru.holyway.ficustracker.client.RestClient;
import ru.holyway.ficustracker.entity.FlowerData;
import ru.holyway.ficustracker.entity.WarnItem;

public class FlowerActivity extends AppCompatActivity {

    private Timer autoUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Integer flowerID = getIntent().getIntExtra("ru.holyway.ficustracker.flower_id", -1);
        new LoadFlowerTask(flowerID, this).execute((Void) null);

        final Activity activity = this;

        autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        new LoadFlowerTask(flowerID, activity).execute((Void) null);
                    }
                });
            }
        }, 0, 5000); // updates each 40 secs

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
                TextView nameTv = activity.findViewById(R.id.flower_name);
                nameTv.setText(flower.getName());

                TextView tempTv = activity.findViewById(R.id.temperature);
                tempTv.setText(flower.getSensorData().getTemperature() + "°C");

                TextView humTv = activity.findViewById(R.id.humidity);
                humTv.setText(flower.getSensorData().getSoilMoisture() + "%");

                TextView light = activity.findViewById(R.id.light);
                light.setText(flower.getSensorData().getLight() + "lm");

                ProgressBar lightProgress = findViewById(R.id.lightProgress);
                lightProgress.setProgress(Math.round(flower.getSensorData().getLight()));
                if (flower.getSensorData().getLight() > flower.getMaxLight() || flower.getSensorData().getLight() < flower.getMinLight() ) {
                    lightProgress.getProgressDrawable().setColorFilter(
                            Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                } else {
                    lightProgress.getProgressDrawable().setColorFilter(
                            Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                }

                ProgressBar tempProgress = findViewById(R.id.tempProgress);
                tempProgress.setProgress(Math.round(flower.getSensorData().getTemperature()));
                if (flower.getSensorData().getTemperature() > flower.getMaxTemp() || flower.getSensorData().getTemperature() < flower.getMinTemp() ) {
                    tempProgress.getProgressDrawable().setColorFilter(
                            Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                } else {
                    tempProgress.getProgressDrawable().setColorFilter(
                            Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                }

                ProgressBar humProgress = findViewById(R.id.humProgress);
                humProgress.setProgress(Math.round(flower.getSensorData().getSoilMoisture()));
                if (flower.getSensorData().getSoilMoisture() > flower.getMaxHum() || flower.getSensorData().getSoilMoisture() < flower.getMinHum() ) {
                    humProgress.getProgressDrawable().setColorFilter(
                            Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                } else {
                    humProgress.getProgressDrawable().setColorFilter(
                            Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                }

                List<WarnItem> warnItems = new ArrayList<>();

                if (flower.getProblems() != null && !flower.getProblems().isEmpty()) {
                    for (String problem : flower.getProblems()) {
                        warnItems.add(new WarnItem(WarnItem.Type.PROBLEM, problem));
                    }
                } else if (flower.getWarnings() != null && !flower.getWarnings().isEmpty()) {
                    for (String problem : flower.getWarnings()) {
                        warnItems.add(new WarnItem(WarnItem.Type.WARNING, problem));
                    }
                } else if (flower.getRecommendations() != null && !flower.getRecommendations().isEmpty()) {
                    for (String problem : flower.getRecommendations()) {
                        warnItems.add(new WarnItem(WarnItem.Type.NOTIFY, problem));
                    }
                }
                RecyclerView rv = findViewById(R.id.rv);
                LinearLayoutManager llm = new LinearLayoutManager(activity);
                rv.setLayoutManager(llm);
                WarnAdapter warnAdapter = new WarnAdapter(warnItems);
                rv.setAdapter(warnAdapter);
            } else {
                Snackbar.make(activity.findViewById(R.id.rv), "Проблема подключения к серверу", Snackbar.LENGTH_LONG)
                        .setAction("Ок", null).setActionTextColor(activity.getResources().getColor(R.color.colorAccent)).show();
            }
        }

        @Override
        protected void onCancelled() {
        }
    }

}
