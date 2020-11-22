package com.java.roomoverview.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.java.roomoverview.models.Post;
import com.java.roomoverview.repositories.PostRepository;
import java.util.List;

import io.reactivex.Flowable;

public class MainActivityViewModel extends ViewModel {

    private PostRepository postRepository;
    private Context mContext;

    public void init(Context mContext){
        this.mContext = mContext;
        this.postRepository = PostRepository.getInstance(mContext);
    }


    public Flowable<List<Post>> getAllPosts(){
        return postRepository.getAllPosts();
    }

    public void insertPost(Post post){
        postRepository.insertPost(post);
    }
}
