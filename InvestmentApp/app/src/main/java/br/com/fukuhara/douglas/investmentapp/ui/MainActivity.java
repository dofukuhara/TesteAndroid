package br.com.fukuhara.douglas.investmentapp.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.fukuhara.douglas.investmentapp.R;
import br.com.fukuhara.douglas.investmentapp.asset.AssetFragment;
import br.com.fukuhara.douglas.investmentapp.asset.AssetPresenter;

public class MainActivity extends AppCompatActivity {

    private AssetPresenter mAssetPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Action Bar before it is drawn by setContentView
        getSupportActionBar().hide();

        // Inflate the Main Activity layout
        setContentView(R.layout.activity_main);

        // Create an instance of AssetFragment
        AssetFragment assetFragment =
                (AssetFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        // Initialize the fragment obj if necessary
        if (assetFragment == null) {
            // Create the fragment
            assetFragment = AssetFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, assetFragment);
            transaction.commit();
        }

        // Create the Presenter obj and pass the View instance to it
        mAssetPresenter = new AssetPresenter(assetFragment);
    }
}
