package anchor.mybatis.entity;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TimestampTable {

    private String timestampOne;

    private Time timestampTwo;

    private Timestamp timestampThree;

    private Date timestampFour;

    private LocalDateTime timestampFive;

    private Instant timestampSix;

    public TimestampTable() {
        this.timestampOne = "1592388213";
        this.timestampTwo = new Time(1592388213);
        this.timestampThree = new Timestamp(1592388213);
        this.timestampFour = new Date();
        this.timestampFive = LocalDateTime.now();
        this.timestampSix = Instant.now();
    }
}
