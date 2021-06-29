package home_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ColumnInfo;

import com.example.user.R;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import activities.HomeActivity;
import adapters.PostsAdapter;
import review_data_access.ReviewDao;
import review_data_access.ReviewDatabase;
import review_data_access.ReviewEntity;
import user_data_access.UserEntity;

public class HomeFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        ReviewDatabase reviewDatabase = ReviewDatabase.getReviewDatabase(getActivity().getApplicationContext());
        ReviewDao reviewDao = reviewDatabase.reviewDao();


        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                if (reviewDao.getReviews() != null)
                {
                    List<ReviewEntity> reviewEntities = reviewDao.getReviews();
                    Collections.reverse(reviewEntities);

                    PostsAdapter postsAdapter = new PostsAdapter(getActivity().getApplicationContext(), reviewEntities);

                    RecyclerView recyclerView = view.findViewById(R.id.all_post_recyclerView);

                    recyclerView.setAdapter(postsAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                }
            }
        }).start();

        return view;
    }
}
