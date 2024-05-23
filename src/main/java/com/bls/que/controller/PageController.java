package com.bls.que.controller;

import com.bls.que.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @projectName: bls-que
 * @package: com.bls.que.controller
 * @className: PageController
 * @author: huihui
 * @description: TODO
 * @date: 2024/4/30 11:04
 * @version: 1.0
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/goto/{page}",method = RequestMethod.GET)
    public ModelAndView gotoPage(ModelAndView modelAndView,@PathVariable("page") String page){
        modelAndView.setViewName(page);
        return modelAndView;
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public ModelAndView home(ModelAndView mv , Integer userId,String userName){
        mv.addObject("userId",userId);
        mv.addObject("userName",userName);
        mv.setViewName("home");
        return mv;
    }


    //调查问卷
    @RequestMapping(value = "/que",method = RequestMethod.GET)
    public ModelAndView queIndex(ModelAndView mv,String queId){
        if (historyService.queryHistoryByQueId(queId)){
            mv.addObject("queId",queId);
            mv.setViewName("que-index");
        }else {
            mv.setViewName("error");
        }
        return mv;
    }

    @RequestMapping(value = "/queNext",method = RequestMethod.GET)
    public ModelAndView que(ModelAndView mv,String queId){
        mv.addObject("queId",queId);
        mv.setViewName("que");
        return mv;
    }


    @RequestMapping(value = "gotoDisease",method = RequestMethod.GET)
    public ModelAndView gotoDisease(String name,ModelAndView mv,String disease){
        mv.addObject("name",name);
        mv.setViewName(disease);
        return mv;
    }


}
