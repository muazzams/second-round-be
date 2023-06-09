package dev.abidino.secondround.region.district.business;

import java.util.List;

public interface DistrictService {

    District save(District district);

    List<District> findAllByCityId(Long cityId);

    List<District> findAllByCityPlate(String plate);

    District findById(Long aLong);
}
