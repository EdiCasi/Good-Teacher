package comment_data_acces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface CommentDao
{
    @Insert
    void postComment(CommentEntity commentEntity);

    @Query("SELECT * FROM comments WHERE rewId = :rewId")
    List<CommentEntity> getComments(Integer rewId);
}
