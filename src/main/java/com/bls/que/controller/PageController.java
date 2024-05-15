package com.bls.que.controller;

import com.bls.que.pojo.History;
import com.bls.que.service.HistoryService;
import com.bls.que.vo.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(value = "/index.do",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mv){
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model,Integer userId,String userName){
        //获取该用户的订单列表
        model.addAttribute("userName",userName);
        model.addAttribute("userId",userId);
        return "list";
    }

    //调查问卷
    @RequestMapping(value = "que",method = RequestMethod.GET)
    public ModelAndView que(ModelAndView mv,String queId){
        if (historyService.queryHistoryByQueId(queId)){
            mv.addObject("queId",queId);
            mv.setViewName("que");
        }else {
            mv.setViewName("error");
        }
        return mv;
    }

    //跳转更新页面
    @RequestMapping(value = "update",method = RequestMethod.GET)
    public ModelAndView gotoUpdatePage(int id,ModelAndView mv){
        mv.addObject("id",id);
        mv.setViewName("update");
        return mv;
    }

    @RequestMapping(value = "success",method = RequestMethod.GET)
    public ModelAndView success(ModelAndView mv){
        mv.setViewName("success");
        return mv;
    }


    @RequestMapping(value = "error",method = RequestMethod.GET)
    public ModelAndView error(ModelAndView mv){
        mv.setViewName("error");
        return mv;
    }
}
