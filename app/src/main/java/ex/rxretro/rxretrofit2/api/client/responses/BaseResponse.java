package ex.rxretro.rxretrofit2.api.client.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by panji on 6/19/2017.
 */

public class BaseResponse {
    protected static final Gson GSON = new Gson();
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public boolean isOk() {
        return status >= 200 && status < 300;
    }
}
