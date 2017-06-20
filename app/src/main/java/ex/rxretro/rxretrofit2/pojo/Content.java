package ex.rxretro.rxretrofit2.pojo;

import java.util.List;

/**
 * Created by panji on 6/20/2017.
 */

public class Content {
    private String title;
    private String content;

    public interface ParseableContents {
        List<Content> toContents();
    }

    public Content(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
