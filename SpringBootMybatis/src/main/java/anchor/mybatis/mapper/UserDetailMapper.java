package anchor.mybatis.mapper;

import anchor.mybatis.object.entity.UserDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anchor
 */
@Repository
public interface UserDetailMapper {

    int saveOne(UserDetail detail);

    int saveList(List<UserDetail> details);

    List<UserDetail> findAll(String orderBy);
}
