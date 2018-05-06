package com.ups.UPSTrailerTracker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;

    protected Greeting(){}

    public Greeting(String content){
        this.content = content;
    }

    public Long getId(){
        return id;
    }

    public String getContent(){
        return content;
    }

    @Override
    public String toString() {
        return String.format("Greeting[id=%d, content=%s]", id, content);
    }
}
