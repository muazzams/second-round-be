package dev.abidino.secondround.region.district.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    List<DistrictEntity> findAllByCityEntity_Id(Long cityId);
}
