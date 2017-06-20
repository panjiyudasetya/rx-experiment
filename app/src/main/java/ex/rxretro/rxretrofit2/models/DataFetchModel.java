package ex.rxretro.rxretrofit2.models;

import ex.rxretro.rxretrofit2.api.client.ApiaryClient;
import ex.rxretro.rxretrofit2.api.client.listeners.IDataCallback;
import ex.rxretro.rxretrofit2.api.client.responses.NewsFeedResponse;

/**
 * Created by panji on 6/20/2017.
 */

public class DataFetchModel {

    /**
     * Fetch news feed data stream from Api
     * @param token     User token
     * @param page      page
     * @param callback  {@link IDataCallback<NewsFeedResponse>} expected data callback
     */
    public void getNewsFeed(String token,
                            int page,
                            final IDataCallback<NewsFeedResponse> callback) {

        ApiaryClient.getInstance().getNewsFeed(token, page, callback);
    }
}
