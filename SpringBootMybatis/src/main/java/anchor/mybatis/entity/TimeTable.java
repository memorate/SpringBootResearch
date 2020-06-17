package anchor.mybatis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TimeTable {
    private String timeOne;

    private Date timeTwo;

    private Timestamp timeThree;

    private Time timeFour;

    private LocalDateTime timeFive;

    public TimeTable() {
        this.timeOne = "2020-03-20 16:35:20";
        this.timeTwo = new Date();
//        this.timeThree = new java.sql.Date(1584688952);    java.sql.Date类不能匹配mysql中的time
        this.timeThree = new Timestamp(1584688952);
        this.timeFour = new Time(1584688952);
        this.timeFive = LocalDateTime.now();
    }
}
