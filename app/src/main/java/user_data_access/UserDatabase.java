package user_data_access;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase
{

    public static final String dbName = "user_data_acces";
    public static UserDatabase userDatabase;

    public static synchronized UserDatabase getUserDatabase(Context context)
    {
        if (userDatabase == null)
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, dbName).
                    fallbackToDestructiveMigration().build();

        return userDatabase;
    }

    public abstract UserDao userDao();

}
