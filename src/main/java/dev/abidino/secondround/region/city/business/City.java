package dev.abidino.secondround.region.city.business;

import dev.abidino.secondround.region.city.data.CityEntity;

public class City {
    private Long id;
    private String plate;
    private String name;

    public City(Long id, String plate, String name) {
        this.id = id;
        this.plate = plate;
        this.name = name;
    }

    public City() {

    }

    public City(CityEntity cityEntity) {
        this.id = cityEntity.getId();
        this.name = cityEntity.getName();
        this.plate = cityEntity.getPlate();
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
