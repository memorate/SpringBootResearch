package anchor.mybatis.service.impl;

import anchor.mybatis.mapper.UserDetailMapper;
import anchor.mybatis.object.constant.UserDetailColumn;
import anchor.mybatis.object.entity.UserDetail;
import anchor.mybatis.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Anchor
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Resource
    private UserDetailMapper detailMapper;

    @Override
    public int saveOne(UserDetail detail) {
        return detailMapper.saveOne(detail);
    }

    @Override
    public int saveList(List<UserDetail> details) {
        return detailMapper.saveList(details);
    }

    @Override
    public List<UserDetail> findAll(String orderBy) {
        String sort = StringUtils.isEmpty(orderBy) ? UserDetailColumn.ID : orderBy;
        return detailMapper.findAll(sort);
    }
}
