package by.klishevich.vacancy_control_system.service;

import by.klishevich.vacancy_control_system.entity.user.UserEntity;
import by.klishevich.vacancy_control_system.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${jwt.token.secret}")
    private String SECRET;

    @Value("${jwt.token.expired}")
    private long EXPIRED;

    private final UserRepository userRepository;



    public Optional<UserEntity> resolveUser(final HttpServletRequest req){
        final String bearerToken = req.getHeader("Authorization");

        Optional<UserEntity> user = Optional.empty();

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return user;
        }

        String token = bearerToken.substring(7);

        if (!validateToken(token)){
            return user;
        }

        final Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Long userId = Long.parseLong(claims.getBody().get("sub", String.class));

        return userRepository.findById(userId);
    }

    public boolean validateToken(final String token) {
        try {
            final Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException expEx) {
            log.warn("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.warn("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.warn("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.warn("Invalid signature", sEx);
        } catch (Exception e) {
            log.warn("invalid token", e);
        }
        return false;
    }

    public String createToken(UserEntity user) {
        final Claims claims = Jwts.claims().setSubject(user.getId().toString());

        final Date now = new Date();
        final Date validity = new Date(new Date().getTime() + EXPIRED);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
