package dev.abidino.secondround.region.city.data;

import java.util.List;

public interface CityDataService {
    CityEntity save(CityEntity cityEntity);

    List<CityEntity> findAll();
}
