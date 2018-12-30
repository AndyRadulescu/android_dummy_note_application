package dragoiu_raul.com.notesbackup.SqlLite;

import java.util.UUID;

public class Note {

    private UUID id;
    private String title;
    private String content;

    public Note(UUID id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
