package dev.abidino.secondround.ballotbox.business;

import dev.abidino.secondround.ballotbox.data.BulletBoxEntity;
import dev.abidino.secondround.ballotbox.web.BulletBoxDto;
import dev.abidino.secondround.region.district.business.District;
import dev.abidino.secondround.user.business.User;

public class BulletBox {
    private Long id;
    private Long kkCount;
    private Long rteCount;
    private Long invalidCount;

    private District district;

    private String schoolName;

    private Long bulletBoxNumber;

    private User attendant;

    public BulletBox(Long id, Long kkCount, Long rteCount, Long invalidCount, District district, String schoolName, Long bulletBoxNumber, User attendant) {
        this.id = id;
        this.kkCount = kkCount;
        this.rteCount = rteCount;
        this.invalidCount = invalidCount;
        this.district = district;
        this.schoolName = schoolName;
        this.bulletBoxNumber = bulletBoxNumber;
        this.attendant = attendant;
    }

    public BulletBox(BulletBox bulletBox, Long id) {
        this.id = id;
        this.kkCount = bulletBox.getKkCount();
        this.rteCount = bulletBox.getRteCount();
        this.invalidCount = bulletBox.getInvalidCount();
        this.district = bulletBox.getDistrict();
        this.schoolName = bulletBox.getSchoolName();
        this.bulletBoxNumber = bulletBox.getBulletBoxNumber();
        this.attendant = bulletBox.getAttendant();
    }

    public BulletBox(BulletBoxEntity bulletBoxEntity) {
        this.id = bulletBoxEntity.getId();
        this.kkCount = bulletBoxEntity.getKkCount();
        this.rteCount = bulletBoxEntity.getRteCount();
        this.invalidCount = bulletBoxEntity.getInvalidCount();
        this.district = new District(bulletBoxEntity.getDistrictEntity());
        this.schoolName = bulletBoxEntity.getSchoolName();
        this.bulletBoxNumber = bulletBoxEntity.getBulletBoxNumber();
        this.attendant = new User(bulletBoxEntity.getAttendant());
    }

    public BulletBox(BulletBoxDto bulletBoxDto, User attendant, District district) {
        this.id = bulletBoxDto.id();
        this.kkCount = bulletBoxDto.kkCount();
        this.rteCount = bulletBoxDto.rteCount();
        this.invalidCount = bulletBoxDto.invalidCount();
        this.district = district;
        this.schoolName = bulletBoxDto.schoolName();
        this.bulletBoxNumber = bulletBoxDto.bulletBoxNumber();
        this.attendant = attendant;
    }

    public BulletBox() {
    }

    public void setCounts(Long kkCount, Long rteCount, Long invalidCount) {
        this.kkCount = kkCount;
        this.rteCount = rteCount;
        this.invalidCount = invalidCount;
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

    public District getDistrict() {
        return district;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public Long getBulletBoxNumber() {
        return bulletBoxNumber;
    }

    public User getAttendant() {
        return attendant;
    }
}
