package br.com.alura.demo.config

import br.com.alura.demo.model.Role
import br.com.alura.demo.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JWTUtil(
    private val service : UsuarioService
) {

    val expiration: Long = 6000000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    // Utilizando uma única chave secreta para assinar e validar
    private val keySecret: SecretKey
        get() = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", authorities)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(keySecret) // Usando keySecret para assinar
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser()
                .setSigningKey(keySecret) // Usando keySecret para validar
                .build()
                .parseClaimsJws(jwt)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(jwt: String?): UsernamePasswordAuthenticationToken {
        val username = Jwts.parser()
            .setSigningKey(keySecret) // Usando keySecret para validar
            .build()
            .parseClaimsJws(jwt)
            .body.subject
        val user = service.loadUserByUsername(username)

        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}
