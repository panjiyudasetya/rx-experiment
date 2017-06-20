package ex.rxretro.rxretrofit2.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by panji on 6/19/2017.
 */

public class Feed {
    @SerializedName("feed_id")
    private int feedId;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("url_avatar")
    private String urlAvatar;
    @SerializedName("type")
    private String type;
    @SerializedName("total_viewer")
    private int totalViewer;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("title_content")
    private String titleContent;
    @SerializedName("content")
    private String content;
    @SerializedName("url_content")
    private String urlContent;
    @SerializedName("likes_by")
    private List<Feedback> likesBy;
    @SerializedName("viewer")
    private List<Feedback> viewer;
    @SerializedName("total_like")
    private int totalLike;
    @SerializedName("comments")
    private List<Comment> comments;
    @SerializedName("total_comment")
    private int totalComment;

    public int getFeedId() {
        return feedId;
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

    public String getType() {
        return type;
    }

    public int getTotalViewer() {
        return totalViewer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getTitleContent() {
        return titleContent;
    }

    public String getContent() {
        return content;
    }

    public String getUrlContent() {
        return urlContent;
    }

    public List<Feedback> getLikesBy() {
        return likesBy;
    }

    public List<Feedback> getViewer() {
        return viewer;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public int getTotalComment() {
        return totalComment;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "feedId=" + feedId +
                ", userId=" + userId +
                ", displayName='" + displayName + '\'' +
                ", urlAvatar='" + urlAvatar + '\'' +
                ", type='" + type + '\'' +
                ", totalViewer=" + totalViewer +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", titleContent='" + titleContent + '\'' +
                ", content='" + content + '\'' +
                ", urlContent='" + urlContent + '\'' +
                ", likesBy=" + likesBy +
                ", viewer=" + viewer +
                ", totalLike=" + totalLike +
                ", comments=" + comments +
                ", totalComment=" + totalComment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feed feed = (Feed) o;

        if (feedId != feed.feedId) return false;
        if (userId != feed.userId) return false;
        if (totalViewer != feed.totalViewer) return false;
        if (totalLike != feed.totalLike) return false;
        if (totalComment != feed.totalComment) return false;
        if (!displayName.equals(feed.displayName)) return false;
        if (!urlAvatar.equals(feed.urlAvatar)) return false;
        if (!type.equals(feed.type)) return false;
        if (!createdAt.equals(feed.createdAt)) return false;
        if (!updatedAt.equals(feed.updatedAt)) return false;
        if (!titleContent.equals(feed.titleContent)) return false;
        if (!content.equals(feed.content)) return false;
        if (!urlContent.equals(feed.urlContent)) return false;
        if (!likesBy.equals(feed.likesBy)) return false;
        if (!viewer.equals(feed.viewer)) return false;
        return comments.equals(feed.comments);

    }

    @Override
    public int hashCode() {
        int result = feedId;
        result = 31 * result + userId;
        result = 31 * result + displayName.hashCode();
        result = 31 * result + urlAvatar.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + totalViewer;
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + updatedAt.hashCode();
        result = 31 * result + titleContent.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + urlContent.hashCode();
        result = 31 * result + likesBy.hashCode();
        result = 31 * result + viewer.hashCode();
        result = 31 * result + totalLike;
        result = 31 * result + comments.hashCode();
        result = 31 * result + totalComment;
        return result;
    }
}
