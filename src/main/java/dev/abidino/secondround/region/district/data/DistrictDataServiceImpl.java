package dev.abidino.secondround.region.district.data;

import dev.abidino.secondround.exception.DistrictNotFoundException;
import dev.abidino.secondround.exception.ErrorMessageType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record DistrictDataServiceImpl(DistrictRepository districtRepository) implements DistrictDataService {
    @Override
    public DistrictEntity save(DistrictEntity district) {
        return districtRepository.save(district);
    }

    @Override
    public List<DistrictEntity> findAllByCityId(Long cityId) {
        return districtRepository.findAllByCityEntity_IdOrderByName(cityId);
    }

    @Override
    public List<DistrictEntity> findAllByCityPlate(String plate) {
        return districtRepository.findAllByCityEntity_PlateOrderByName(plate);
    }

    @Override
    public DistrictEntity findById(Long id) {
        return districtRepository.findById(id).orElseThrow(() -> new DistrictNotFoundException(ErrorMessageType.DISTRICT_NOT_FOUND.getMessage()));
    }
}
