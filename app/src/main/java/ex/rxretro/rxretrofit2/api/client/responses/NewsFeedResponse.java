package ex.rxretro.rxretrofit2.api.client.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ex.rxretro.rxretrofit2.pojo.Content;
import ex.rxretro.rxretrofit2.pojo.Feed;

/**
 * Created by panji on 6/19/2017.
 */

public class NewsFeedResponse extends BaseResponse implements Content.ParseableContents {
    @SerializedName("data")
    private List<Feed> newsFeed;

    public List<Feed> getNewsFeed() {
        return newsFeed;
    }

    @Override
    public List<Content> toContents() {
        if (newsFeed != null) {
            List<Content> contents = new ArrayList<>();
            for (Feed feed : newsFeed) {
                contents.add(new Content(
                        feed.getTitleContent(),
                        feed.getContent()
                ));
            }
            return contents;
        } else return Collections.emptyList();
    }
}