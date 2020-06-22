package anchor.mybatis.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
public class DateTable {

    private String dateOne;

    private Date dateTwo;

    private java.sql.Date dateThree;

    private Instant dateFour;

    private LocalDateTime dateFive;

    private Timestamp dateSix;

    public DateTable() {
        this.dateOne = "2020-03-20 16:35:20";
        Instant instant = Instant.now();
        this.dateTwo = new Date(instant.toEpochMilli());
        this.dateThree = new java.sql.Date(instant.toEpochMilli());
        this.dateFour = instant;
        this.dateFive = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
        this.dateSix = new Timestamp(instant.toEpochMilli());
    }
}
