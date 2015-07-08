package com.example.maciej.bootcampbeer;

import java.io.Serializable;

public class Beer implements Serializable {

    private String name;
    private String desctiption;
    private String photo;

    public Beer(String name, String desctiption, String photo) {
        this.name = name;
        this.desctiption = desctiption;
        this.photo = photo;
    }

    public String getName() { return name; }
    public String getDesctiption() { return desctiption; }
    public String getPhoto() { return photo; }

}

