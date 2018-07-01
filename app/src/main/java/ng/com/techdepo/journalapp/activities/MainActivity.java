package ng.com.techdepo.journalapp.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

import ng.com.techdepo.journalapp.R;
import ng.com.techdepo.journalapp.adapters.JournalAdapter;
import ng.com.techdepo.journalapp.pojo.Journal;
import ng.com.techdepo.journalapp.viewmodels.AddJournalActivityViewModel;
import ng.com.techdepo.journalapp.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements JournalAdapter.ItemClickListener{

    FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    JournalAdapter mJournalAdapter;
    RecyclerView mRecyclerView;
    DatabaseReference myRef;
    DatabaseReference mDatabaseReference;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddJournalActivity.class));
            }
        });
        layoutManager = new LinearLayoutManager(this);
        myRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("journals");

        mAuth = FirebaseAuth.getInstance();
        mRecyclerView = (RecyclerView) findViewById(R.id.all_memoriew_list_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mJournalAdapter = new JournalAdapter(this,this);
        mRecyclerView.setAdapter(mJournalAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        AddJournalActivityViewModel viewModel= ViewModelProviders.of(this).get(AddJournalActivityViewModel.class);
        viewModel.insertToRoom();

       setupViewModel();

            }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            startActivity(new Intent(getApplicationContext(),SignInActivity.class));
        }

            }

    private void setupViewModel() {
        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getJournal().observe(this, new Observer<List<Journal>>() {
            @Override
            public void onChanged(@Nullable List<Journal> guestkEntries) {

                mJournalAdapter.setTasks(guestkEntries);
            }
        });
    }

    @Override
    public void onItemClickListener(int itemId) {

    }
}
