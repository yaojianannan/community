package com.example.demo.mapper;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sun.awt.SunHints;

import java.util.List;

/**
 * Created by BJB139 on 2019/7/23.
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into questions (title,description,gmt_create,gmt_modified,user_id,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{user_id},#{tag})")
    public void create(Question question);

    @Select("select * from questions limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from questions")
    Integer count();

    @Select("select * from questions where user_id = #{userId}  limit #{offset} , #{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from questions where user_id = #{userId} ")
    Integer countById(@Param(value = "userId") Integer userId);

    @Select("select * from questions where user_id = #{userId}")
    List<Question> selectByUserId(@Param(value = "userId") Integer userID);


    @Select("select * from questions where id = #{id}")
    Question getById(@Param(value = "id") Integer id);
}
