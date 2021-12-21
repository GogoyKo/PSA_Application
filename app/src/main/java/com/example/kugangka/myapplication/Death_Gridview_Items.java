package com.example.kugangka.myapplication;


public class Death_Gridview_Items {
    private int rdlyhm_id;
    private String d1;
    private String d2;
    private String lastname;
    private String firstname;
    private String gender;
    private String age_at_death;
    private String death_reg_d6;
    private String death_reg_d7;


    //Constructor

    public Death_Gridview_Items(int rdlyhm_id, String d1, String d2, String lastname, String firstname,
                                String gender, String age_at_death, String death_reg_d6, String death_reg_d7) {
        this.rdlyhm_id = rdlyhm_id;
        this.d1 = d1;
        this.d2 = d2;
        this.lastname = lastname;
        this.firstname = firstname;
        this.gender = gender;
        this.age_at_death = age_at_death;
        this.death_reg_d6 = death_reg_d6;
        this.death_reg_d7 = death_reg_d7;
    }

    //Setter, getter

    public int getId() {
        return rdlyhm_id;
    }

    public void setId(int rdlyhm_id) {
        this.rdlyhm_id = rdlyhm_id;
    }



    //send to EditText in ListAdapter to Display in EditText
    public String getName() {
        return d1;
    }

    public void setName(String name) {
        this.d1 = name;
    }

    public String getPrice() {
        return d2;
    }

    public void setPrice(String price) {
        this.d2 = price;
    }

    public String lastname() {
        return lastname;
    }

    public void lastname(String description) {
        this.lastname = description;
    }


    public String firstname() {
        return firstname;
    }
    public String gender() {
        return gender;
    }
    public String age_at_death() {
        return age_at_death;
    }
    public String death_reg_d6() {
        return death_reg_d6;
    }
    public String death_reg_d7() {
        return death_reg_d7;
    }
}
