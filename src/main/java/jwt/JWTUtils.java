package jwt;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JWTUtils {

    private String jwtSecretKey ="LongTran";
    private String User ="LongTran";

    private Long jwtExpirationMs=1000000L;

    private Long jwtRefreshExpirationMs =10000000L;

    public String generateJwtToken() {

        return Jwts.builder()
                .setSubject(User)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        JWTUtils jwtUtils = new JWTUtils();
        System.out.println(jwtUtils.generateJwtToken());

    }

}
