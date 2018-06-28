package ng.com.techdepo.journalapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ng.com.techdepo.journalapp.database.AppDatabase;
import ng.com.techdepo.journalapp.pojo.Journal;

public class MainActivityViewModel extends AndroidViewModel {


    private LiveData<List<Journal>> journal;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        AppDatabase database = AppDatabase.getInstance(this.getApplication());

        journal = database.memoryDAO().getAllJournals();
    }

    public LiveData<List<Journal>> getJournal() {
        return journal;
    }
}
