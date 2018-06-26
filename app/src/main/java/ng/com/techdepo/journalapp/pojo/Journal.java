package ng.com.techdepo.journalapp.pojo;

public class Journal {

    public String id;
    public String body;
    public String title;

    public Journal(String id, String body, String title) {
        this.id = id;
        this.body = body;
        this.title = title;
    }

    public Journal(String body, String title) {
        this.body = body;
        this.title = title;
    }

    public Journal() {
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
