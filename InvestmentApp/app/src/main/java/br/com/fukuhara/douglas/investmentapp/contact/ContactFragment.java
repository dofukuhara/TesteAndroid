package br.com.fukuhara.douglas.investmentapp.contact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.fukuhara.douglas.investmentapp.R;
import br.com.fukuhara.douglas.investmentapp.mvp.ContactContract;
import butterknife.ButterKnife;

public class ContactFragment extends Fragment implements ContactContract.View {

    private ContactContract.Presenter mPresenter;

    // Empty constructor for AssetFragment class
    public ContactFragment() { }


    public static ContactFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void setPresenter(ContactContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.contact_fragment, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Requesting Presenter to get the Cells info, so that the UI can be properly populated
        mPresenter.start( savedInstanceState );
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // In case that we have the Cell information and the user is rotating the device,
        // let's save this info in the bundle, so that we can retrieve this information after
        // the device finishes the rotating action and save some REST call
        if (mPresenter.getCells() != null) {
            outState.putParcelableArrayList(SAVED_STATE_BUNDLE_CELLS_KEY, mPresenter.getCells());
        }

        super.onSaveInstanceState(outState);
    }
}
