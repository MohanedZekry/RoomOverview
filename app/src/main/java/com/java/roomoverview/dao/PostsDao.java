package com.java.roomoverview.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.java.roomoverview.models.Post;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface PostsDao {

    @Insert
    Completable insertPost(Post post);

    @Query("SELECT * FROM post_table")
    Flowable<List<Post>> getAllPosts();
}
