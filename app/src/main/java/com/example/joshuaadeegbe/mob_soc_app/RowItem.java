package com.example.joshuaadeegbe.mob_soc_app;

public class RowItem {
    private String fitness_name;
    private int picture_id;
    private String description_br;

    public RowItem (String fitness_name, int picture_id, String description_br){
        this.fitness_name = fitness_name;
        this.picture_id = picture_id;
        this.description_br = description_br;
    }

    public String getFitness_name() {
        return fitness_name;
    }

    public void setFitness_name(String fitness_name) {
        this.fitness_name = fitness_name;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public String getDescription_br() {
        return description_br;
    }

    public void setDescription_br(String description_br) {
        this.description_br = description_br;
    }
}
