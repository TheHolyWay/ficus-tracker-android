package ru.holyway.ficustracker.client;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.holyway.ficustracker.entity.Flower;
import ru.holyway.ficustracker.entity.FlowerData;
import ru.holyway.ficustracker.entity.FlowerType;
import ru.holyway.ficustracker.security.UserService;

public class RestClient {

    private static final RestClient INSTANCE = new RestClient();

    private static final String BACKEND_SERVER_URL = "http://213.183.48.72/api/v1/";

    private final RestAPI service;

    public RestClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(BACKEND_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(RestAPI.class);
    }

    public String authorize(final String userName, final String password) throws IOException {
        byte[] data = (userName + ":" + password).getBytes();
        String authHeader = "Basic " + Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\n", "");
        return service.authorize(authHeader).execute().body().getToken();
    }

    public List<FlowerData> getMyFlowers() throws IOException {
        byte[] data = (UserService.getInstance().getUserName() + ":" + UserService.getInstance().getUserPassword()).getBytes();
        String authHeader = "Basic " + Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\n", "");
        return service.getAllFlowers(authHeader).execute().body();
    }

    public List<FlowerType> getTypes() throws IOException {
        byte[] data = (UserService.getInstance().getUserName() + ":" + UserService.getInstance().getUserPassword()).getBytes();
        String authHeader = "Basic " + Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\n", "");
        return service.getAllTypes(authHeader).execute().body();
    }

    public List<Integer> getSensors() throws IOException {
        byte[] data = (UserService.getInstance().getUserName() + ":" + UserService.getInstance().getUserPassword()).getBytes();
        String authHeader = "Basic " + Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\n", "");
        return service.getAllSensors(authHeader).execute().body();
    }

    public FlowerData addFlower(Flower flower) throws IOException {
        byte[] data = (UserService.getInstance().getUserName() + ":" + UserService.getInstance().getUserPassword()).getBytes();
        String authHeader = "Basic " + Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\n", "");
        return service.addFlower(authHeader, flower).execute().body();
    }

    public FlowerData getFlowerById(Integer id) throws IOException {
        byte[] data = (UserService.getInstance().getUserName() + ":" + UserService.getInstance().getUserPassword()).getBytes();
        String authHeader = "Basic " + Base64.encodeToString(data, Base64.DEFAULT).replaceAll("\\n", "");
        return service.getFlower(authHeader, id.toString()).execute().body();
    }

    public static RestClient getInstance() {
        return INSTANCE;
    }
}
