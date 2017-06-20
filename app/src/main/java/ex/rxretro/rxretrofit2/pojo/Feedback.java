package ex.rxretro.rxretrofit2.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by panji on 6/19/2017.
 */

public class Feedback {
    @SerializedName("likes_id")
    private int id;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("url_avatar")
    private String urlAvatar;
    @SerializedName("emoticon_type")
    private String emoticonTye;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public String getEmoticonTye() {
        return emoticonTye;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", userId=" + userId +
                ", urlAvatar='" + urlAvatar + '\'' +
                ", emoticonTye='" + emoticonTye + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (id != feedback.id) return false;
        if (userId != feedback.userId) return false;
        if (!urlAvatar.equals(feedback.urlAvatar)) return false;
        return emoticonTye.equals(feedback.emoticonTye);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + urlAvatar.hashCode();
        result = 31 * result + emoticonTye.hashCode();
        return result;
    }
}
