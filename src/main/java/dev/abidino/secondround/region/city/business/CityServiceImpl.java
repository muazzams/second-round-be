package dev.abidino.secondround.region.city.business;

import dev.abidino.secondround.region.city.data.CityDataService;
import dev.abidino.secondround.region.city.data.CityEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CityServiceImpl(CityDataService cityDataService) implements CityService {
    @Override
    public City save(City city) {
        CityEntity cityEntity = new CityEntity(city);
        CityEntity save = cityDataService.save(cityEntity);
        return new City(save);
    }

    @Override
    public List<City> findAll() {
        return cityDataService.findAll().stream().map(City::new).toList();
    }
}
