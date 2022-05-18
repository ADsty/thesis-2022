package ru.vitaliy.petrov.server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ForbiddenApiException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${server.jwt.secret.key}")
    private String SECRET_KEY;

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public void validateToken(String token, UsersDetails usersDetails) {
        try {
            final Claims claims = extractAllClaims(token);
            final String login = claims.getSubject();
            final Long id = claims.get("id", Long.class);
            final String password = claims.get("password", String.class);
            final String role = claims.get("role", String.class);

            if (!(usersDetails.getUser().getUserPhoneNumber().equals(login)
                    && usersDetails.getUser().getId().equals(id)
                    && usersDetails.getPassword().equals(password)
                    && usersDetails.getUser().getUserRole().getUserRoleName().equals(role)
                    && !isTokenExpired(token))) {
                throw new ForbiddenApiException("Некорректные поля токена");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ForbiddenApiException("Некорректный токен");
        }
    }

    public String generateToken(UsersDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", userDetails.getUser().getId());
        claims.put("password", userDetails.getPassword());
        claims.put("role", userDetails.getUser().getUserRole());

        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        final long currentTimeMillis = System.currentTimeMillis();
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 3_600_000_000L))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims extractAllClaimsFromHeader(String jwtTokenHeader) {
        String jwtToken;
        if (jwtTokenHeader.startsWith("Bearer ")) {
            jwtToken = jwtTokenHeader.substring(7);
        } else {
            throw new ForbiddenApiException("Некорректный префикс токена");
        }
        return extractAllClaims(jwtToken);
    }

}
