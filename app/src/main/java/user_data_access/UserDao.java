package user_data_access;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao
{
    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE userId=(:userId) AND password=(:password)")
    UserEntity login(String userId,String password);
}
