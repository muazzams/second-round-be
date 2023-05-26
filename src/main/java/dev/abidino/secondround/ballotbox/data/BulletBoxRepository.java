package dev.abidino.secondround.ballotbox.data;

import dev.abidino.secondround.region.city.data.CityEntity;
import dev.abidino.secondround.region.district.data.DistrictEntity;
import dev.abidino.secondround.user.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BulletBoxRepository extends JpaRepository<BulletBoxEntity, Long> {
    Optional<BulletBoxEntity> findByAttendant(UserEntity attendant);

    List<BulletBoxEntity> findByDistrictEntity(DistrictEntity districtEntity);

    List<BulletBoxEntity> findByDistrictEntity_CityEntity(CityEntity cityEntity);
}
