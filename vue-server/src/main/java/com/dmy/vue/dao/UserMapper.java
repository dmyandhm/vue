package com.dmy.vue.dao;

import com.dmy.vue.domain.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: daimengying
 * @Date: 2018/5/17 14:31
 * @Description:
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends BaseMapper<Users> {

    @Select({"<script>",
            "update g_users u "+
            "set u.balance=u.balance+#{chargeMoney} "+
            "WHERE u.username=#{username} ",
            "</script>"})
    Integer updateAgentBalance(@Param("username") String username, @Param("chargeMoney") Double chargeMoney);

}
