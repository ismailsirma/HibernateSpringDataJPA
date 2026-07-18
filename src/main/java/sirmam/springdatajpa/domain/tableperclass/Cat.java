package sirmam.springdatajpa.domain.tableperclass;


import jakarta.persistence.Entity;

@Entity
public class Cat extends Mammal {

    private String breed;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
