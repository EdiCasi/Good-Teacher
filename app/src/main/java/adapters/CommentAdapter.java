package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;

import java.util.List;

import activities.PostActivity;
import comment_data_acces.CommentEntity;
import review_data_access.ReviewEntity;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>
{
    private Context context;
    private List<CommentEntity> commentEntities;

    public CommentAdapter(Context ct, List<CommentEntity> commentEntities)
    {
        this.context = ct;
        this.commentEntities = commentEntities;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.comment_cell, parent, false);

        return new CommentAdapter.CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position)
    {
        holder.username.setText(commentEntities.get(position).getUserName());
        holder.comment.setText(commentEntities.get(position).getCommentText());
    }

    @Override
    public int getItemCount()
    {
        return commentEntities.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder
    {
        TextView username;
        TextView comment;

        public CommentViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.username = itemView.findViewById(R.id.username_comment);
            this.comment = itemView.findViewById(R.id.comment_text);
        }
    }
}
