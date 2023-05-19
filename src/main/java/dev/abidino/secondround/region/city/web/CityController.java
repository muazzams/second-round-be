package dev.abidino.secondround.region.city.web;

import dev.abidino.secondround.region.city.business.City;
import dev.abidino.secondround.region.city.business.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static dev.abidino.secondround.region.city.web.CityController.API;

@RestController
@RequestMapping(API)
public class CityController {
    public static final String API = "/api/v1/cities";
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> all() {
        return cityService.findAll();
    }
}
