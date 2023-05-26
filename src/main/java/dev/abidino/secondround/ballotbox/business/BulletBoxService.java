package dev.abidino.secondround.ballotbox.business;

import dev.abidino.secondround.ballotbox.web.BulletBoxDto;

import java.util.List;

public interface BulletBoxService {
    BulletBox save(BulletBox bulletBox);

    List<BulletBox> findAll();

    BulletBox findByAttendant(String username);

    BulletBox update(BulletBox bulletBox, Long id);

    BulletBox updateCount(Long id, long kkCount, long rteCount, long invalidCount);

    BulletBox findById(Long id);

    BulletBox convertDto(BulletBoxDto bulletBoxDto);

    List<BulletBox> getAllVoteCount();
}
