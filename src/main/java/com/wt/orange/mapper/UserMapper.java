package com.wt.orange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wt.orange.entity.User;
import com.wt.orange.entity.UserPermission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p> 用户相关操作 </p>
 *
 * @author Wang Tao
 * @date 2021-02-02 19:27:08
 */
@Component
public interface UserMapper extends BaseMapper<User> {

    /**
     * <p>根据用户id获取用户权限列表</p>
     *
     * @param userId 用户id
     * @return List 用户权限列表
     * @author Wang Tao
     * @date 2021-02-06 16:53:36
     */
    @Select("SELECT r.role_name roleName,r.permission rolePermission,m.menu_name menuName,m.url,m.permission menuPermission FROM user_role ur LEFT JOIN role r ON r.id = ur.role_id LEFT JOIN role_menu rm ON r.id = rm.role_id LEFT JOIN menu m ON m.id = rm.menu_id WHERE ur.user_id=#{userId}")
    List<UserPermission> getUserPermissionByUserId(Long userId);
}
