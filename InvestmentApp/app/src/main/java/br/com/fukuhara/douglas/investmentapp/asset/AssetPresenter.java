package br.com.fukuhara.douglas.investmentapp.asset;

import android.os.Bundle;

import br.com.fukuhara.douglas.investmentapp.R;
import br.com.fukuhara.douglas.investmentapp.domain.Screen;
import br.com.fukuhara.douglas.investmentapp.mvp.AssetContract;

public class AssetPresenter implements AssetContract.Presenter {

    // View instance, so that Presenter can notify and update the UI
    private AssetContract.View mAssetView;
    // Model instance, so that Presenter can fetch for Financial Asset's Json
    private AssetContract.Model mAssetModel;
    // Instance of the data to be drawn in the UI
    private Screen mScreen;

    // Public constructor for AssetPresenter class
    public AssetPresenter(AssetContract.View assetView) {
        mAssetView = assetView;
        mAssetModel = new AssetModel(this);

        mAssetView.setPresenter(this);
    }

    @Override
    public void start(Bundle savedInstanceState) {
        // If the Screen info is saved in bundle (offline cache), we can retrive the data from it, without the need
        // of re-fetching for this data again when rotating the device
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(AssetContract.View.SAVED_STATE_BUNDLE_SCREEN_KEY)) {

            Screen screen = savedInstanceState.getParcelable(AssetContract.View.SAVED_STATE_BUNDLE_SCREEN_KEY);
            updateUiWithFinancialAsset(screen);
        } else {

            // But, in case that the bundle is not or if does not contains a value stored in our KEY,
            // let's fetch this info from EndPoint
            mAssetModel.getFinancialAssetsJson();
        }
    }

    @Override
    public Screen getScreen() {
        return mScreen;
    }

    @Override
    public void investInThis(String investmentName) {
        mAssetView.notifyUserWithToastMessage(R.string.fund_applied, investmentName);
    }

    // Return from RetroFit's callback function when the request was successful
    @Override
    public void updateUiWithFinancialAsset(Screen screen) {
        mScreen = screen;

        mAssetView.updateUiWithScreen(screen);
    }

    // Return from RetroFit's callback function when the request got a failure
    @Override
    public void notifyAboutDownloadError(Throwable error) {
        // TODO: Implement the user notification about a download failure of Screen info
    }
}
