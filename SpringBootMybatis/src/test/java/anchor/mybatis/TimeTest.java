package anchor.mybatis;

import anchor.mybatis.entity.TimeTable;
import anchor.mybatis.entity.TimestampTable;
import anchor.mybatis.mapper.TimeTableMapper;
import anchor.mybatis.mapper.TimestampTableMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeTest {

    @Resource
    private TimeTableMapper timeTableMapper;

    @Resource
    private TimestampTableMapper timestampTableMapper;

    @Test
    public void timeInsert() {
        TimeTable timeTable = new TimeTable();
        System.out.println(timeTable);
        timeTableMapper.insert(timeTable);
    }

    @Test
    public void timeQuery() {
        TimeTable time = timeTableMapper.findById(4);
        System.out.println(time);
    }

    @Test
    public void timestampInsert() {
        TimestampTable timestampTable = new TimestampTable();
        System.out.println(timestampTable);
        timestampTableMapper.insert(timestampTable);
    }

    @Test
    public void timestampQuery() {
        TimestampTable timestamp = timestampTableMapper.findById(4);
        System.out.println(timestamp);
    }
}