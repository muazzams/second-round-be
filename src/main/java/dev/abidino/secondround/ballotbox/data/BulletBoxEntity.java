package dev.abidino.secondround.ballotbox.data;

import dev.abidino.secondround.region.city.data.CityEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private DistrictEntity districtEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private CityEntity cityEntity;
    private String schoolName;

    @Column(unique = true)
    private Long bulletBoxNumber;

    @OneToOne
    private UserEntity attendant;

    public BulletBoxEntity() {
    }

    public BulletBoxEntity(Long id, Long kkCount, Long rteCount, Long invalidCount, DistrictEntity districtEntity, CityEntity cityEntity, String schoolName, Long bulletBoxNumber, UserEntity attendant) {
        this.id = id;
        this.kkCount = kkCount;
        this.rteCount = rteCount;
        this.invalidCount = invalidCount;
        this.districtEntity = districtEntity;
        this.cityEntity = cityEntity;
        this.schoolName = schoolName;
        this.bulletBoxNumber = bulletBoxNumber;
        this.attendant = attendant;
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

    public CityEntity getCityEntity() {
        return cityEntity;
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
