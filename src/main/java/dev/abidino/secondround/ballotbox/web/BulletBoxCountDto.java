package dev.abidino.secondround.ballotbox.web;

import dev.abidino.secondround.ballotbox.business.BulletBox;

import java.util.List;

public record BulletBoxCountDto(long kkCount,
                                long rteCount,
                                long invalidCount) {
    public static BulletBoxCountDto generateBulletBoxCountDto(List<BulletBox> bulletBoxList) {
        long kkCount = 0;
        long rteCount = 0;
        long invalidCount = 0;
        for (BulletBox bulletBox : bulletBoxList) {
            kkCount = kkCount + bulletBox.getKkCount();
            rteCount = rteCount + bulletBox.getRteCount();
            invalidCount = invalidCount + bulletBox.getInvalidCount();
        }
        return new BulletBoxCountDto(kkCount, rteCount, invalidCount);
    }


}
