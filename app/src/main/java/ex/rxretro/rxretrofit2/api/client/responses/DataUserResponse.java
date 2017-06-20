package ex.rxretro.rxretrofit2.api.client.responses;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ex.rxretro.rxretrofit2.pojo.Content;
import ex.rxretro.rxretrofit2.pojo.User;

/**
 * Created by panji on 6/19/2017.
 */

public class DataUserResponse extends BaseResponse implements Content.ParseableContents {
    @SerializedName("data")
    private User data;

    public User getData() {
        return data;
    }

    @Override
    public List<Content> toContents() {
        if (data != null) {
            return Arrays.asList(new Content(
                    data.getDisplayName(),
                    data.getBirthday()
            ));
        } else return Collections.emptyList();
    }
}
