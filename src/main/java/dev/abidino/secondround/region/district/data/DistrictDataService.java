package dev.abidino.secondround.region.district.data;

import java.util.List;

public interface DistrictDataService {
    DistrictEntity save(DistrictEntity district);

    List<DistrictEntity> findAllByCityId(Long cityId);
}
