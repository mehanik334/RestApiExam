package com.denisenko.restdemo.model;

import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    private String value;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id")
    private File file;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Event() {
    }


    public Event(String value, User user) {
        this.value = value;
        this.user = user;
    }

    public Event(String value, File file, User user) {
        this.value = value;
        this.file = file;
        this.user = user;
    }

    public Event(Integer id, String value, File file, User user) {
        this.id = id;
        this.value = value;
        this.file = file;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id : " + id +
                ", value : " + value +
//                ", file : " + file +
//                ", user : " + user.getId() + user.getFirstName()+user.getLastName()+user.getPassword()+
                '}';
    }
}
