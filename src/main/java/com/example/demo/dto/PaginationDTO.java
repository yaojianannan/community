package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BJB139 on 2019/7/26.
 */
@Data
public class PaginationDTO {
     private List<QuestionDTO> questions;
     private Boolean showPrevious;//是否有向前按钮
     private Boolean showFirstPage;//是否有首页按钮
     private Boolean showNext;//是否有下一页按钮
     private Boolean showEndPage;//是否有尾页按钮
     private Integer page;//当前页
     private List<Integer> pages = new ArrayList<>();//页码数组
     private Integer totalPage;//总页数

     public void setPagination(Integer totalCount, Integer page, Integer size) {

        //总页数
       totalPage = (totalCount /size == 0) ? (totalCount/size) : ((totalCount/size) + 1);

         if(page<1){ page = 1;}
         if(page >totalPage) {page = totalPage;}
           this.page = page;
        //页面展示页码数
       pages.add(page);
       for(int i = 1; i <= 3; i++)
       {
           if(page - i > 0){
               pages.add(0,page - i);
           }
           if(page + i <= totalPage){
               pages.add(page + i);
           }
       }
        //是否展示上一页
       showPrevious = (page == 1) ? false:true;

        //是否展示下一页
       showNext = (page == totalPage) ? false : true;

        //是否展示第一页
        showFirstPage = (pages.contains(1)) ? false : true;

        //是否展示最后一页
        showEndPage = (pages.contains(totalPage)) ? false : true;

      }



}
