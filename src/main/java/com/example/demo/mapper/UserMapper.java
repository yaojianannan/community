package com.example.demo.mapper;


import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;


/**
 * Created by BJB139 on 2019/7/21.
 *
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,avatar_url) values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
   void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{account_id}")
    User findByAccountId(@Param(value = "account_id") String account_id);

    @Update("update user set name = #{name},token = #{token},gmt_modified = #{gmt_modified},avatar_url = #{avatar_url} where id = #{id}")
    void update(User user);
}
