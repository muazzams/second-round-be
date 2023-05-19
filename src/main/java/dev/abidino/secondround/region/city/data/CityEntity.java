package dev.abidino.secondround.region.city.data;

import dev.abidino.secondround.region.city.business.City;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String plate;

    private String name;

    public CityEntity(Long id, String plate, String name) {
        this.id = id;
        this.plate = plate;
        this.name = name;
    }


    public CityEntity() {

    }

    public CityEntity(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.plate = city.getPlate();
    }

    public Long getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public String getName() {
        return name;
    }
}
