package ex.rxretro.rxretrofit2.api.client;

import ex.rxretro.rxretrofit2.api.client.listeners.IDataCallback;
import ex.rxretro.rxretrofit2.api.client.responses.DataUserResponse;
import ex.rxretro.rxretrofit2.api.client.responses.NewsFeedResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by panji on 6/19/2017.
 */

public class ApiaryClient {
    private static ApiaryClient sApiaryClient;
    private ApiaryServices mApiServices;

    private ApiaryClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BackendConfigs.getApiaryServer())
                .client(BackendConfigs.interceptHttpRequest())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiServices = retrofit.create(ApiaryServices.class);
    }

    public static ApiaryClient getInstance() {
        if (sApiaryClient == null) {
            sApiaryClient = new ApiaryClient();
        }
        return sApiaryClient;
    }

    public void login(String email,
                      String password,
                      String deviceId,
                      final IDataCallback<DataUserResponse> callback) {
        mApiServices.login(email, password, deviceId)
                .enqueue(new Callback<DataUserResponse>() {
                    @Override
                    public void onResponse(Call<DataUserResponse> call,
                                           retrofit2.Response<DataUserResponse> response) {
                        // int httpCode = response.code();
                        // or do something more if need regarding stream handler from API
                        if (response.isSuccessful()) {
                            DataUserResponse data = response.body();
                            if (data != null) {
                                if(data.isOk()) callback.onSuccess(data);
                                else callback.onFailure(new Throwable(data.getMessage()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DataUserResponse> call, Throwable t) {
                        callback.onFailure(t);
                    }
                });
    }

    public void getNewsFeed(String token,
                            int page,
                            final IDataCallback<NewsFeedResponse> callback) {

        mApiServices.getNewsFeed(token, page)
                .enqueue(new Callback<NewsFeedResponse>() {
                    @Override
                    public void onResponse(Call<NewsFeedResponse> call,
                                           retrofit2.Response<NewsFeedResponse> response) {
                        if (response.isSuccessful()) {
                            NewsFeedResponse data = response.body();
                            if (data != null) {
                                if(data.isOk()) callback.onSuccess(data);
                                else callback.onFailure(new Throwable(data.getMessage()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsFeedResponse> call, Throwable t) {
                        callback.onFailure(t);
                    }
                });
    }
}
