package anchor.mybatis.object.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Anchor
 */
@Data
public class DatetimeTable {

    private String datetimeOne;

    private Date datetimeTwo;

    private java.sql.Date datetimeThree;

    private Instant datetimeFour;

    private LocalDateTime datetimeFive;

    private Timestamp datetimeSix;

    public DatetimeTable() {
        this.datetimeOne = "2020-03-20 16:35:20";
        Instant instant = Instant.now();
        this.datetimeTwo = new Date(instant.toEpochMilli());
        this.datetimeThree = new java.sql.Date(instant.toEpochMilli());
        this.datetimeFour = instant;
        this.datetimeFive = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
        this.datetimeSix = new Timestamp(instant.toEpochMilli());
    }
}
