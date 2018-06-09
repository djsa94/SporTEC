package com.example.daniel.sportec.modelos;

import java.util.ArrayList;

public class Deporte {
    private String name;
    private ArrayList<String> news;
    private String _id;

    public Deporte(String name, ArrayList<String> news) {
        this.name = name;
        this.news = news;
    }

    public Deporte() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getNews() {
        return news;
    }

    public void setNews(ArrayList<String> news) {
        this.news = news;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
