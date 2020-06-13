import anchor.mybatis.entity.TimeTable;
import anchor.mybatis.mapper.TimeTableMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TimeTableTest {

    @Resource
    private TimeTableMapper timeTableMapper;

    @Test
    @Rollback(false)
    public void test() {
        timeTableMapper.insert(new TimeTable());
    }
}