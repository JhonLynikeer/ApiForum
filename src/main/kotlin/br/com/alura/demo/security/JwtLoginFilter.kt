package br.com.alura.demo.security

import br.com.alura.demo.config.JWTUtil
import br.com.alura.demo.model.Credentials
import br.com.alura.demo.service.UserDetail
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtLoginFilter(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val(username, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)
        val token  = UsernamePasswordAuthenticationToken(username, password)
        return authenticationManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
       val username = (authResult?.principal as UserDetail).username
       val token = jwtUtil.generateToken(username)
        response?.addHeader("Authorization", "Bearer $token")
    }

}
