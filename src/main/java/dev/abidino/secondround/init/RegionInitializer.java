package dev.abidino.secondround.init;

import dev.abidino.secondround.region.city.business.City;
import dev.abidino.secondround.region.city.business.CityService;
import dev.abidino.secondround.region.district.business.District;
import dev.abidino.secondround.region.district.business.DistrictService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.FileReader;
import java.util.List;

@Service
public class RegionInitializer {

    private final CityService cityService;
    private final DistrictService districtService;

    public RegionInitializer(CityService cityService, DistrictService districtService) {
        this.cityService = cityService;
        this.districtService = districtService;
    }

    @PostConstruct
    @Transactional
    public void initializeData() {
        List<City> all = cityService.findAll();
        if (CollectionUtils.isEmpty(all)) {
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader("/Users/abidino/Desktop/projects/be/secondRound/src/main/java/dev/abidino/secondround/init/il-ilce.json")) {
                Object obj = jsonParser.parse(reader);
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray dataList = (JSONArray) jsonObject.get("data");
                dataList.forEach(data -> parseRegionObject((JSONObject) data));
            } catch (Exception e) {
                System.out.printf(e.getMessage());
                throw new RuntimeException();
            }
        }
    }

    private void parseRegionObject(JSONObject jsonObjectCity) {
        String cityName = (String) jsonObjectCity.get("il_adi");
        String plate = (String) jsonObjectCity.get("plaka_kodu");
        City city = new City(null, plate, cityName);
        City savedCity = cityService.save(city);
        JSONArray districtList = (JSONArray) jsonObjectCity.get("ilceler");
        for (Object d : districtList) {
            JSONObject jsonObjectDistrict = (JSONObject) d;
            String districtId = (String) jsonObjectDistrict.get("ilce_kodu");
            String districtName = (String) jsonObjectDistrict.get("ilce_adi");
            District district = new District(null, districtId, districtName, savedCity);
            districtService.save(district);
        }
    }
}
