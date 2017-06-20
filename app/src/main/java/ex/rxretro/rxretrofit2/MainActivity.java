package ex.rxretro.rxretrofit2;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import ex.rxretro.rxretrofit2.adapter.ContentListAdapter;
import ex.rxretro.rxretrofit2.api.client.listeners.IDataCallback;
import ex.rxretro.rxretrofit2.api.client.responses.NewsFeedResponse;
import ex.rxretro.rxretrofit2.models.DataFetchModel;
import ex.rxretro.rxretrofit2.models.RxDataFetchModel;
import ex.rxretro.rxretrofit2.pojo.Content;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "MA";
    @BindView(R.id.recycler_view)
    RecyclerView mRvContent;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    private ContentListAdapter mAdapter;

    private DataFetchModel mDataModel;
    private RxDataFetchModel mRxDataModel;

    @Override
    protected int withLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mDataModel = new DataFetchModel();
        mRxDataModel = new RxDataFetchModel();

        mRefreshLayout.setOnRefreshListener(this);

        mRvContent.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvContent.setLayoutManager(layoutManager);

        mAdapter = new ContentListAdapter(new ArrayList<Content>());
        mRvContent.setAdapter(mAdapter);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(true);
        //mDataModel.getNewsFeed("token", 1, NEWS_FEED_REQUEST_CALLBACK);
        /*mRxDataModel.loginThenGetNewsFeedThenParseIt(
                "sometext@mail.com",
                "somepassword",
                "somedeviceid",
                CONTENTS_REQUEST_CALLBACK
        );*/

        mRxDataModel.getNewsFeedAndErrorHappened(new IDataCallback<List<Content>>() {
            @Override
            public void onSuccess(List<Content> data) {
                showProgress(false);
                Log.d(TAG, "onSuccess: THIS SHOULD BE NEVER HAPPENED");
                mAdapter.setDataSource(data);
            }

            @Override
            public void onFailure(Throwable throwable) {
                showProgress(false);
                Toast.makeText(mActivity, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        /*mRxDataModel.getNewsFeedAndFilterIt(
                "sometokenkey",
                1,
                "now",
                new IFilterListener<Content>() {
                    @Override
                    public void onNext(Content data) {
                        Log.d(TAG, "onNext: " + data.toString());
                        mAdapter.addContent(data);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        showProgress(false);
                        Toast.makeText(mActivity, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        showProgress(false);
                        mAdapter.notifyDataSetChanged();
                        Log.d(TAG, "onComplete: triggered");
                    }
                }
        );*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxDataModel.release();
    }

    private final IDataCallback<NewsFeedResponse> NEWS_FEED_REQUEST_CALLBACK = new IDataCallback<NewsFeedResponse>() {
        @Override
        public void onSuccess(NewsFeedResponse data) {
            mAdapter.setDataSource(data.toContents());
            showProgress(false);
        }

        @Override
        public void onFailure(Throwable throwable) {
            Toast.makeText(mActivity, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            showProgress(false);
        }
    };

    private final IDataCallback<List<Content>> CONTENTS_REQUEST_CALLBACK = new IDataCallback<List<Content>>() {
        @Override
        public void onSuccess(List<Content> data) {
            mAdapter.setDataSource(data);
            showProgress(false);
        }

        @Override
        public void onFailure(Throwable throwable) {
            Toast.makeText(mActivity, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            showProgress(false);
        }
    };

    private void showProgress(final boolean show) {
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(show);
            }
        });
    }
}
