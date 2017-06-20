package ex.rxretro.rxretrofit2.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by panji on 6/19/2017.
 */

public class Comment {
    @SerializedName("comment_id")
    private int id;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("url_avatar")
    private String urlAvatar;
    @SerializedName("content")
    private String content;
    @SerializedName("date_time")
    private String dateTime;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public String getContent() {
        return content;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", displayName='" + displayName + '\'' +
                ", urlAvatar='" + urlAvatar + '\'' +
                ", content='" + content + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (userId != comment.userId) return false;
        if (!displayName.equals(comment.displayName)) return false;
        if (!urlAvatar.equals(comment.urlAvatar)) return false;
        if (!content.equals(comment.content)) return false;
        return dateTime.equals(comment.dateTime);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + displayName.hashCode();
        result = 31 * result + urlAvatar.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + dateTime.hashCode();
        return result;
    }
}
