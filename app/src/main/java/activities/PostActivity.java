package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;

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