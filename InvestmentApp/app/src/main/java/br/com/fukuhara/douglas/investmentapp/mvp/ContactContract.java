package br.com.fukuhara.douglas.investmentapp.mvp;

import android.os.Bundle;

import java.util.ArrayList;

import br.com.fukuhara.douglas.investmentapp.domain.CellItem;

public interface ContactContract {

    /*
        All the interfaces for 'Contact Layout' Model needed by MVP architectural pattern
     */

    interface Model {

        // Method to retrieve Cells definition from EndPoint via REST
        void getCellsDefinitionJson();
    }

    interface Presenter {

        // Inflate and populate the UI after retrieving it's definition from EndPoint
        void inflateUiWithCellInformation(ArrayList<CellItem> cells);
        // Notify end-user in case of a failure in retrieving Financial Asset info from EndPoint
        void notifyAboutDownloadError(Throwable error);
        // Method that will start the Json request and update the UI
        void start(Bundle savedInstanceState);
        // Return the cell arraylist object, so that it can be saved in the savedInstanceState bundle
        ArrayList<CellItem> getCells();

        void validateParams(String name, String email, String phone);
    }

    interface View {
        // Bundle KEY to store/retrive screen info
        String SAVED_STATE_BUNDLE_CELLS_KEY = "cells_key";

        // Set the a presenter instance to View obj
        void setPresenter(Presenter presenter);
        // Update UI with the contents from cells JSON
        void updateUiWithCellsInfo(ArrayList<CellItem> cells);

        void hideLayoutUntilCompletion();
        void paramsValidated(boolean isNameOk, boolean isEmailOk, boolean isPhoneOk);
        void internetConnectionError();
    }
}
