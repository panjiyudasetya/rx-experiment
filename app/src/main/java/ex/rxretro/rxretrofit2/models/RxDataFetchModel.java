package ex.rxretro.rxretrofit2.models;

import java.util.List;

import ex.rxretro.rxretrofit2.api.client.RxApiaryClient;
import ex.rxretro.rxretrofit2.api.client.listeners.IDataCallback;
import ex.rxretro.rxretrofit2.api.client.listeners.IFilterListener;
import ex.rxretro.rxretrofit2.api.client.responses.NewsFeedResponse;
import ex.rxretro.rxretrofit2.pojo.Content;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by panji on 6/20/2017.
 */

public class RxDataFetchModel {
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * Rx Java request to get news feed data stream from API
     * @param token     user token
     * @param page      page
     * @param callback  {@link IDataCallback<NewsFeedResponse>} expected data callback
     */
    public void getNewsFeed(String token,
                            int page,
                            final IDataCallback<List<Content>> callback) {

        mCompositeDisposable.add(
                RxApiaryClient
                        .getInstance()
                        .getNewsFeed(token, page, callback)
        );
    }

    /**
     * Chaining request example using Rx Java
     * @param email     user email address
     * @param password  user password
     * @param deviceId  device Id
     * @param callback  {@link IDataCallback<List<Content>>} expected data callback
     */
    public void loginAndGetNewsFeed(String email,
                                    String password,
                                    String deviceId,
                                    final IDataCallback<List<Content>> callback) {

        mCompositeDisposable.add(
                RxApiaryClient
                        .getInstance()
                        .loginAndGetNewsFeed(email, password, deviceId, callback)
        );
    }

    /**
     * Chaining request example using Rx Java to get news feed and parse it to
     * {@link List<Content>}
     * @param email     user email address
     * @param password  user password
     * @param deviceId  device Id
     * @param callback  {@link IDataCallback<List<Content>>} expected data callback
     */
    public void loginThenGetNewsFeedThenParseIt(String email,
                                    String password,
                                    String deviceId,
                                    final IDataCallback<List<Content>> callback) {

        mCompositeDisposable.add(
                RxApiaryClient
                        .getInstance()
                        .loginThenGetNewsFeedThenParseIt(email, password, deviceId, callback)
        );
    }

    /**
     * Another chaining request example using Rx Java to filter retrieved feed by specific words
     * @param token     token
     * @param page      page
     * @param filter    filter by
     * @param callback  {@link IFilterListener <Content>} expected data callback
     */
    public void getNewsFeedAndFilterIt(String token,
                                              int page,
                                              final String filter,
                                              final IFilterListener<Content> callback) {
        mCompositeDisposable.add(
                RxApiaryClient
                        .getInstance()
                        .getNewsFeedAndFilterIt(token, page, filter, callback)
        );
    }

    /**
     * Another chaining request containing an errors
     * @param callback  {@link IDataCallback<List<Content>>} expected data callback
     */
    public void getNewsFeedAndErrorHappened(final IDataCallback<List<Content>> callback) {
        mCompositeDisposable.add(
                RxApiaryClient
                        .getInstance()
                        .getNewsFeedAndErrorHappened(callback)
        );
    }

    /**
     * Releasing all thread reference from memory
     */
    public void release() {
        mCompositeDisposable.dispose();
    }
}
