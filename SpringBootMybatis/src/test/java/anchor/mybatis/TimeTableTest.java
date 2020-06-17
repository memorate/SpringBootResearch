package anchor.mybatis;

import anchor.mybatis.entity.TimeTable;
import anchor.mybatis.mapper.TimeTableMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeTableTest {

    @Resource
    private TimeTableMapper timeTableMapper;

    @Test
    public void timeInsert() {
        TimeTable timeTable = new TimeTable();
        System.out.println(timeTable);
        timeTableMapper.insert(timeTable);
    }

    @Test
    public void timeQuery() {
        TimeTable timeTable = timeTableMapper.findById(4);
        System.out.println(timeTable);
    }
}