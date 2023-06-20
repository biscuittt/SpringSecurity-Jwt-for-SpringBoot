package com.nbx.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author biscuitt
 * @version 1.0
 * @description Jwt 配置类
 * @date 2023/6/19 15:18
 */

@Component
@Data
@ConfigurationProperties(prefix = "nbx.jwt")
public class JwtUtils {

  private long expire;
  private String secret;
  private String header;

  // 生成JWT
  public String generateToken(String username, Map<String, Object> claims) {

    Date now = new Date();
    Date expireDate = new Date(now.getTime() + 1000 * expire);

    return Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setSubject(username)
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expireDate)    // 30天过期
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  // 解析JWT
  public Claims getClaimsByToken(String jwt) {
    try {
      return Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(jwt)
          .getBody();
    } catch (Exception e) {
      return null;
    }
  }

  // 判断JWT是否过期
  public boolean isTokenExpired(Claims claims) {
    return claims.getExpiration().before(new Date());
  }
}
