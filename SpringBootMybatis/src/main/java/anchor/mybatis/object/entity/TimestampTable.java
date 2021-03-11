package anchor.mybatis.object.entity;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Anchor
 */
@Data
public class TimestampTable {

    private String timestampOne;

    private Time timestampTwo;

    private Timestamp timestampThree;

    private Date timestampFour;

    private LocalDateTime timestampFive;

    private Instant timestampSix;

    public TimestampTable() {
//        this.timestampOne = "1592388213";              String类不能匹配mysql中的timestamp
        this.timestampOne = null;
//        this.timestampTwo = new Time(1592388213);      java.sql.Time类不能匹配mysql中的timestamp
        this.timestampTwo = null;
        Instant instant = Instant.now();
        this.timestampThree = new Timestamp(instant.toEpochMilli());
        this.timestampFour = new Date(instant.toEpochMilli());
        this.timestampFive = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
        this.timestampSix = instant;
    }
}
