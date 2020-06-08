package com.app.mvvmdemo.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mvvmdemo.R;
import com.app.mvvmdemo.databinding.ItemNewsBinding;
import com.app.mvvmdemo.model.ArticlesItem;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private static final String TAG = NewsListAdapter.class.getSimpleName();

    private Context context;
    private List<ArticlesItem> list;
    private OnItemClickListener onItemClickListener;

    public NewsListAdapter(Context context, List<ArticlesItem> list, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemNewsBinding binding;

        public ViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(final ArticlesItem model, final OnItemClickListener listener) {
            binding.setModel(model);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getLayoutPosition());

                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemNewsBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false);

        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArticlesItem item = list.get(position);

        //Todo: Setup viewholder for item 
        holder.bind(item, onItemClickListener);
    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }else{
            return 0;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}