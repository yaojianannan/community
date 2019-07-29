package com.example.demo.service;

import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BJB139 on 2019/7/24.
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();//数据库中所有数据的条数
        paginationDTO.setPagination(totalCount,page,size);
        //控制数据库中页面数
        if(page<1){ page = 1;}
        if(page > paginationDTO.getTotalPage()) {page = paginationDTO.getTotalPage();}
        //offset偏移  size 查询条数
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //把user与question关联
        for (Question question : questionList)
        {
            User user = userMapper.findById(question.getUser_id());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        //封装
        paginationDTO.setQuestions(questionDTOList);
//        for(QuestionDTO question : paginationDTO.getQuestions()){
//            System.out.println(question.getUser().getToken());
//                   System.out.println(question.getUser().getAvatar_url());
//        }
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countById(userId);//数据库中所有数据的条数

        paginationDTO.setPagination(totalCount,page,size);

        //控制数据库中页面数
        if(page<1){ page = 1;}
        if(page > paginationDTO.getTotalPage()) {page = paginationDTO.getTotalPage();}

        //offset偏移  size 查询条数
        Integer offset = size * (page - 1);
        List<Question> questionList = new ArrayList<>();
        List<Question> questions = questionMapper.selectByUserId(userId);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //如果totalCount = 0,即当前登录用户没有发布过问题时
       if(totalCount == 0) {questionDTOList.add(null);}
       else{
           //根据Userid查询数据实现分页
           questionList = questionMapper.listByUserId(userId,offset,size);
           //把user与question关联
           for (Question question : questionList)
           {
               User user = userMapper.findById(question.getUser_id());
               QuestionDTO questionDTO = new QuestionDTO();
               BeanUtils.copyProperties(question,questionDTO);
               questionDTO.setUser(user);
               questionDTOList.add(questionDTO);

           }
       }

        //封装
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
     }


    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        return  questionDTO;
    }
}

