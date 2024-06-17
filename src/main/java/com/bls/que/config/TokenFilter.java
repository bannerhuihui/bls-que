package com.bls.que.config;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.bls.que.pojo.User;
import com.bls.que.service.UserService;
import com.bls.que.utils.TokenUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @projectName: bls-que
 * @package: com.bls.que.config
 * @className: CustomFilter
 * @author: huihui
 * @description: TODO
 * @date: 2024/6/17 10:34
 * @version: 1.0
 */
@Component
public class TokenFilter implements Filter {


    private UserService userService;

    public void init(FilterConfig filterConfig) throws ServletException {
        // 这里可以放置过滤器初始化代码
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();
        if (checkUrl(requestURI)) {
            // 放行请求，不应用 TokenFilter 逻辑
            chain.doFilter(request, response);
            return;
        }else {
            boolean isAjaxRequest = "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With"));
            String token = httpRequest.getHeader("Authorization");
            // 做token校验
            String userName = TokenUtil.verifyToken(token);
            User user = userService.queryUserByUserName(userName);
            if(user != null){
                if(StrUtil.equals(token,TokenUtil.generateToken(userName))){//toke合法
                    if(user.getTokenDate() != null && DateUtil.between(user.getTokenDate(),new Date(), DateUnit.MINUTE) < 15){ //token15分钟过期
                        //更新token时间，放行
                        user.setTokenDate(new Date());
                        userService.updateUser(user);
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
            String scheme = httpRequest.getScheme();             // http 或 https
            String serverName = httpRequest.getServerName();     // 主机名，如 localhost
            int serverPort = httpRequest.getServerPort();        // 端口号，如 8888
            String contextPath = httpRequest.getContextPath();   // 上下文路径
            // 仅在端口号不等于默认端口时添加端口号部分
            String portString = (serverPort == 80 || serverPort == 443) ? "" : ":" + serverPort;
            String http = scheme + "://" + serverName + portString + contextPath + "/page/goto/index";
            System.out.println(http);
            if(isAjaxRequest){
                httpResponse.setStatus(HttpServletResponse.SC_OK);
                httpResponse.setHeader("X-Redirect", "/page/goto/index");
            }else {
                httpResponse.sendRedirect(http);
            }

        }
    }

    @Override
    public void destroy() {
        // 这里可以放置过滤器销毁代码
    }


    private boolean checkUrl(String url){
        boolean flag = false;
        if(StrUtil.equals("/page/create-history.html",url)){
            flag = true;
        }else if (StrUtil.equals("/page/create-user.html",url)){
            flag = true;
        }else if (StrUtil.equals("/page/update-history.html",url)){
            flag = true;
        }else if (StrUtil.equals("/page/goto/index",url)){
            flag = true;
        }else if (StrUtil.equals("/user/login",url)){
            flag = true;
        }else if (StrUtil.equals("/page/home",url)){
            flag = true;
        }else if (StrUtil.equals("/page/history-list.html",url)){
            flag = true;
        }else if (StrUtil.equals("/page/user-list.html",url)){
            flag = true;
        }
        return flag;
    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}
