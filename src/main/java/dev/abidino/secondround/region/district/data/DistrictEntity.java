package dev.abidino.secondround.region.district.data;

import dev.abidino.secondround.region.city.data.CityEntity;
import dev.abidino.secondround.region.district.business.District;
import jakarta.persistence.*;

@Entity
public class DistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String districtId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private CityEntity cityEntity;

    public DistrictEntity(Long id, String districtId, String name, CityEntity cityEntity) {
        this.id = id;
        this.districtId = districtId;
        this.name = name;
        this.cityEntity = cityEntity;
    }


    public DistrictEntity() {
    }

    public DistrictEntity(District district) {
        this.id = district.getId();
        this.districtId = district.getDistrictId();
        this.name = district.getName();
        this.cityEntity = new CityEntity(district.getCity());
    }

    public Long getId() {
        return id;
    }

    public String getDistrictId() {
        return districtId;
    }

    public String getName() {
        return name;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }
}
