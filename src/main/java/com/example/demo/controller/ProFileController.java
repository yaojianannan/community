package com.example.demo.controller;

import com.example.demo.dto.PaginationDTO;
import com.example.demo.model.User;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by BJB139 on 2019/7/28.
 */
@Controller
public class ProFileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action ,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name ="page" ,defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "2") Integer size){

        User user = (User)request.getSession().getAttribute("user");
        if (user == null)
        {
            return "redirect:/";
        }
        if("question".equals(action))
        {
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO paginationDTO = questionService.list(user.getId(),page,size);
            model.addAttribute("paginations",paginationDTO);

        }else if("replies".equals(action))
        {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            PaginationDTO paginationDTO = questionService.list(user.getId(),page,size);
            model.addAttribute("paginations",paginationDTO);
        }


        return "profile";
    }
}
