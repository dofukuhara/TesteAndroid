package br.com.fukuhara.douglas.investmentapp.mvp;

import android.os.Bundle;

import br.com.fukuhara.douglas.investmentapp.domain.Screen;

public interface AssetContract {

    /*
        All the interfaces for 'Financial Asset' Model needed by MVP architectural pattern
     */

    interface Model {

        // Method to retrieve Financial Asset from EndPoint via REST
        void getFinancialAssetsJson();
    }

    interface Presenter {

        // Update UI with Financial Asset information after retrieving it from EndPoint
        void updateUiWithFinancialAsset(Screen screen);
        // Notify end-user in case of a failure in retrieving Financial Asset info from EndPoint
        void notifyAboutDownloadError(Throwable error);
        // Method that will start the Json request and update the UI
        void start(Bundle savedInstanceState);
        // Return the screen object, so that it can be saved in the savedInstanceState bundle
        Screen getScreen();
        // If this function was requested, it would make this investment into user balance account
        void investInThis(String investmentName);
    }

    interface View {
        // Bundle KEY to store/retrive screen info
        String SAVED_STATE_BUNDLE_SCREEN_KEY = "screen_key";

        // Set the a presenter instance to View obj
        void setPresenter(AssetContract.Presenter presenter);
        // Will send the obj parsed from Json in order to be displayed in the screen
        void updateUiWithScreen(Screen screen);
        // Function used by PRESENTER in order to send a message to end-user via Toast
        void notifyUserWithToastMessage(String message);
        void notifyUserWithToastMessage(int stringId, String... message);

        void hideLayoutUntilCompletion();
        void internetConnectionError();
    }
}
