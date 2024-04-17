package com.zdf.hejiayunweb.filter;

import com.zdf.internalcommon.constant.RedisConstant;
import com.zdf.internalcommon.result.TokenResult;
import com.zdf.internalcommon.util.JwtUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 *@Description Jwt authentication token filter
 *@Author mrzhang
 *@Date 2024/4/15 02:34
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (Objects.isNull(token)){
            filterChain.doFilter(request, response);
            return;
        }
        TokenResult tokenResult = JwtUtil.checkToken(token);
        if (Objects.isNull(tokenResult)){
            throw new IOException("token is error");
        }
        String redisKey = RedisConstant.USER_INFO_KEY_PREFIX + tokenResult.getUserName();
        UserDetails userInfo = (UserDetails) redisTemplate.opsForValue().get(redisKey);
        if (Objects.isNull(userInfo)){
            throw new IOException("token is expire");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(userInfo);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}