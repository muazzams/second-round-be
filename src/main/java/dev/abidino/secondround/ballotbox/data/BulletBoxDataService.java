package dev.abidino.secondround.ballotbox.data;

import dev.abidino.secondround.region.city.data.CityEntity;
import dev.abidino.secondround.region.district.data.DistrictEntity;
import dev.abidino.secondround.user.data.UserEntity;

import java.util.List;

public interface BulletBoxDataService {
    BulletBoxEntity save(BulletBoxEntity bulletBoxEntity);

    List<BulletBoxEntity> findAll();

    BulletBoxEntity findByUserEntity(UserEntity entity);

    BulletBoxEntity update(BulletBoxEntity bulletBoxEntity, Long id);

    BulletBoxEntity findById(Long id);

    List<BulletBoxEntity> findByCityId(CityEntity cityEntity);

    List<BulletBoxEntity> findByDistrictId(DistrictEntity districtEntity);
}
