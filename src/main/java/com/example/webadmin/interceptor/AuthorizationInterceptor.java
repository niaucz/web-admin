package com.example.webadmin.interceptor;

import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.webadmin.common.GlobalConstant;
import com.example.webadmin.entity.User;
import com.example.webadmin.exception.WebException;
import com.example.webadmin.service.UserService;
import com.example.webadmin.utils.JwtUtils;
import com.example.webadmin.utils.ThreadLocalMap;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 权限(Token)验证
 */
@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) {

        String requestURI = request.getRequestURI();
        log.debug("请求URI:{}", URLUtil.decode(requestURI));
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((k, v) -> log.debug("请求参数:{}={}", k, JSONUtil.toJsonStr(v)));

        // 获取用户凭证
        String token = request.getHeader(jwtUtils.getHeader());
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(jwtUtils.getHeader());
        }

        // 凭证为空
        if (StringUtils.isBlank(token)) {
            throw new WebException(jwtUtils.getHeader() + "不能为空", HttpStatus.UNAUTHORIZED.value());
        }

        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
            throw new WebException(jwtUtils.getHeader() + "失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
        }
        //数据库取
        User userInfo = userService.getUserInfoByName(claims.getSubject());
        // 放入userInfo
        ThreadLocalMap.put(GlobalConstant.TOKEN_LOGIN_USER, userInfo);
        return true;
    }
}