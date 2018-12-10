package br.com.fukuhara.douglas.investmentapp.asset;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import br.com.fukuhara.douglas.investmentapp.R;
import br.com.fukuhara.douglas.investmentapp.adapter.AssetDownAdapter;
import br.com.fukuhara.douglas.investmentapp.adapter.AssetInfoAdapter;
import br.com.fukuhara.douglas.investmentapp.domain.Screen;
import br.com.fukuhara.douglas.investmentapp.mvp.AssetContract;
import br.com.fukuhara.douglas.investmentapp.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AssetFragment extends Fragment implements AssetContract.View {

    private AssetContract.Presenter mPresenter;

    @BindView(R.id.assetFragmentConstraintLayout)
    ConstraintLayout assetFragmentConstraintLayout;
    @BindView(R.id.pg_asset)
    ProgressBar pgAsset;
    @BindView(R.id.asset_no_internet)
    TextView tvAssetNoInternet;

    @BindView(R.id.tv_invest_title)
    TextView tvInvestTitle;
    @BindView(R.id.tv_fund_name)
    TextView tvFundName;
    @BindView(R.id.tv_what_is)
    TextView tvWhatIs;
    @BindView(R.id.tv_fund_desc)
    TextView tvFundDesc;
    @BindView(R.id.tv_risk_title)
    TextView tvRiskTitle;

    @BindView(R.id.image_arrow_1)
    ImageView ImgArrow1;
    @BindView(R.id.image_arrow_2)
    ImageView ImgArrow2;
    @BindView(R.id.image_arrow_3)
    ImageView ImgArrow3;
    @BindView(R.id.image_arrow_4)
    ImageView ImgArrow4;
    @BindView(R.id.image_arrow_5)
    ImageView ImgArrow5;

    @BindView(R.id.tv_more_info)
    TextView tvMoreInfo;
    @BindView(R.id.tv_month_fund)
    TextView tvMonthFund;
    @BindView(R.id.tv_month_cdi)
    TextView tvMonthCdi;
    @BindView(R.id.tv_year_fund)
    TextView tvYearFund;
    @BindView(R.id.tv_year_cdi)
    TextView tvYearCdi;
    @BindView(R.id.tv_twelvemonths_fund)
    TextView tvTwelveMonthsFund;
    @BindView(R.id.tv_twelvemonths_cdi)
    TextView tvTwelveMonthsCdi;
    @BindView(R.id.lv_fund_info)
    RecyclerView lvFundInfo;
    @BindView(R.id.lv_fund_down)
    RecyclerView lvFundDown;
    @BindView(R.id.btn_invest)
    Button btnInvest;

    // Empty constructor for AssetFragment class
    public AssetFragment() {
    }

    public static AssetFragment newInstance() {

        Bundle args = new Bundle();

        AssetFragment fragment = new AssetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(AssetContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.asset_fragment, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Requesting Presenter to get the Screen info, so that the UI can be properly populated
        mPresenter.start(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // In case that we have a SCREEN information and the user is rotating the device,
        // let's save this info in the bundle, so that we can retrieve this information after
        // the device finishes the rotating action and save some REST call
        if (mPresenter.getScreen() != null) {
            outState.putParcelable(SAVED_STATE_BUNDLE_SCREEN_KEY, mPresenter.getScreen());
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public void updateUiWithScreen(final Screen screen) {
        tvInvestTitle.setText(screen.getTitle());
        tvFundName.setText(screen.getFundName());
        tvWhatIs.setText(screen.getWhatIs());
        tvFundDesc.setText(screen.getDefinition());
        tvRiskTitle.setText(screen.getRiskTitle());
        tvMoreInfo.setText(screen.getInfoTitle());

        tvMonthFund.setText(
                Utils.formatPercentage(screen.getMoreInfo().getMonth().getFund()));
        tvMonthCdi.setText(
                Utils.formatPercentage(screen.getMoreInfo().getMonth().getCdi()));
        tvYearFund.setText(
                Utils.formatPercentage(screen.getMoreInfo().getYear().getFund()));
        tvYearCdi.setText(
                Utils.formatPercentage(screen.getMoreInfo().getYear().getCdi()));
        tvTwelveMonthsFund.setText(
                Utils.formatPercentage(screen.getMoreInfo().getTwelvemonths().getFund()));
        tvTwelveMonthsCdi.setText(
                Utils.formatPercentage(screen.getMoreInfo().getTwelvemonths().getCdi()));


        lvFundInfo.setHasFixedSize(true);
        lvFundInfo.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        AssetInfoAdapter assetInfoAdapter = new AssetInfoAdapter(screen.getInfo());
        lvFundInfo.setAdapter(assetInfoAdapter);


        lvFundDown.setHasFixedSize(true);
        lvFundDown.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        AssetDownAdapter assetDownAdapter = new AssetDownAdapter(this, screen.getDownInfo());
        lvFundDown.setAdapter(assetDownAdapter);


        drawRiskLevel(Integer.valueOf(screen.getRisk()));


        btnInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.investInThis(screen.getFundName());
            }
        });

        unhideLayout();
    }

    public void drawRiskLevel(int risk) {

        ImgArrow1.setVisibility(
                risk == 1 ? View.VISIBLE : View.INVISIBLE);
        ImgArrow2.setVisibility(
                risk == 2 ? View.VISIBLE : View.INVISIBLE);
        ImgArrow3.setVisibility(
                risk == 3 ? View.VISIBLE : View.INVISIBLE);
        ImgArrow4.setVisibility(
                risk == 4 ? View.VISIBLE : View.INVISIBLE);
        ImgArrow5.setVisibility(
                risk == 5 ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void notifyUserWithToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyUserWithToastMessage(int stringId, String... message) {
        Toast.makeText(getActivity(), getString(stringId, message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLayoutUntilCompletion() {
        tvAssetNoInternet.setVisibility(View.INVISIBLE);
        pgAsset.setVisibility(View.VISIBLE);
        assetFragmentConstraintLayout.setVisibility(View.INVISIBLE);
    }

    private void unhideLayout() {
        tvAssetNoInternet.setVisibility(View.INVISIBLE);
        pgAsset.setVisibility(View.INVISIBLE);
        assetFragmentConstraintLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void internetConnectionError() {
        pgAsset.setVisibility(View.INVISIBLE);
        assetFragmentConstraintLayout.setVisibility(View.INVISIBLE);
        tvAssetNoInternet.setVisibility(View.VISIBLE);

    }
}
