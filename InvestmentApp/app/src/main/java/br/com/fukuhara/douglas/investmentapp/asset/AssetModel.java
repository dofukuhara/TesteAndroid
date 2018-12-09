package br.com.fukuhara.douglas.investmentapp.asset;

import br.com.fukuhara.douglas.investmentapp.domain.Fund;
import br.com.fukuhara.douglas.investmentapp.mvp.AssetContract;
import br.com.fukuhara.douglas.investmentapp.network.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetModel implements AssetContract.Model {

    private AssetContract.Presenter mPresenter;

    // Public constructor from AssetModel class
    public AssetModel(AssetContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getFinancialAssetsJson() {
        Call<Fund> call = NetworkUtils.buildRetroFitRequestForFinancialAsset(
                NetworkUtils.BASE_URL);

        call.enqueue(new Callback<Fund>() {
            @Override
            public void onResponse(Call<Fund> call, Response<Fund> response) {
                mPresenter.updateUiWithFinancialAsset(response.body().getScreen());
            }

            @Override
            public void onFailure(Call<Fund> call, Throwable error) {
                mPresenter.notifyAboutDownloadError(error);
            }
        });
    }
}
