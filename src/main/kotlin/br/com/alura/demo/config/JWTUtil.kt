package br.com.alura.demo.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JWTUtil {

    val expitation: Long = 60000
    @Value("\${jwt.secret}")
    private lateinit var secret : String
    fun generateToken(username: String): String? {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + expitation))
            .signWith(SignatureAlgorithm.ES512, secret.toByteArray())
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).build()
            true
        } catch (e: IllegalArgumentException){
            false
        }
    }

    fun getAuthentication(jwt: String?) : Authentication {
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).build()
        return UsernamePasswordAuthenticationToken(username, null, null)
    }

}