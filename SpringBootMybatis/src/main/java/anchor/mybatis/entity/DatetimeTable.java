package anchor.mybatis.entity;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
public class DatetimeTable {

    private String datetimeOne;

    private Date datetimeTwo;

    private java.sql.Date datetimeThree;

    private Instant datetimeFour;

    private LocalDateTime datetimeFive;

    public DatetimeTable() {
        this.datetimeOne = "2020-03-20 16:35:20";
        Instant instant = Instant.now();
        this.datetimeTwo = new Date(instant.toEpochMilli());
        this.datetimeThree = new java.sql.Date(instant.toEpochMilli());
        this.datetimeFour = instant;
        this.datetimeFive = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
    }
}
