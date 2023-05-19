package dev.abidino.secondround.region.city.data;

import dev.abidino.secondround.region.city.business.City;
import jakarta.persistence.*;

@Entity
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
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
