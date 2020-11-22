package com.java.roomoverview.repositories;

import com.java.roomoverview.models.Post;
import java.util.List;
import io.reactivex.Flowable;

interface IPostRepository {

    Flowable<List<Post>> getAllPosts();

    void insertPost(Post post);
}
