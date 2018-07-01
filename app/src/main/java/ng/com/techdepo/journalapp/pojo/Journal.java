package ng.com.techdepo.journalapp.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

@Entity (tableName = "journals")
public class Journal {


    @PrimaryKey @NonNull
   public String uid;
    public int id;
    public String body;
    public String title;
    private String timeStamp;

    public Journal(String uid, String body, String title,String timeStamp) {
        this.uid = uid;
        this.body = body;
        this.title = title;
        this.timeStamp = timeStamp;
    }
    @Ignore
    public Journal(String body, String title,String timeStamp) {
        this.body = body;
        this.title = title;
        this.timeStamp = timeStamp;
    }

    public Journal() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("body", body);
        result.put("title", title);
        result.put("timeStamp", timeStamp);

        return result;
    }
}
