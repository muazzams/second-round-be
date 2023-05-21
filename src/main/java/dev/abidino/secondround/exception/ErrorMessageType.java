package dev.abidino.secondround.exception;

public enum ErrorMessageType {

    UNAUTHORIZED("Lutfen giris yapiniz."),
    USERNAME_NOT_FOUND("User bulunamadı."),
    BULLET_BOX_NOT_FOUND("Sandik Kutusu bulunamadı."),
    USER_HAS_NOT_BULLET_BOX("Herhangi bir sandikta gorevli degilsiniz."),
    USERNAME_ALREADY_EXIST("Kullanıcı adı daha önceden alınmış."),
    USERNAME_AND_PASSWORD_NOT_MATCH("Kullanıcı adı veya parola doğru değil."),
    ERROR_MAIL_SEND("Mail gönderilirken hata meydana geldi."),
    EXPIRE_TOKEN("Oturumunuzun süresi dolmuş, lütfen tekrar giriş yapınız."),
    GENERIC_ERROR("Sistem kaynakli bir sorun olustu. Lutfen daha sonra tekrar deneyiniz."),
    FORBIDDEN("Yetkiniz yoktur."),
    DISTRICT_NOT_FOUND("Ilce bulunamadi.");

    ErrorMessageType(String message) {
        this.message = message;
    }

    private final String message;

    public String getMessage() {
        return message;
    }
}
