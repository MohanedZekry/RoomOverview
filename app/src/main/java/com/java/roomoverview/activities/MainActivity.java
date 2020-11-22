package com.java.roomoverview.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.java.roomoverview.R;
import com.java.roomoverview.adapter.PostAdapter;
import com.java.roomoverview.databinding.ActivityMainBinding;
import com.java.roomoverview.models.Post;
import com.java.roomoverview.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private PostAdapter adapter;
    private List<Post> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization(){
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init(this);
        list = new ArrayList<>();
        adapter = new PostAdapter(list, getApplicationContext());
        binding.recyclerview.setAdapter(adapter);
    }

    public void onMainClick(View view){
        switch (view.getId()){
            case R.id.btnGet:{
                list.clear();
                CompositeDisposable disposableGetPost = new CompositeDisposable();
                disposableGetPost.add(
                        viewModel.getAllPosts()
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(posts -> {
                                    list.addAll(posts);
                                    adapter.notifyDataSetChanged();
                                    disposableGetPost.dispose();
                                })
                );
                break;
            }
            case R.id.btnInsert:{
                viewModel.insertPost(new Post(2, binding.etTitle.getText().toString(),
                        binding.etBody.getText().toString()));
                Toast.makeText(this, "Post is successfully added", Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

}