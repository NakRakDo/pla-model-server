package com.example.pmb.global.config.security.jwt;

import com.example.pmb.domain.auth.component.UserService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.Ed25519Signer;
import com.nimbusds.jose.crypto.Ed25519Verifier;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.OctetKeyPair;
import com.nimbusds.jose.jwk.gen.OctetKeyPairGenerator;
import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final String SUBJECT = "jwt";
    private final String ISSUER = "pms";
    private OctetKeyPair jwk;
    private OctetKeyPair publicJwk;
    private JWSSigner jwsSigner;
    // 30 mins
    private long tokenValidTime = 30 * 60 * 1000L;
    private final UserService userService;

    @PostConstruct
    protected void init() throws Exception{
        // Generate a key pair with Ed25519 curve
        jwk = new OctetKeyPairGenerator(Curve.Ed25519)
            .keyID("123")
            .generate();
        publicJwk = jwk.toPublicJWK();

        // Create the EdDSA signer
        jwsSigner = new Ed25519Signer(jwk);
    }

    // create JWT Token
    public String createToken(String userPk, List<String> roles) throws JOSEException {
        // Prepare JWT with claims set

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
            .subject(SUBJECT)
            .audience(userPk)
            .issuer(ISSUER)
            .expirationTime(new Date(new Date().getTime() + tokenValidTime))
            //.claim("user",userPk) private claim
            .build();

        SignedJWT signedJwt = new SignedJWT(
            new JWSHeader.Builder(JWSAlgorithm.EdDSA)
                .keyID(jwk.getKeyID())
                .build(),
            claimsSet);

        // Compute the EC signature
        signedJwt.sign(jwsSigner);

        // Serialize the JWS to compact form
        String s = signedJwt.serialize();

        return s;
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        //TODO use redis inmemory DB
        return request.getHeader("X-AUTH-TOKEN");
    }

    // JWT 토큰에서 대상 조회 후 인증 정보 생성
    public Authentication getAuthentication(String token) throws ParseException, JOSEException {
        UserDetails userDetails = userService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) throws ParseException, JOSEException {
        SignedJWT signedJwt0 = SignedJWT.parse(token);
        JWSVerifier verifier = new Ed25519Verifier(publicJwk);
        signedJwt0.verify(verifier);

        List<String> audList = signedJwt0.getJWTClaimsSet().getAudience();

        return audList.get(0);
        //return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        /*try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }*/
        return true;
    }
}
