package com.example.demo.controller;


import com.example.demo.dto.PaginationDTO;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by BJB139 on 2019/7/14.
 */
@Controller
public class IndexController {

   @Autowired
   private QuestionService questionService;

   @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name ="page" ,defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "2") Integer size){

       PaginationDTO pagination = questionService.list(page,size);
       model.addAttribute("paginations",pagination);

        return "index";
    }
}
