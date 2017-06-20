package ex.rxretro.rxretrofit2.api.client;

import ex.rxretro.rxretrofit2.api.client.responses.DataUserResponse;
import ex.rxretro.rxretrofit2.api.client.responses.FriendListResponse;
import ex.rxretro.rxretrofit2.api.client.responses.NewsFeedResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by panji on 6/19/2017.
 */

public interface ApiaryServices {
    @FormUrlEncoded
    @POST(BackendConfigs.API_PATH + "user/login")
    Call<DataUserResponse> login(@Field("email") String email,
                                 @Field("password") String password,
                                 @Field("device_id") String deviceId);

    @GET(BackendConfigs.API_PATH + "user/profile")
    Call<DataUserResponse> getProfile(@Query("token") String token);

    @GET(BackendConfigs.API_PATH + "newsfeed/list")
    Call<NewsFeedResponse> getNewsFeed(@Query("token") String token,
                                       @Query("page") int page);

    @FormUrlEncoded
    @POST(BackendConfigs.API_PATH + "newsfeed/search")
    Call<NewsFeedResponse> searchNewsFeed(@Field("token") String token,
                                          @Field("keywords") String keywords,
                                          @Field("page") int page);

    @GET(BackendConfigs.API_PATH + "user/friends/list")
    Call<FriendListResponse> getFriendList(@Query("token") String token,
                                           @Field("page") int page);

    @GET(BackendConfigs.API_PATH + "user/friends/search")
    Call<FriendListResponse> searchFriends(@Query("token") String token,
                                           @Query("keywords") int keywords,
                                           @Query("page") int page);
}
