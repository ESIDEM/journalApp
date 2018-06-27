package ng.com.techdepo.journalapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import ng.com.techdepo.journalapp.pojo.Journal;

public class AddJournalActivityViewModel extends AndroidViewModel {


    DatabaseReference myRef;
    DatabaseReference mDatabaseReference;

    public AddJournalActivityViewModel(@NonNull Application application) {
        super(application);

       myRef = FirebaseDatabase.getInstance().getReference();
         mDatabaseReference = FirebaseDatabase.getInstance().getReference("journals");
    }

    public void addTofirebaseDatabase(Journal journal, String uid){

        String key = mDatabaseReference.child(uid).push().getKey();
        Map<String, Object> postValues = journal.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put( key, postValues);
        mDatabaseReference.updateChildren(childUpdates);

       // mDatabaseReference.child(uid).setValue(journal);
    }
}
