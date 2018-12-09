package br.com.fukuhara.douglas.investmentapp.contact;

import android.os.Bundle;
import android.util.Log;

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

        // TODO: update UI with Cell content
    }

    @Override
    public void notifyAboutDownloadError(Throwable error) {
        // TODO: Implement the user notificaion about a download failure of Cells info
    }

    @Override
    public void start(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(ContactContract.View.SAVED_STATE_BUNDLE_CELLS_KEY)) {

            mCells = savedInstanceState.getParcelableArrayList(
                    ContactContract.View.SAVED_STATE_BUNDLE_CELLS_KEY);
            // TODO: update UI with Cell content

        } else {
            mContactModel.getCellsDefinitionJson();
        }
    }

    @Override
    public ArrayList<CellItem> getCells() {
        return this.mCells;
    }
}
