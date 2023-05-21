package dev.abidino.secondround.region.district.data;

import java.util.List;

public interface DistrictDataService {
    DistrictEntity save(DistrictEntity district);

    List<DistrictEntity> findAllByCityId(Long cityId);

    List<DistrictEntity> findAllByCityPlate(String plate);

    DistrictEntity findById(Long id);
}
