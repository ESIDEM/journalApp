package ng.com.techdepo.journalapp.pojo;

public class Journal {

    public String id;
    public String body;
    public String title;
    private String timeStamp;

    public Journal(String id, String body, String title,String timeStamp) {
        this.id = id;
        this.body = body;
        this.title = title;
        this.timeStamp = timeStamp;
    }

    public Journal(String body, String title,String timeStamp) {
        this.body = body;
        this.title = title;
        this.timeStamp = timeStamp;
    }

    public Journal() {
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


}
