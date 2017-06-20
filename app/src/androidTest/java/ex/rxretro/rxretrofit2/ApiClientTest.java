package ex.rxretro.rxretrofit2;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApiClientTest {
    private Context context;
    @Before
    public void setup() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
    }
    @After
    public void tearDown() throws Exception {
        context = null;
    }
}
