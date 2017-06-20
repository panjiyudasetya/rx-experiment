package ex.rxretro.rxretrofit2.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by panji on 6/19/2017.
 */

public class Friend {
    @SerializedName("user_id")
    private int id;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("url_avatar")
    private String urlAvatar;
    @SerializedName("date_time")
    private String dateTime;

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "dateTime='" + dateTime + '\'' +
                ", id=" + id +
                ", displayName='" + displayName + '\'' +
                ", urlAvatar='" + urlAvatar + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (id != friend.id) return false;
        if (displayName != null ? !displayName.equals(friend.displayName) : friend.displayName != null)
            return false;
        if (urlAvatar != null ? !urlAvatar.equals(friend.urlAvatar) : friend.urlAvatar != null)
            return false;
        return dateTime != null ? dateTime.equals(friend.dateTime) : friend.dateTime == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (urlAvatar != null ? urlAvatar.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }
}
