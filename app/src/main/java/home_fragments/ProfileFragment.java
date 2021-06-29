package home_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;

import java.util.Collections;
import java.util.List;

import adapters.PostsAdapter;
import login_fragments.LoginFragment;
import review_data_access.ReviewDao;
import review_data_access.ReviewDatabase;
import review_data_access.ReviewEntity;

public class ProfileFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        ReviewDatabase reviewDatabase = ReviewDatabase.getReviewDatabase(getActivity().getApplicationContext());
        ReviewDao reviewDao = reviewDatabase.reviewDao();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                if (reviewDao.getReviewsFromLoggedUser(LoginFragment.loggedUser.getId()) != null)
                {
                    List<ReviewEntity> reviewEntities = reviewDao.getReviewsFromLoggedUser(LoginFragment.loggedUser.getId());
                    Collections.reverse(reviewEntities);
                    PostsAdapter postsAdapter = new PostsAdapter(getActivity().getApplicationContext(), reviewEntities);

                    RecyclerView recyclerView = view.findViewById(R.id.profile_recyclerView);

                    recyclerView.setAdapter(postsAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                }
            }
        }).start();
        return view;
    }
}