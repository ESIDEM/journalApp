package ng.com.techdepo.journalapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ng.com.techdepo.journalapp.pojo.Journal;
@Dao
public interface MemoryDAO {

    @Query("SELECT * FROM journals ORDER BY timeStamp")
    LiveData<List<Journal>> getAllJournals();

    @Insert
    void insertJournal(Journal journal);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateJournal(Journal journal);

    @Delete
    void deleteJournal(Journal journal);

    @Query("SELECT * FROM journals WHERE id = :id")
    LiveData<Journal> loadJournalById(int id);
}
