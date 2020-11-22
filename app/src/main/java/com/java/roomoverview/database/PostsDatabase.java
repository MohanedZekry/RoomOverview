package com.java.roomoverview.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.java.roomoverview.dao.PostsDao;
import com.java.roomoverview.models.Post;
import static com.java.roomoverview.utils.Constants.POST_DATABASE_NAME;

@Database(entities = Post.class, version = 1, exportSchema = false)
public abstract class PostsDatabase extends RoomDatabase {

    private static PostsDatabase instance;

    public synchronized static PostsDatabase getInstance(Context mContext){
        if (instance == null)
        {
            instance = Room.databaseBuilder(
                    mContext,
                    PostsDatabase.class,
                    POST_DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract PostsDao postsDao();

}
