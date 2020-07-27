package anchor.mybatis.mapper;

import anchor.mybatis.entity.UserDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailMapper {

    int saveOne(UserDetail detail);

    int saveList(List<UserDetail> details);

    List<UserDetail> findAll(String orderBy);
}