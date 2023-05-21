package dev.abidino.secondround.security;

import dev.abidino.secondround.auth.TokenResource;
import dev.abidino.secondround.exception.ErrorMessageType;
import dev.abidino.secondround.exception.GenericException;
import dev.abidino.secondround.user.business.User;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

public interface JwtTokenUtil {

    String SECRET_KEY_ALGORITHM = "HmacSHA512";

    static TokenResource generateToken(User user) {
        String token = user.getUsername() + "&" + user.getRole() + "&" + calculateHmac(user.getUsername());
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        return new TokenResource(token, localDateTime);
    }

    static String calculateHmac(String username) {
        byte[] secretBytes = SecurityConstant.SECRET.getBytes(StandardCharsets.UTF_8);
        byte[] valueBytes = username.getBytes(StandardCharsets.UTF_8);

        try {
            Mac mac = Mac.getInstance(SECRET_KEY_ALGORITHM);
            SecretKeySpec sec = new SecretKeySpec(secretBytes, SECRET_KEY_ALGORITHM);
            mac.init(sec);
            byte[] hmacBytes = mac.doFinal(valueBytes);
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new GenericException(ErrorMessageType.GENERIC_ERROR.getMessage());
        }
    }
}