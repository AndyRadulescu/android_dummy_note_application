package com.dragoiu.nodebackup_backend.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column
    private String title;

    @Column
    private String content;

    public Note(){ }

    public Note(UUID id, String title, String content) {
        this.title = title;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
