package br.com.fukuhara.douglas.investmentapp.contact;

import android.os.Bundle;

import java.util.ArrayList;

import br.com.fukuhara.douglas.investmentapp.domain.CellItem;
import br.com.fukuhara.douglas.investmentapp.mvp.ContactContract;

public class ContactPresenter implements ContactContract.Presenter {

    private ContactContract.View mContactView;
    private ContactContract.Model mContactModel;
    private ArrayList<CellItem> mCells;

    public ContactPresenter(ContactContract.View contactView) {
        this.mContactView = contactView;
        this.mContactModel = new ContactModel(this);

        this.mContactView.setPresenter( this );
    }

    @Override
    public void inflateUiWithCellInformation(ArrayList<CellItem> cells) {
        mCells = cells;

        mContactView.updateUiWithCellsInfo(mCells);
    }

    @Override
    public void notifyAboutDownloadError(Throwable error) {
        mContactView.internetConnectionError();
    }

    @Override
    public void start(Bundle savedInstanceState) {
        mContactView.hideLayoutUntilCompletion();
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(ContactContract.View.SAVED_STATE_BUNDLE_CELLS_KEY)) {

            mCells = savedInstanceState.getParcelableArrayList(
                    ContactContract.View.SAVED_STATE_BUNDLE_CELLS_KEY);

            mContactView.updateUiWithCellsInfo(mCells);

        } else {
            mContactModel.getCellsDefinitionJson();
        }
    }

    @Override
    public ArrayList<CellItem> getCells() {
        return this.mCells;
    }

    @Override
    public void validateParams(String name, String email, String phone) {

        boolean isNameOk = name != null && ContactFieldValidator.isNameValid(name);
        boolean isEmailOk = email != null && ContactFieldValidator.isEmailValid(email);
        boolean isPhoneOk = phone != null && ContactFieldValidator.isPhoneValid(phone);

        mContactView.paramsValidated(isNameOk, isEmailOk, isPhoneOk);
    }
}
