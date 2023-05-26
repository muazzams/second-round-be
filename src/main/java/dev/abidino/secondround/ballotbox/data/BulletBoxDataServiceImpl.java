package dev.abidino.secondround.ballotbox.data;

import dev.abidino.secondround.exception.BulletBoxNotFoundException;
import dev.abidino.secondround.exception.ErrorMessageType;
import dev.abidino.secondround.region.city.data.CityEntity;
import dev.abidino.secondround.region.district.data.DistrictEntity;
import dev.abidino.secondround.user.data.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BulletBoxDataServiceImpl(BulletBoxRepository bulletBoxRepository) implements BulletBoxDataService {
    @Override
    public BulletBoxEntity save(BulletBoxEntity bulletBoxEntity) {
        return bulletBoxRepository.save(bulletBoxEntity);
    }

    @Override
    public List<BulletBoxEntity> findAll() {
        return bulletBoxRepository.findAll();
    }

    @Override
    public BulletBoxEntity findByUserEntity(UserEntity entity) {
        return bulletBoxRepository.findByAttendant(entity).orElseThrow(() -> new BulletBoxNotFoundException(ErrorMessageType.USER_HAS_NOT_BULLET_BOX.getMessage()));
    }

    @Override
    public BulletBoxEntity update(BulletBoxEntity bulletBoxEntity, Long id) {
        findById(id);
        return bulletBoxRepository.save(bulletBoxEntity);
    }

    @Override
    public BulletBoxEntity findById(Long id) {
        return bulletBoxRepository.findById(id).orElseThrow(() -> new BulletBoxNotFoundException(ErrorMessageType.BULLET_BOX_NOT_FOUND.getMessage()));
    }

    @Override
    public List<BulletBoxEntity> findByCityId(CityEntity cityEntity) {
        return bulletBoxRepository.findByDistrictEntity_CityEntity(cityEntity);
    }

    @Override
    public List<BulletBoxEntity> findByDistrictId(DistrictEntity districtEntity) {
        return bulletBoxRepository.findByDistrictEntity(districtEntity);
    }
}
