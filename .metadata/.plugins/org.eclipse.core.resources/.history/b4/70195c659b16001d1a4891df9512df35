package com.spark.rest.api.utils;

import java.util.Date;
import java.util.UUID;

import com.spark.rest.api.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

public final class JwtUtils {

	private static final long EXPIRATION_TIME = 10 * 60 * 1000l; // 10 minutes
	private static final String JWT_SECRET_KEY = "Pritam Ray";

	public static final String generateToken(User user) {
		DefaultClaims claims = new DefaultClaims();
		claims.setId(UUID.randomUUID().toString());
		claims.put("email", user.getEmail());
		claims.setSubject(user.getUsername());

		return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY).compact();
	}

	// UserPrincipal
	public static final UserPrincipal getUserPrincipal(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
		return UserPrincipal.of(claims.getSubject(), (String) claims.get("email"));
	}

}
