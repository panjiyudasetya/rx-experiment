package ex.rxretro.rxretrofit2.api.client.listeners;

/**
 * Created by panji on 6/19/2017.
 */

public interface IDataCallback<T> {
    void onSuccess(T data);
    void onFailure(Throwable throwable);
}
