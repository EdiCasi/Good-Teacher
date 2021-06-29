package comment_data_acces;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {CommentEntity.class}, version = 1)
public abstract class CommentDatabase extends RoomDatabase
{

    public static final String dbName = "comment_data_access";
    public static CommentDatabase commentDatabase;

    public static synchronized CommentDatabase getCommentDatabase(Context context)
    {
        if (commentDatabase == null)
            commentDatabase = Room.databaseBuilder(context, CommentDatabase.class, dbName).
                    fallbackToDestructiveMigration().build();

        return commentDatabase;
    }

    public abstract CommentDao commentDao();
}
