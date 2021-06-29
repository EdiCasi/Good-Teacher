package activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;

import java.util.Collections;
import java.util.List;

import adapters.CommentAdapter;
import adapters.PostsAdapter;
import comment_data_acces.CommentDao;
import comment_data_acces.CommentDatabase;
import comment_data_acces.CommentEntity;
import review_data_access.ReviewDao;
import review_data_access.ReviewDatabase;
import review_data_access.ReviewEntity;
import user_data_access.UserDao;
import user_data_access.UserDatabase;
import user_data_access.UserEntity;

public class PostActivity extends AppCompatActivity
{

    private TextView profNameEditText;
    private TextView profDescriptionEditText;
    private TextView profGradeEditText;

    private String profName;
    private String profDescription;
    private String profGrade;
    private Integer revId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        profNameEditText = findViewById(R.id.prof_name_post);
        profDescriptionEditText = findViewById(R.id.prof_description_post);
        profGradeEditText = findViewById(R.id.prof_grade_post);

        getData();
        setData();

        initializeCommentRecyclerView();
    }

    private void initializeCommentRecyclerView()
    {
        CommentDatabase commentDatabase = CommentDatabase.getCommentDatabase(this);
        CommentDao commentDao = commentDatabase.commentDao();

        // Insert a comment for testing propose

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                if (commentDao.getComments(revId) != null)
                {

                    CommentAdapter commentAdapter = new CommentAdapter(getApplicationContext(), commentDao.getComments(revId));
                    RecyclerView recyclerView = findViewById(R.id.comments_recyclerview);

                    recyclerView.setAdapter(commentAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }
        }).start();
    }

//    private void insertComment(UserEntity userEntity,ReviewEntity reviewEntity, String commentText)
//    {
//        CommentEntity commentEntity = new CommentEntity();
//        commentEntity.setUserEntity(userEntity);
//        commentEntity.setCommentText(commentText);
//        commentEntity.setReviewEntity(reviewEntity);
//        if (validateInput(commentEntity))
//        {
//            CommentDatabase commentDatabase = CommentDatabase.getCommentDatabase(this);
//
//            CommentDao commentDao = commentDatabase.commentDao();
//            new Thread(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    commentDao.getComments(reviewEntity.getRewId());
//                    getActivity().runOnUiThread(new Runnable()
//                    {
//                        @Override
//                        public void run()
//                        {
//                            Toast.makeText(getActivity().getApplicationContext(), "User Registered!", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }).start();
//        } else
//        {
//            Toast.makeText(getActivity().getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
//        }
//    }

    private Boolean validateInput(CommentEntity commentEntity)
    {
        if (commentEntity.getCommentText().isEmpty())
            return false;
        return true;
    }

    private void getData()
    {
        if(getIntent().hasExtra("profName")&&getIntent().hasExtra("profDescription")&&getIntent().hasExtra("profGrade"))
        {
            profName= getIntent().getStringExtra("profName");
            profDescription= getIntent().getStringExtra("profDescription");
            profGrade= getIntent().getStringExtra("profGrade");
            revId= getIntent().getIntExtra("revId",1);
        }
        else {
            Toast.makeText(this,"No data !",Toast.LENGTH_SHORT);
        }
    }

    private void setData()
    {
        profNameEditText.setText(profName);
        profDescriptionEditText.setText(profDescription);
        profGradeEditText.setText(profGrade);
    }
}