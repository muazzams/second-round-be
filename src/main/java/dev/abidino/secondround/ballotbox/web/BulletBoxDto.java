package dev.abidino.secondround.ballotbox.web;

import dev.abidino.secondround.ballotbox.business.BulletBox;
import jakarta.validation.constraints.NotNull;

public record BulletBoxDto(Long id,
                           long kkCount,
                           long rteCount,
                           long invalidCount,
                           @NotNull Long districtId,
                           String schoolName,
                           @NotNull Long bulletBoxNumber,
                           String attendantName) {
    public BulletBoxDto(BulletBox bulletBox) {
        this(bulletBox.getId(),
                bulletBox.getKkCount(),
                bulletBox.getRteCount(),
                bulletBox.getInvalidCount(),
                bulletBox.getDistrict() == null ? null : bulletBox.getDistrict().getId(),
                bulletBox.getSchoolName(),
                bulletBox.getBulletBoxNumber(),
                bulletBox.getAttendant() == null ? null : bulletBox.getAttendant().getUsername());
    }
}
