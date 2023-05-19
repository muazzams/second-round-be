package dev.abidino.secondround.region.district.business;

import dev.abidino.secondround.region.district.data.DistrictDataService;
import dev.abidino.secondround.region.district.data.DistrictEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record DistrictServiceImpl(DistrictDataService districtDataService) implements DistrictService {
    @Override
    public District save(District district) {
        DistrictEntity districtEntity = new DistrictEntity(district);
        DistrictEntity savedDistrict = districtDataService.save(districtEntity);
        return new District(savedDistrict);
    }

    @Override
    public List<District> findAllByCityId(Long cityId) {
        return districtDataService.findAllByCityId(cityId).stream().map(District::new).toList();
    }

    @Override
    public List<District> findAllByCityPlate(String plate) {
        return districtDataService.findAllByCityPlate(plate).stream().map(District::new).toList();
    }
}
