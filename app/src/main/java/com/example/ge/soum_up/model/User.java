package com.example.ge.soum_up.model;

/**
 * Created by GE on 09/02/2017.
 */

public class User {
    private  String nom,username,mdp,email,adresse,prenom,sexe;
    private int age,user_ID;
    public User(int user_ID,String nom,String prenom,int age ,String username,String email,String adresse,String mdp,String sexe) {
        this.user_ID=user_ID;
        this.nom=nom;
        this.prenom=prenom;
        this.username=username;
        this.mdp=mdp;
        this.age=age;
        this.email=email;
        this.adresse=adresse;
        this.sexe=sexe;
    }
    public User (String username,String mdp)
    {
        this.username=username;
        this.mdp=mdp;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
