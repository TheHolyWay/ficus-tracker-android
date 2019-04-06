package ru.holyway.ficustracker.client;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.holyway.ficustracker.entity.Flower;
import ru.holyway.ficustracker.entity.FlowerData;
import ru.holyway.ficustracker.entity.FlowerType;
import ru.holyway.ficustracker.entity.Token;

public interface RestAPI {

    @POST("users/authorize")
    Call<Token> authorize(@Header("Authorization") String auth) throws IOException;

    @GET("flowers")
    Call<List<FlowerData>> getAllFlowers(@Header("Authorization") String auth) throws IOException;

    @GET("flowers/types")
    Call<List<FlowerType>> getAllTypes(@Header("Authorization") String auth) throws IOException;

    @GET("sensors")
    Call<List<Integer>> getAllSensors(@Header("Authorization") String auth) throws IOException;

    @POST("flowers")
    Call<FlowerData> addFlower(@Header("Authorization") String auth, @Body Flower flower) throws IOException;

    @GET("flowers/{id}")
    Call<FlowerData> getFlower(@Header("Authorization") String auth, @Path("id") String id) throws IOException;
}
