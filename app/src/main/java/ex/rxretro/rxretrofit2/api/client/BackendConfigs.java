package ex.rxretro.rxretrofit2.api.client;

import java.io.IOException;

import ex.rxretro.rxretrofit2.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by panji on 6/19/2017.
 */

public class BackendConfigs {
    private BackendConfigs() { }
    private static final String APIARY_SERVER = "http://private-8c8b8f-rxretroex.apiary-mock.com/";
    public static final String API_PATH = "api/v1/";

    public static String getApiaryServer() {
        return APIARY_SERVER;
    }

    public static OkHttpClient interceptHttpRequest() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(
                        BuildConfig.DEBUG
                                ? HttpLoggingInterceptor.Level.BODY
                                : HttpLoggingInterceptor.Level.NONE
                );

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("APP_KEY", "MY SUPER SECRET APP KEY")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    public static OkHttpClient noHeadersInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(
                        BuildConfig.DEBUG
                                ? HttpLoggingInterceptor.Level.HEADERS
                                : HttpLoggingInterceptor.Level.NONE
                );

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }
}