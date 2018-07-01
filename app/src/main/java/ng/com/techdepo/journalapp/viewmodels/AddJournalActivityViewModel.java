package ng.com.techdepo.journalapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ng.com.techdepo.journalapp.database.AppDatabase;
import ng.com.techdepo.journalapp.pojo.Journal;

public class AddJournalActivityViewModel extends AndroidViewModel {


    DatabaseReference myRef;
    DatabaseReference mDatabaseReference;
    List<Journal> journalList = new ArrayList<>();
    AppDatabase appDatabase;

    public AddJournalActivityViewModel(@NonNull Application application) {
        super(application);

       myRef = FirebaseDatabase.getInstance().getReference();
         mDatabaseReference = FirebaseDatabase.getInstance().getReference("journals");
         appDatabase = AppDatabase.getInstance(this.getApplication());


    }

    public void addTofirebaseDatabase(Journal journal, String uid){

        String key = mDatabaseReference.child(uid).push().getKey();
        Map<String, Object> postValues = journal.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put( key, postValues);
        mDatabaseReference.updateChildren(childUpdates);



    }

    public void insertToRoom(){



        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot journalSnapshot: dataSnapshot.getChildren()){

                    Journal journal = journalSnapshot.getValue(Journal.class);
                    journal.setUid(journalSnapshot.getKey());
                    insertItem(journal);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void insertItem(Journal journal){

        new BackgroundInsert(appDatabase).execute(journal);
    }

    private static class BackgroundInsert extends AsyncTask<Journal,Void,Void>{

        private AppDatabase db;

        BackgroundInsert(AppDatabase appDatabase){

            db =appDatabase;
        }

        @Override
        protected Void doInBackground(final Journal... params) {

            db.memoryDAO().insertJournal(params[0]);

            return null;

        }
    }
}
