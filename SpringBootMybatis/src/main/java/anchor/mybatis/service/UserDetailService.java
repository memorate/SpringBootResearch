package anchor.mybatis.service;

import anchor.mybatis.object.entity.UserDetail;

import java.util.List;

public interface UserDetailService {
    
    int saveOne(UserDetail detail);

    int saveList(List<UserDetail> details);

    List<UserDetail> findAll(String orderBy);
}
