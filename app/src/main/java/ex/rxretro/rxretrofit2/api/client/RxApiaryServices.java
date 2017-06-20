package ex.rxretro.rxretrofit2.api.client;
import ex.rxretro.rxretrofit2.api.client.responses.DataUserResponse;
import ex.rxretro.rxretrofit2.api.client.responses.FriendListResponse;
import ex.rxretro.rxretrofit2.api.client.responses.NewsFeedResponse;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static ex.rxretro.rxretrofit2.api.client.BackendConfigs.API_PATH;

/**
 * Created by panji on 6/19/2017.
 */

public interface RxApiaryServices {
    @FormUrlEncoded
    @POST(API_PATH + "user/login")
    Observable<DataUserResponse> login(@Field("email") String email,
                                       @Field("password") String password,
                                       @Field("device_id") String deviceId);
    @GET(API_PATH + "user/profile")
    Observable<DataUserResponse> getProfile(@Query("token") String token);

    @GET(API_PATH + "newsfeed/list")
    Observable<NewsFeedResponse> getNewsFeed(@Query("token") String token,
                                             @Query("page") int page);

    @FormUrlEncoded
    @POST(API_PATH + "newsfeed/search")
    Observable<NewsFeedResponse> searchNewsFeed(@Field("token") String token,
                                                @Field("keywords") String keywords,
                                                @Field("page") int page);

    @GET(API_PATH + "user/friends/list")
    Observable<FriendListResponse> getFriendList(@Query("token") String token,
                                                 @Field("page") int page);

    @GET(API_PATH + "user/friends/search")
    Observable<FriendListResponse> searchFriends(@Query("token") String token,
                                                 @Query("keywords") int keywords,
                                                 @Query("page") int page);
}
