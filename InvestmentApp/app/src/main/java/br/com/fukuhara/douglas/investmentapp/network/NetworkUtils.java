package br.com.fukuhara.douglas.investmentapp.network;

import br.com.fukuhara.douglas.investmentapp.domain.Fund;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    // Base URL that the EndPoint will be built upon
    public static final String BASE_URL = "https://floating-mountain-50292.herokuapp.com/";

    // Method to create the Request to the FinancialAsset EndPoint
    public static Call<Fund> buildRetroFitRequestForFinancialAsset(String url) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        EndpointApiService client = retrofit.create(EndpointApiService.class);

        return client.listOfFinancialAsset();
    }
}
