package com.macro.gateway.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtils {

    /**
     * 生成jwt
     * @Param claims 内容
     * @Param secret 加密秘钥
     * @Param expireTime 过期时间 ms
     * @Return
     */
    public static String create(Map<String, Object> claims, String secret, Long expireTime) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expireTime);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * jwt验证
     * @Param authToken 令牌
     * @Param secret 秘钥
     * @Return
     */
    public static boolean validate(String authToken, String secret) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        }catch (Exception e) {
            log.error("[ jwt ] jwt令牌验证异常：{}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * jwt验证
     * @Param authToken 令牌
     * @Param secret 秘钥
     * @Return
     */
    public static Claims getClaim(String authToken, String secret) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(authToken)
                    .getBody();
        } catch (Exception e) {
            log.error("[ jwt ] jwt令牌验证异常：{}", e.getMessage(), e);
        }
        return claims;
    }




}
