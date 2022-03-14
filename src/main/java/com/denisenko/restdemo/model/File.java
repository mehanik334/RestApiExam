package com.denisenko.restdemo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "location")
    private String location;

    @OneToOne(mappedBy = "file", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Event event;

    public File() {
    }

    public File(String location) {
        this.location = location;
    }

    public File(Integer id, String location) {
        this.id = id;
        this.location = location;
    }

    public File(String location, Event event) {
        this.location = location;
        this.event = event;
    }

    public File(Integer id, String location, Event event) {
        this.id = id;
        this.location = location;
        this.event = event;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "File{" +
                "id : " + id +
                ", location : " + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return id.equals(file.id) && location.equals(file.location) && (event.getId()).equals(file.event.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, event);
    }
}
