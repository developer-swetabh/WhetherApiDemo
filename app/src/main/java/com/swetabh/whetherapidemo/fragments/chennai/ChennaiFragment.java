package com.swetabh.whetherapidemo.fragments.chennai;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.swetabh.whetherapidemo.MainContract;
import com.swetabh.whetherapidemo.R;
import com.swetabh.whetherapidemo.WhetherApiKey;
import com.swetabh.whetherapidemo.adapter.WhetherAdapter;
import com.swetabh.whetherapidemo.models.WhetherResponse;
import com.swetabh.whetherapidemo.network.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChennaiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChennaiFragment extends Fragment implements MainContract.ChennaiView {


    private static final String CHENNAI_ID = "1273294";
    @BindView(R.id.progress_bar_whether)
    ProgressBar mProgressbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private Context mContext;
    private MainContract.ChennaiPresenter mPresenter;
    private MainContract.MainCommunicator mCommunicator;


    public ChennaiFragment() {
        // Required empty public constructor
    }


    public static ChennaiFragment newInstance() {
        ChennaiFragment fragment = new ChennaiFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.layout_common_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.checkNetworkConnectivity(mContext);
        }
    }

    @Override
    public void setPresenter(MainContract.ChennaiPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setCommunicator(MainContract.MainCommunicator communicator) {
        mCommunicator = communicator;
    }

    @Override
    public void updateActionBar() {

    }


    @Override
    public void internetConnected() {
        showProgress();
        RetrofitClient.getInstance()
                .getWhetherReport(CHENNAI_ID, WhetherApiKey.WHETHER_API_KEY)
                .enqueue(new Callback<WhetherResponse>() {
                    @Override
                    public void onResponse(Call<WhetherResponse> call, Response<WhetherResponse> response) {
                        hideProgress();
                        WhetherAdapter adapter = new WhetherAdapter(mContext, response.body().getList());
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                        mRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<WhetherResponse> call, Throwable t) {
                        hideProgress();
                    }
                });
    }

    @Override
    public void internetNotConnected() {
        hideEveryThing();
        Toast.makeText(mContext, getString(R.string.no_internet_available), Toast.LENGTH_LONG).show();
    }

    private void hideEveryThing() {
        mProgressbar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void showProgress() {
        mProgressbar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void hideProgress() {
        mProgressbar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
