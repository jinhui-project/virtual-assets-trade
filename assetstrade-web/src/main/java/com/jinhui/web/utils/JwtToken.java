package com.jinhui.web.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt token
 *
 * @autor wsc
 * @create 2018-03-29 16:31
 **/
public class JwtToken {

    /**
     * 公钥
     */
    private static String SECRET_KEY = "ZJjsdls3$4dfllk4450fglkdgldgflgfk34%";

    public static String createToken(String str) throws Exception {
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,60);
        Date expireDate = calendar.getTime();


        Map<String,Object> map = new HashMap<String,Object>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        String token = JWT.create()
                          .withHeader(map)
                          .withClaim("project","assetstrade")
                          .withClaim("remark","trade")
                          .withClaim("userId",str)
                          .withExpiresAt(expireDate)
                          .withIssuedAt(date)
                          .sign(Algorithm.HMAC256(SECRET_KEY));
        return  token;
    }

    public static Map<String,Claim> verifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaims();
    }

    public static String getUserId(String token) throws Exception {
        Map<String, Claim> map = verifyToken(token);
        return map.get("userId").asString();
    }


    public static void main(String[] args) {
        try {
            String token = JwtToken.createToken("M9900000002");
            System.out.println(token);
            System.out.println(getUserId(token));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
