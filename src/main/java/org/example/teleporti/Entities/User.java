package org.example.teleporti.Entities;

import java.sql.Date;
import java.util.Objects;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String email;
    private String motDePasse;
    private String type;
    private Date creationDate = new Date(new java.util.Date().getDate());
    private Date updateDate = new Date(new java.util.Date().getDate());

    public User(
            int id,
            String nom,
            String prenom,
            int age,
            String email,
            String motDePasse,
            String type
    ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.motDePasse = motDePasse;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && getAge() == user.getAge() && Objects.equals(getNom(), user.getNom()) && Objects.equals(getPrenom(), user.getPrenom()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getMotDePasse(), user.getMotDePasse()) && Objects.equals(getType(), user.getType()) && Objects.equals(getCreationDate(), user.getCreationDate()) && Objects.equals(getUpdateDate(), user.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getPrenom(), getAge(), getEmail(), getMotDePasse(), getType(), getCreationDate(), getUpdateDate());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", type='" + type + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }

    public static User empty() {
        return new User(0, "", "", 0, "", "", "");
    }
}
