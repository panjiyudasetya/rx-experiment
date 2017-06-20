package ex.rxretro.rxretrofit2.api.client.listeners;

/**
 * Created by panji on 6/20/2017.
 */

public interface IFilterListener<T> {
    void onNext(T data);
    void onComplete();
    void onFailure(Throwable throwable);
}
