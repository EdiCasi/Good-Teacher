package comment_data_acces;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import review_data_access.ReviewEntity;
import user_data_access.UserEntity;

@Entity(tableName = "comments")
public class CommentEntity
{
    @PrimaryKey(autoGenerate = true)
    Integer comId;

    @Embedded
    ReviewEntity reviewEntity;

    @ColumnInfo(name = "userName")
    String userName;

    @ColumnInfo(name = "commentText")
    String commentText;

    public void setComId(Integer comId)
    {
        this.comId = comId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Integer getComId()
    {
        return comId;
    }

    public void setComIdId(Integer comId)
    {
        this.comId = comId;
    }

    public ReviewEntity getReviewEntity()
    {
        return reviewEntity;
    }

    public void setReviewEntity(ReviewEntity reviewEntity)
    {
        this.reviewEntity = reviewEntity;
    }

    public String getCommentText()
    {
        return commentText;
    }

    public void setCommentText(String commentText)
    {
        this.commentText = commentText;
    }
}
