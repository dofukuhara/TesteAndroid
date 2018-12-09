package br.com.fukuhara.douglas.investmentapp.contact;

import br.com.fukuhara.douglas.investmentapp.domain.Cell;
import br.com.fukuhara.douglas.investmentapp.mvp.ContactContract;
import br.com.fukuhara.douglas.investmentapp.network.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactModel implements ContactContract.Model {

    private ContactContract.Presenter mContactPresenter;

    public ContactModel(ContactContract.Presenter contactPresenter) {
        this.mContactPresenter = contactPresenter;
    }

    @Override
    public void getCellsDefinitionJson() {
        Call<Cell> call = NetworkUtils.buildRetroFitRequestForLayoutConstraint(
                NetworkUtils.BASE_URL);

        call.enqueue(new Callback<Cell>() {
            @Override
            public void onResponse(Call<Cell> call, Response<Cell> response) {
                mContactPresenter.inflateUiWithCellInformation(response.body().getCells());
            }

            @Override
            public void onFailure(Call<Cell> call, Throwable error) {
                mContactPresenter.notifyAboutDownloadError(error);
            }
        });
    }
}
