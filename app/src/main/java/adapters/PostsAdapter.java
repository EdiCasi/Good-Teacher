package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;

import java.util.List;

import activities.PostActivity;
import review_data_access.ReviewEntity;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder>
{
    private Context context;
    private List<ReviewEntity> reviewEntity;

    public PostsAdapter(Context ct, List<ReviewEntity> reviewEntity)
    {
        this.context = ct;
        this.reviewEntity = reviewEntity;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.post_cell,parent,false);

        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position)
    {
        holder.name.setText(reviewEntity.get(position).getProfessorName());
        holder.description.setText(reviewEntity.get(position).getProfessorDescription());
        holder.grade.setText(reviewEntity.get(position).getProfessorGrade());

        holder.mainLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("profName",reviewEntity.get(position).getProfessorName());
                intent.putExtra("profDescription",reviewEntity.get(position).getProfessorDescription());
                intent.putExtra("profGrade",reviewEntity.get(position).getProfessorGrade());
                intent.putExtra("revId",reviewEntity.get(position).getRewId());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return reviewEntity.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView description;
        TextView grade;

        ConstraintLayout mainLayout;

        public PostsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.name=itemView.findViewById(R.id.prof_name_view);
            this.description=itemView.findViewById(R.id.prof_description_view);
            this.grade=itemView.findViewById(R.id.prof_grade_view);
            this.mainLayout = itemView.findViewById(R.id.post_item);
        }
    }
}
