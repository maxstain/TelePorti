package org.example.teleporti.Entities;

import java.util.Objects;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String email;
    private String motDePasse;
    private String type;

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
        this.motDePasse = hashPassword(motDePasse);
        this.type = type;
    }

    public static String hashPassword(String motDePasse) {
        return motDePasse.hashCode() + "";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && getAge() == user.getAge() && Objects.equals(getNom(), user.getNom()) && Objects.equals(getPrenom(), user.getPrenom()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getMotDePasse(), user.getMotDePasse()) && Objects.equals(getType(), user.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getPrenom(), getAge(), getEmail(), getMotDePasse(), getType());
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
                '}';
    }

    public static User empty() {
        return new User(0, "", "", 0, "", "", "");
    }
}
