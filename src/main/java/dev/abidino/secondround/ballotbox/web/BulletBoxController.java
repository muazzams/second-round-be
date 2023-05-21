package dev.abidino.secondround.ballotbox.web;

import dev.abidino.secondround.ballotbox.business.BulletBox;
import dev.abidino.secondround.ballotbox.business.BulletBoxService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dev.abidino.secondround.ballotbox.web.BulletBoxController.API;

@RestController
@RequestMapping(API)
public class BulletBoxController {
    public static final String API = "api/v1/bullet-box";

    private final BulletBoxService bulletBoxService;

    public BulletBoxController(BulletBoxService bulletBoxService) {
        this.bulletBoxService = bulletBoxService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public BulletBoxDto save(@RequestBody @Valid BulletBoxDto bulletBoxDto) {
        BulletBox bulletBox = bulletBoxService.convertDto(bulletBoxDto);
        BulletBox savedBulletBox = bulletBoxService.save(bulletBox);
        return new BulletBoxDto(savedBulletBox);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BulletBoxDto update(@RequestBody @Valid BulletBoxDto bulletBoxDto, @PathVariable Long id) {
        BulletBox bulletBox = bulletBoxService.convertDto(bulletBoxDto);
        BulletBox savedBulletBox = bulletBoxService.update(bulletBox, id);
        return new BulletBoxDto(savedBulletBox);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<BulletBoxDto> all() {
        return bulletBoxService.findAll().stream().map(BulletBoxDto::new).toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BulletBoxDto findById(@PathVariable Long id) {
        return new BulletBoxDto(bulletBoxService.findById(id));
    }

    @GetMapping("/attendant")
    public BulletBoxDto findByAttendant(Authentication authentication) {
        BulletBox bulletBox = bulletBoxService.findByAttendant(authentication.getName());
        return new BulletBoxDto(bulletBox);
    }

    @PatchMapping("/{id}")
    public BulletBoxDto updateCount(@RequestBody BulletBoxUpdateCountDto bulletBoxDto, @PathVariable Long id) {
        BulletBox savedBulletBox = bulletBoxService.updateCount(id, bulletBoxDto.kkCount(), bulletBoxDto.rteCount(), bulletBoxDto.invalidCount());
        return new BulletBoxDto(savedBulletBox);
    }

}
