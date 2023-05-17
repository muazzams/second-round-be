package dev.abidino.secondround.auth;

import java.time.LocalDateTime;

public record TokenResource(String token, LocalDateTime expireDate) {
}