package dev.abidino.secondround.region.district.web;

import dev.abidino.secondround.region.district.business.District;
import dev.abidino.secondround.region.district.business.DistrictService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dev.abidino.secondround.region.district.web.DistrictController.API;

@RestController
@RequestMapping(API)
public class DistrictController {
    public static final String API = "/api/v1";

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/cities/{cityId}/districts")
    public List<District> findByCityId(@PathVariable Long cityId) {
        return districtService.findAllByCityId(cityId);
    }

    @GetMapping("/cities/plate/{plate}/districts")
    public List<District> findByCityPlate(@PathVariable String plate) {
        return districtService.findAllByCityPlate(plate);
    }
}
