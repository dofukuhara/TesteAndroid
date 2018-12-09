package br.com.fukuhara.douglas.investmentapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.fukuhara.douglas.investmentapp.R;
import br.com.fukuhara.douglas.investmentapp.domain.Info;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AssetInfoAdapter extends RecyclerView.Adapter<AssetInfoAdapter.AssetInfoViewHolder> {

    private ArrayList<Info> mInfoArrayList;


    public AssetInfoAdapter(ArrayList<Info> infoArrayList) {
        this.mInfoArrayList = infoArrayList;
    }

    @NonNull
    @Override
    public AssetInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.asset_info;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new AssetInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssetInfoViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mInfoArrayList.size();
    }

    class AssetInfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_fund_info)
        TextView tvFundInfo;
        @BindView(R.id.tv_fund_value)
        TextView tvFundValue;

        public AssetInfoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind (int listIndex) {
            tvFundInfo.setText(mInfoArrayList.get(listIndex).getName());
            tvFundValue.setText(mInfoArrayList.get(listIndex).getData());
        }
    }
}
