package dev.abidino.secondround.ballotbox.data;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//@Entity

public class BulletBoxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long kkCount;
    private long rteCount;
    private long invalidCount;
    private long districtId;
    private long cityId;

    public BulletBoxEntity(Long id, long kkCount, long rteCount, long invalidCount, long districtId, long cityId) {
        this.id = id;
        this.kkCount = kkCount;
        this.rteCount = rteCount;
        this.invalidCount = invalidCount;
        this.districtId = districtId;
        this.cityId = cityId;
    }

    public BulletBoxEntity() {

    }
}
