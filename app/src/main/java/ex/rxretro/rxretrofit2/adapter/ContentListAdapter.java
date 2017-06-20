package ex.rxretro.rxretrofit2.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ex.rxretro.rxretrofit2.R;
import ex.rxretro.rxretrofit2.pojo.Content;

/**
 * Created by panji on 6/20/2017.
 */

public class ContentListAdapter extends RecyclerView.Adapter<ContentListAdapter.ContentViewHolder> {
    private List<Content> mDataSource;

    public ContentListAdapter(@NonNull List<Content> dataSource) {
        this.mDataSource = dataSource;
    }

    public void setDataSource(@NonNull List<Content> dataSource) {
        this.mDataSource = dataSource;
        notifyDataSetChanged();
    }

    public void addContent(@NonNull Content content) {
        int idx = mDataSource.size();
        this.mDataSource.add(idx, content);
    }

    @Override
    public ContentListAdapter.ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentListAdapter.ContentViewHolder holder, int position) {
        holder.populate(mDataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView mTvTitle;
        @BindView(R.id.txt_description)
        TextView mTvDescription;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(Content content) {
            mTvTitle.setText(content.getTitle());
            mTvDescription.setText(content.getContent());
        }
    }
}
