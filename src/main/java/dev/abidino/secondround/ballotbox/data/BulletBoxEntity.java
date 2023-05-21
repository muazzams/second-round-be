package dev.abidino.secondround.ballotbox.data;

import dev.abidino.secondround.ballotbox.business.BulletBox;
import dev.abidino.secondround.region.district.data.DistrictEntity;
import dev.abidino.secondround.user.data.UserEntity;
import jakarta.persistence.*;

@Entity

public class BulletBoxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long kkCount;
    private Long rteCount;
    private Long invalidCount;

    @ManyToOne
    private DistrictEntity districtEntity;
    private String schoolName;

    @Column(unique = true)
    private Long bulletBoxNumber;

    @OneToOne
    private UserEntity attendant;

    public BulletBoxEntity() {
    }

    public BulletBoxEntity(Long id, Long kkCount, Long rteCount, Long invalidCount, DistrictEntity districtEntity, String schoolName, Long bulletBoxNumber, UserEntity attendant) {
        this.id = id;
        this.kkCount = kkCount;
        this.rteCount = rteCount;
        this.invalidCount = invalidCount;
        this.districtEntity = districtEntity;
        this.schoolName = schoolName;
        this.bulletBoxNumber = bulletBoxNumber;
        this.attendant = attendant;
    }

    public BulletBoxEntity(BulletBox bulletBox) {
        this.id = bulletBox.getId();
        this.kkCount = bulletBox.getKkCount();
        this.rteCount = bulletBox.getRteCount();
        this.invalidCount = bulletBox.getInvalidCount();
        this.districtEntity = new DistrictEntity(bulletBox.getDistrict());
        this.schoolName = bulletBox.getSchoolName();
        this.bulletBoxNumber = bulletBox.getBulletBoxNumber();
        this.attendant = new UserEntity(bulletBox.getAttendant());
    }

    public Long getId() {
        return id;
    }

    public Long getKkCount() {
        return kkCount;
    }

    public Long getRteCount() {
        return rteCount;
    }

    public Long getInvalidCount() {
        return invalidCount;
    }

    public DistrictEntity getDistrictEntity() {
        return districtEntity;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public Long getBulletBoxNumber() {
        return bulletBoxNumber;
    }

    public UserEntity getAttendant() {
        return attendant;
    }
}
