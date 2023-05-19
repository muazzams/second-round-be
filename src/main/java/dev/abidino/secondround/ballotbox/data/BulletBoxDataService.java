package dev.abidino.secondround.ballotbox.data;

import dev.abidino.secondround.user.data.UserEntity;

import java.util.List;

public interface BulletBoxDataService {
    BulletBoxEntity save(BulletBoxEntity bulletBoxEntity);

    List<BulletBoxEntity> findAll();

    BulletBoxEntity findByUserEntity(UserEntity entity);

    BulletBoxEntity update(BulletBoxEntity bulletBoxEntity, Long id);

    BulletBoxEntity findById(Long id);
}
