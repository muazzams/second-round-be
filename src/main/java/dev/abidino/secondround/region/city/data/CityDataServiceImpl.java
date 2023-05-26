package dev.abidino.secondround.region.city.data;

import dev.abidino.secondround.exception.CityNotFoundException;
import dev.abidino.secondround.exception.ErrorMessageType;
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

    @Override
    public CityEntity findById(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException(ErrorMessageType.CITY_NOT_FOUND.getMessage()));
    }
}
