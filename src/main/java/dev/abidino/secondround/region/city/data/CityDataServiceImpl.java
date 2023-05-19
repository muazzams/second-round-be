package dev.abidino.secondround.region.city.data;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CityDataServiceImpl(CityRepository cityRepository) implements CityDataService {
    @Override
    public CityEntity save(CityEntity cityEntity) {
        return cityRepository.save(cityEntity);
    }

    @Override
    public List<CityEntity> findAll() {
        return cityRepository.findAllByOrderByName();
    }
}
