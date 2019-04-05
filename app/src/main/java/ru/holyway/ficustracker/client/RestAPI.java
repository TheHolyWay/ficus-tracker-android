package ru.holyway.ficustracker.client;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.holyway.ficustracker.entity.Flower;

public interface RestAPI {

    @POST("/users/authorize")
    Call<String> authorize(@Header("Authorization") String auth) throws IOException;

    @GET("/flowers")
    Call<List<Flower>> getAllFlowers(@Header("Authorization") String auth) throws IOException;

}
