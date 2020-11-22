package com.java.roomoverview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.java.roomoverview.R;
import com.java.roomoverview.databinding.ItemContainerBinding;
import com.java.roomoverview.models.Post;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostTvHolder> {

    private List<Post> list;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public PostAdapter(List<Post> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PostTvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        ItemContainerBinding itemContainerBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_container,
                parent,
                false
        );

        return new PostTvHolder(itemContainerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostTvHolder holder, int position) {
        holder.bindPost(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PostTvHolder extends RecyclerView.ViewHolder{

        private ItemContainerBinding itemContainerBinding;

        public PostTvHolder(ItemContainerBinding itemContainerBinding) {
            super(itemContainerBinding.getRoot());
            this.itemContainerBinding = itemContainerBinding;
        }

        public void bindPost(Post post){
            itemContainerBinding.setPost(post);
        }
    }

}
