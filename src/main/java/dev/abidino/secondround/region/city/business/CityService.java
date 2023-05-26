package dev.abidino.secondround.region.city.business;

import java.util.List;

public interface CityService {
    City save(City city);

    List<City> findAll();

    City findById(Long cityId);
}
