package dev.abidino.secondround.region.district.data;

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
        return districtRepository.findAllByCityEntity_Id(cityId);
    }
}
