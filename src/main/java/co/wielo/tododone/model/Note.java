package co.wielo.tododone.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

@Entity
public class Note
{
    public static HashMap<Integer, String> priorities;
    static {
        priorities = new HashMap<>();
        priorities.put(1, "High");
        priorities.put(2, "Medium");
        priorities.put(3, "Low");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int priority;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean done = false;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted = false;

    public Long getId() {
        return this.id;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getAuthor()
    {
        return this.author;
    }

    public String getPriorityText()
    {
        return priorities.get(priority);
    }

    public int getPriority() {
        return priority;
    }

    public boolean isDone() {
        return done;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @SuppressWarnings("unused")
    @PrePersist
    public void onPersist()
    {
        Date now = new Date();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @SuppressWarnings("unused")
    @PreUpdate
    public void onUpdate()
    {
        this.updatedAt = new Date();
    }
}
