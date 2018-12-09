package br.com.fukuhara.douglas.investmentapp.network;

import br.com.fukuhara.douglas.investmentapp.domain.Fund;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointApiService {

    // EndPoint to get Financial Assets information via REST (RetroFit2)
    @GET("/fund.json")
    Call<Fund> listOfFinancialAsset();
}
