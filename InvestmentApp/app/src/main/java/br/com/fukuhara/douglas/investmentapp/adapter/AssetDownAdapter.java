package br.com.fukuhara.douglas.investmentapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.fukuhara.douglas.investmentapp.R;
import br.com.fukuhara.douglas.investmentapp.domain.DownInfo;
import br.com.fukuhara.douglas.investmentapp.mvp.AssetContract;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AssetDownAdapter extends RecyclerView.Adapter<AssetDownAdapter.AssetDownViewHolder> {

    private ArrayList<DownInfo> mDownArrayList;
    private AssetContract.View mView;

    public AssetDownAdapter(AssetContract.View view, ArrayList<DownInfo> downArrayList) {
        this.mView = view;
        this.mDownArrayList = downArrayList;
    }

    @NonNull
    @Override
    public AssetDownViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.down_info;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new AssetDownViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssetDownViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mDownArrayList.size();
    }

    class AssetDownViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_down_info)
        TextView downInfo;
        @BindView(R.id.btn_download)
        Button downBtn;

        public AssetDownViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(final int listIndex) {

            downInfo.setText(mDownArrayList.get(listIndex).getName());

            /*
                It was not requested, and also all the data from JSON on this are equals to null.
                So, in here, I'm justing notifying end-user that there is "No info to be downloaded".
                In case that this function should need to be implemented, it is just a matter of
                creating a new method signature in Presenter contract, such as:
                mPresenter.downloadFileWithGivenURL(getData()) and implement it as well...
             */
            downBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String message = "File to be downloaded....";
                    if (mDownArrayList.get(listIndex).getData() == null ||
                            "null".equals(mDownArrayList.get(listIndex).getData())) {
                        message = "No info to be downloaded";
                    }
                    mView.notifyUserWithToastMessage(message);
                }
            });
        }
    }
}
