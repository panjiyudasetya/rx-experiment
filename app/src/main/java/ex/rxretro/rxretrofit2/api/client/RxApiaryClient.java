package ex.rxretro.rxretrofit2.api.client;

import java.util.Collections;
import java.util.List;

import ex.rxretro.rxretrofit2.api.client.listeners.IDataCallback;
import ex.rxretro.rxretrofit2.api.client.listeners.IFilterListener;
import ex.rxretro.rxretrofit2.api.client.responses.DataUserResponse;
import ex.rxretro.rxretrofit2.api.client.responses.NewsFeedResponse;
import ex.rxretro.rxretrofit2.pojo.Content;
import ex.rxretro.rxretrofit2.pojo.User;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by panji on 6/19/2017.
 */

public class RxApiaryClient {
    private static RxApiaryClient sRxApiaryClient;
    private RxApiaryServices mApiService;

    private RxApiaryClient() {
        mApiService = new Retrofit.Builder()
                .baseUrl(BackendConfigs.getApiaryServer())
                .client(BackendConfigs.interceptHttpRequest())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RxApiaryServices.class);
    }

    public static RxApiaryClient getInstance() {
        if (sRxApiaryClient == null) {
            sRxApiaryClient = new RxApiaryClient();
        }
        return sRxApiaryClient;
    }

    /**
     * Rx Java request to login into system
     * @param email     user token
     * @param password  page
     * @param deviceId  device Id
     * @param callback  {@link IDataCallback<NewsFeedResponse>} expected data callback
     */
    public Disposable login(String email,
                            String password,
                            String deviceId,
                            final IDataCallback<DataUserResponse> callback) {

        return mApiService.login(email, password, deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DataUserResponse>() {
                    @Override
                    public void accept(DataUserResponse dataUserResponse) throws Exception {
                        if (dataUserResponse != null){
                            if(dataUserResponse.isOk()) callback.onSuccess(dataUserResponse);
                            else callback.onFailure(new Throwable(dataUserResponse.getMessage()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onFailure(throwable);
                    }
                });
    }

    /**
     * Rx Java request to get news feed data stream from API
     * @param token     user token
     * @param page      page
     * @param callback  {@link IDataCallback<List<Content>} expected data callback
     */
    public Disposable getNewsFeed(String token,
                                  int page,
                                  final IDataCallback<List<Content>> callback) {

        return mApiService.getNewsFeed(token, page)
                .flatMap(new Function<NewsFeedResponse, ObservableSource<List<Content>>>() {
                    @Override
                    public ObservableSource<List<Content>> apply(NewsFeedResponse newsFeedResponse) throws Exception {
                        if (newsFeedResponse == null || !newsFeedResponse.isOk()){
                            throw new Exception("Unable to get contents from news feed response");
                        }
                        return Observable.just(parseObject(newsFeedResponse));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Content>>() {
                    @Override
                    public void accept(List<Content> contents) throws Exception {
                        callback.onSuccess(contents);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onFailure(throwable);
                    }
                });
    }

    /**
     * Chaining request example using Rx Java
     * @param email     user email address
     * @param password  user password
     * @param deviceId  device Id
     * @param callback  {@link IDataCallback<List<Content>>} expected data callback
     */
    public Disposable loginAndGetNewsFeed(String email,
                                          String password,
                                          String deviceId,
                                          final IDataCallback<List<Content>> callback) {

        return mApiService.login(email, password, deviceId)
                .flatMap(new Function<DataUserResponse, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(DataUserResponse dataUserResponse) throws Exception {
                        User user = dataUserResponse.getData();
                        return mApiService.getNewsFeed(user.getToken(), 1);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (o instanceof Content.ParseableContents)
                            callback.onSuccess(((Content.ParseableContents) o).toContents());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onFailure(throwable);
                    }
                });
    }

    /**
     * Another chaining request example using Rx Java
     * @param email     user email address
     * @param password  user password
     * @param deviceId  device Id
     * @param callback  {@link IDataCallback<List<Content>>} expected data callback
     */
    public Disposable loginThenGetNewsFeedThenParseIt(String email,
                                                      String password,
                                                      String deviceId,
                                                      final IDataCallback<List<Content>> callback) {

        return mApiService.login(email, password, deviceId)
                .flatMap(new Function<DataUserResponse, ObservableSource<NewsFeedResponse>>() {
                    @Override
                    public ObservableSource<NewsFeedResponse> apply(DataUserResponse dataUserResponse) throws Exception {
                        User user = dataUserResponse.getData();
                        return mApiService.getNewsFeed(user.getToken(), 1);
                    }
                })
                .flatMap(new Function<NewsFeedResponse, ObservableSource<List<Content>>>() {
                    @Override
                    public ObservableSource<List<Content>> apply(NewsFeedResponse newsFeedResponse) throws Exception {
                        return Observable.just(parseObject(newsFeedResponse));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Content>>() {
                    @Override
                    public void accept(List<Content> contents) throws Exception {
                        callback.onSuccess(contents);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onFailure(throwable);
                    }
                });
    }

    /**
     * Another chaining request example using Rx Java to filter retrieved feed by specific words
     * @param token     token
     * @param page      page
     * @param filter    filter by
     * @param callback  {@link IFilterListener<Content>} expected data callback
     */
    public Disposable getNewsFeedAndFilterIt(String token,
                                              int page,
                                              final String filter,
                                              final IFilterListener<Content> callback) {

        return mApiService.getNewsFeed(token, page)
                .flatMap(new Function<NewsFeedResponse, ObservableSource<List<Content>>>() {
                    @Override
                    public ObservableSource<List<Content>> apply(NewsFeedResponse newsFeedResponse) throws Exception {
                        return Observable.just(parseObject(newsFeedResponse));
                    }
                })
                .flatMap(new Function<List<Content>, ObservableSource<Content>>() {
                    @Override
                    public ObservableSource<Content> apply(List<Content> contents) throws Exception {
                        return Observable.fromIterable(contents);
                    }
                })
                .filter(new Predicate<Content>() {
                    @Override
                    public boolean test(Content content) throws Exception {
                        return content.getTitle().contains(filter) || content.getContent().contains(filter);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        callback.onComplete();
                    }
                })
                .subscribe(new Consumer<Content>() {
                    @Override
                    public void accept(Content content) throws Exception {
                        callback.onNext(content);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onFailure(throwable);
                    }
                });

    }

    /**
     * Another chaining request containing an errors
     * @param callback  {@link IDataCallback<List<Content>>} expected data callback
     */
    public Disposable getNewsFeedAndErrorHappened(final IDataCallback<List<Content>> callback) {
        return mApiService.login("email@mail.com", "password", "deviceid")
                .flatMap(new Function<DataUserResponse, ObservableSource<NewsFeedResponse>>() {
                    @Override
                    public ObservableSource<NewsFeedResponse> apply(DataUserResponse dataUserResponse) throws Exception {
                        if (true) throw new Exception("Try to break the chain request");
                        return mApiService.getNewsFeed("token", 1);
                    }
                })
                .flatMap(new Function<NewsFeedResponse, ObservableSource<List<Content>>>() {
                    @Override
                    public ObservableSource<List<Content>> apply(NewsFeedResponse newsFeedResponse) throws Exception {
                        return Observable.just(parseObject(newsFeedResponse));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Content>>() {
                    @Override
                    public void accept(List<Content> contents) throws Exception {
                        callback.onSuccess(contents);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onFailure(throwable);
                    }
                });

    }

    private List<Content> parseObject(Object object) {
        if (object instanceof Content.ParseableContents)
            return ((Content.ParseableContents) object).toContents();
        else return Collections.emptyList();
    }
}
