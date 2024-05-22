package com.example.individualwebservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "topic")
    private String topic;
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"phone", "memberType", "address", "postList"})
    private User author;

    public Post() {
    }

    public Post(String topic, String content, User author) {
        this.topic = topic;
        this.content = content;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setUser(User author) {
        this.author = author;
    }
}
