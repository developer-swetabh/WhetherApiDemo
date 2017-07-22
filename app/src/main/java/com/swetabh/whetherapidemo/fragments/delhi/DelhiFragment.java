package com.swetabh.whetherapidemo.fragments.delhi;


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
import com.swetabh.whetherapidemo.WhetherApplication;
import com.swetabh.whetherapidemo.adapter.WhetherAdapter;
import com.swetabh.whetherapidemo.models.List;
import com.swetabh.whetherapidemo.models.WhetherResponse;
import com.swetabh.whetherapidemo.network.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DelhiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DelhiFragment extends Fragment implements MainContract.DelhiView {

    private static final String DELHI_ID = "1273294";
    @BindView(R.id.progress_bar_whether)
    ProgressBar mProgressbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private Context mContext;
    private MainContract.DelhiPresenter mPresenter;
    private MainContract.MainCommunicator mCommunicator;
    private Subscription subscription;
    private java.util.List<List> mDataList;


    public DelhiFragment() {
        // Required empty public constructor
    }

    public static DelhiFragment newInstance() {
        DelhiFragment fragment = new DelhiFragment();
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
    public void setPresenter(MainContract.DelhiPresenter presenter) {
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
        WhetherApplication application = WhetherApplication.get(mContext);
        subscription = RetrofitClient.getInstance().getWhetherReport(DELHI_ID, WhetherApiKey.WHETHER_API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<WhetherResponse>() {
                    @Override
                    public void onCompleted() {
                        hideProgress();
                        WhetherAdapter adapter = new WhetherAdapter(mContext, mDataList);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                        mRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgress();
                    }

                    @Override
                    public void onNext(WhetherResponse whetherResponse) {
                        mDataList = whetherResponse.getList();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(subscription);
    }
}
