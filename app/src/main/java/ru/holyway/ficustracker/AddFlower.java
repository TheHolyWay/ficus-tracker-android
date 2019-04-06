package ru.holyway.ficustracker;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.holyway.ficustracker.client.RestClient;
import ru.holyway.ficustracker.entity.Flower;
import ru.holyway.ficustracker.entity.FlowerType;

public class AddFlower extends AppCompatActivity {

    private AddFlower flowerAdd = this;

    private Map<String, Integer> types = new HashMap<>();
    private List<String> sensors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flower);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Добавление растения");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new LoadListsTask(this).execute((Void) null);

        Button addFlowerButton = findViewById(R.id.addFlower);
        addFlowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name = findViewById(R.id.addName);
                Spinner sensorId = findViewById(R.id.sensor);
                Spinner typeid = findViewById(R.id.type);

                new AddFlowerTask(flowerAdd, String.valueOf(name.getText()),
                        Integer.valueOf(sensorId.getSelectedItem().toString()),
                        types.get(typeid.getSelectedItem()))
                        .execute((Void) null);
            }
        });
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class LoadListsTask extends AsyncTask<Void, Void, Boolean> {

        private AddFlower addFlower;

        public LoadListsTask(AddFlower addFlower) {
            this.addFlower = addFlower;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                List<FlowerType> flowerTypes = RestClient.getInstance().getTypes();
                for (FlowerType flowerType : flowerTypes) {
                    types.put(flowerType.getName(), flowerType.getId());
                }

                List<Integer> sensors = RestClient.getInstance().getSensors();
                for (Integer sensorId : sensors) {
                    addFlower.sensors.add(sensorId.toString());
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            // showProgress(false);

            if (success) {
//                finish();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);

                Spinner typesSpinner = findViewById(R.id.type);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        addFlower, android.R.layout.simple_spinner_item, new ArrayList<>(types.keySet()));

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                typesSpinner.setAdapter(adapter);

                Spinner sensorSpinner = findViewById(R.id.sensor);

                ArrayAdapter<String> adapterSensor = new ArrayAdapter<>(
                        addFlower, android.R.layout.simple_spinner_item, sensors);

                adapterSensor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sensorSpinner.setAdapter(adapterSensor);
            } else {
                Snackbar.make(addFlower.findViewById(R.id.addLayout), "Проблема подключения к серверу", Snackbar.LENGTH_LONG)
                        .setAction("Ок", null).setActionTextColor(addFlower.getResources().getColor(R.color.colorAccent)).show();
            }
        }

        @Override
        protected void onCancelled() {
            //showProgress(false);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class AddFlowerTask extends AsyncTask<Void, Void, Boolean> {

        private AddFlower addFlower;

        private String name;
        private Integer sensor;
        private Integer type;

        public AddFlowerTask(AddFlower addFlower, String name, Integer sensor, Integer type) {
            this.addFlower = addFlower;
            this.name = name;
            this.sensor = sensor;
            this.type = type;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                Flower flower = new Flower(name, type, sensor);
                RestClient.getInstance().addFlower(flower);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            // showProgress(false);

            if (success) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            } else {
                Snackbar.make(addFlower.findViewById(R.id.addLayout), "Проблема подключения к серверу", Snackbar.LENGTH_LONG)
                        .setAction("Ок", null).setActionTextColor(addFlower.getResources().getColor(R.color.colorAccent)).show();
            }
        }

        @Override
        protected void onCancelled() {
            //showProgress(false);
        }
    }

}
