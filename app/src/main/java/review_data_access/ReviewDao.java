package review_data_access;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReviewDao
{
    @Insert
    void postReview(ReviewEntity reviewEntity);

    @Query("SELECT * FROM reviews")
    List<ReviewEntity> getReviews();

    @Query("SELECT * FROM reviews WHERE id =(:userId)")
    List<ReviewEntity> getReviewsFromLoggedUser(Integer userId);
}
