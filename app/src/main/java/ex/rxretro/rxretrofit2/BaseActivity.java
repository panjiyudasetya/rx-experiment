package ex.rxretro.rxretrofit2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by panji on 6/20/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;
    protected Activity mActivity;
    protected abstract int withLayout();
    protected abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(withLayout());
        mUnbinder = ButterKnife.bind(this);
        mActivity = this;
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}