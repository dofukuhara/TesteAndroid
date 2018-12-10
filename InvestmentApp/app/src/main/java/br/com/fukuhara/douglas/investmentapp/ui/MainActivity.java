package br.com.fukuhara.douglas.investmentapp.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.fukuhara.douglas.investmentapp.R;
import br.com.fukuhara.douglas.investmentapp.asset.AssetFragment;
import br.com.fukuhara.douglas.investmentapp.asset.AssetPresenter;
import br.com.fukuhara.douglas.investmentapp.contact.ContactFragment;
import br.com.fukuhara.douglas.investmentapp.contact.ContactPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private AssetPresenter mAssetPresenter;
    private ContactPresenter mContacPresenter;

    @BindView(R.id.btn_invest)
    Button btnInvest;
    @BindView(R.id.btn_contact)
    Button btnContact;

    // true --> Contact Screen
    // false --> Investment Screen
    private boolean mFlowControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Action Bar before it is drawn by setContentView
        getSupportActionBar().hide();

        // Inflate the Main Activity layout
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        if (savedInstanceState != null &&
                savedInstanceState.containsKey("flow-control")) {
            mFlowControl = savedInstanceState.getBoolean("flow-control");
        } else {
            mFlowControl = true;
        }

        changeButtonsLayout(mFlowControl);

        // Create an instance of AssetFragment
        AssetFragment assetFragment =
                (AssetFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        // Create an instance of ContactFragment
        ContactFragment contactFragment =
                (ContactFragment) getSupportFragmentManager().findFragmentById(R.id.contactFrame);

        // Initialize the fragment obj if necessary
        if (assetFragment == null) {
            // Create the fragment
            assetFragment = AssetFragment.newInstance();
        }

        // Initialize the fragment obj if necessary
        if (contactFragment == null) {
            // Create the fragment
            contactFragment = ContactFragment.newInstance();

            // Let's inflate the Contact Fragment, in order to set this as a default "entry page"
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.contactFrame, contactFragment, "fragment-contact");
            transaction.disallowAddToBackStack();
            transaction.remove(assetFragment);
            transaction.commit();

        }

        // Create the Presenter objects and pass the View instance to it
        mAssetPresenter = new AssetPresenter(assetFragment);
        mContacPresenter = new ContactPresenter(contactFragment);

        final ContactFragment finalContactFragment = contactFragment;
        final AssetFragment finalAssetFragment = assetFragment;

        // Creating the bottom buttons click listeners, so that the end-user can naviage between
        // the available Contact and Asset fragments
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.contactFrame, finalContactFragment, "fragment-contact");
                transaction.disallowAddToBackStack();
                transaction.remove(finalAssetFragment);
                transaction.commit();

                if (mFlowControl == false) {
                    mFlowControl = !mFlowControl;

                    changeButtonsLayout(mFlowControl);
                }

            }
        });

        btnInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.contentFrame, finalAssetFragment, "fragment-asset");
                transaction.disallowAddToBackStack();
                transaction.remove(finalContactFragment);
                transaction.commit();

                if (mFlowControl == true) {
                    mFlowControl = !mFlowControl;

                    changeButtonsLayout(mFlowControl);
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putBoolean("flow-control", mFlowControl);

        super.onSaveInstanceState(outState);
    }

    private void changeButtonsLayout(boolean flowControl) {
        if (flowControl) {
            btnInvest.setBackgroundColor(getResources().getColor(R.color.primaryLightColor));
            btnInvest.setTextColor(getResources().getColor(android.R.color.white));
            btnContact.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            btnContact.setTextColor(getResources().getColor(android.R.color.white));
        } else {
            btnInvest.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            btnInvest.setTextColor(getResources().getColor(android.R.color.white));
            btnContact.setBackgroundColor(getResources().getColor(R.color.primaryLightColor));
            btnContact.setTextColor(getResources().getColor(android.R.color.white));
        }
    }

}
