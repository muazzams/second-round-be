package dev.abidino.secondround.region.district.business;

import dev.abidino.secondround.region.city.business.City;
import dev.abidino.secondround.region.district.data.DistrictEntity;

public class District {
    private Long id;

    private String districtId;

    private String name;

    private City city;

    public District(Long id, String districtId, String name, City city) {
        this.id = id;
        this.districtId = districtId;
        this.name = name;
        this.city = city;
    }

    public District(DistrictEntity districtEntity) {
        this.id = districtEntity.getId();
        this.name = districtEntity.getName();
        this.districtId = districtEntity.getDistrictId();
        this.city = new City(districtEntity.getCityEntity());
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

    public City getCity() {
        return city;
    }
}
