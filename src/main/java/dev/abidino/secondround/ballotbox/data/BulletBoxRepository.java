package dev.abidino.secondround.ballotbox.data;

import dev.abidino.secondround.user.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BulletBoxRepository extends JpaRepository<BulletBoxEntity, Long> {
    Optional<BulletBoxEntity> findByAttendant(UserEntity attendant);
}
