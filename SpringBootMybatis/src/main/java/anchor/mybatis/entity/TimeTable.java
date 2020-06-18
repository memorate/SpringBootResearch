package anchor.mybatis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
public class TimeTable {
    private String timeOne;

    private Date timeTwo;

    private Timestamp timeThree;

    private Time timeFour;

    private LocalDateTime timeFive;

    private Instant timeSix;

    public TimeTable() {
        this.timeOne = "2020-03-20 16:35:20";
        this.timeTwo = new Date();
//        this.timeThree = new java.sql.Date(1584688952);    java.sql.Date类不能匹配mysql中的time
        Instant instant = Instant.now();
        this.timeThree = new Timestamp(instant.toEpochMilli());
        this.timeFour = new Time(instant.toEpochMilli());
        this.timeFive = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
        this.timeSix = instant;
    }
}
