package com.bls.que.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @projectName: bls-que
 * @package: com.bls.que.utils
 * @className: JwtUtil
 * @author: huihui
 * @description: TODO
 * @date: 2024/6/17 10:21
 * @version: 1.0
 */
public class TokenUtil {


    private static final String SECRET = "Hyd-token";

    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);
    private static final JWTVerifier verifier = JWT.require(algorithm).build();

    public static String generateToken(String str) {
        String token = JWT.create()
                .withSubject(str)
                .sign(algorithm);
        return token;
    }

    public static String verifyToken(String token) {
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

}
