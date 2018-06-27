package ng.com.techdepo.journalapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;

import ng.com.techdepo.journalapp.R;
import ng.com.techdepo.journalapp.pojo.Journal;
import ng.com.techdepo.journalapp.viewmodels.AddJournalActivityViewModel;

public class AddJournalActivity extends AppCompatActivity {

    TextInputEditText editTextTitle;
    TextInputEditText editTextDetail;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);
        editTextDetail = (TextInputEditText) findViewById(R.id.journal_detail_edit_text);
        editTextTitle = (TextInputEditText) findViewById(R.id.journal_title_edit_text);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }


    public void insertMemory(View view){

        String title = editTextTitle.getText().toString();
        String details = editTextDetail.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String timestamp = dateFormat.format(date);

        Journal journal = new Journal(details,title,timestamp);
       String uid = firebaseUser.getUid();

       AddJournalActivityViewModel viewModel= ViewModelProviders.of(this).get(AddJournalActivityViewModel.class);
       viewModel.addTofirebaseDatabase(journal,uid);
       startActivity(new Intent(getApplicationContext(),MainActivity.class));
       finish();
    }
}
