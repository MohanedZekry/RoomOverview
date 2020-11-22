package com.java.roomoverview.repositories;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.java.roomoverview.database.PostsDatabase;
import com.java.roomoverview.models.Post;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PostRepository implements IPostRepository {

    private static PostRepository instance;
    private Context mContext;
    private PostsDatabase postsDatabase;

    private PostRepository(Context mContext) {
        this.mContext = mContext;
        postsDatabase = PostsDatabase.getInstance(mContext);
    }

    public static synchronized PostRepository getInstance(Context mContext){
        if (instance == null)
            instance = new PostRepository(mContext);

        return instance;
    }

    @Override
    public Flowable<List<Post>> getAllPosts() {
        return postsDatabase.postsDao().getAllPosts();
    }

    @Override
    public void insertPost(Post post) {
        CompositeDisposable disposableInsert = new CompositeDisposable();
        disposableInsert.add(
                postsDatabase.postsDao().insertPost(post)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    disposableInsert.dispose();
                }));
    }
}
