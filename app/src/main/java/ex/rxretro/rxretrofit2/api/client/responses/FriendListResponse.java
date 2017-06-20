package ex.rxretro.rxretrofit2.api.client.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ex.rxretro.rxretrofit2.pojo.Content;
import ex.rxretro.rxretrofit2.pojo.Friend;

/**
 * Created by panji on 6/19/2017.
 */

public class FriendListResponse extends BaseResponse implements Content.ParseableContents {
    @SerializedName("data")
    private List<Friend> friends;

    public List<Friend> getFriends() {
        return friends;
    }

    @Override
    public List<Content> toContents() {
        if (friends != null) {
            List<Content> contents = new ArrayList<>();
            for (Friend friend : friends) {
                contents.add(new Content(
                        friend.getDisplayName(),
                        "FID-" + friend.getId() + "-" + friend.getDateTime()
                ));
            }
            return contents;
        } else return Collections.emptyList();
    }
}
