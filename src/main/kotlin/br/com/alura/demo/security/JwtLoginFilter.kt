package br.com.alura.demo.security

import br.com.alura.demo.config.JWTUtil
import br.com.alura.demo.model.Credentials
import br.com.alura.demo.model.Usuario
import br.com.alura.demo.service.UserDetail
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
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
        val user = authResult?.principal as UserDetails
        val userDetails = authResult?.principal as UserDetail
        val token = jwtUtil.generateToken(user.username, user.authorities)

        // Suponha que o UserDetails tenha um método getUser que retorne uma instância do seu usuário
        // Ou você pode ter um objeto User que armazena todas as informações necessárias.
        // Substitua por sua implementação de UserDetails
        val userInfo = mapOf(
            "id" to userDetails.getUsuario().id,
            "roles" to user.authorities.map { it.authority },
            "name" to userDetails.getUsuario().nome, // Ou qualquer método que você tenha para obter o nome
            "email" to user.username // Ou qualquer método que você tenha para obter o email
        )

        // Cria o objeto de resposta
        val responseBody = mapOf(
            "token" to token,
            "user" to userInfo
        )

        // Configura o tipo de conteúdo da resposta
        response?.contentType = "application/json"
        response?.status = HttpServletResponse.SC_OK

        // Converte o objeto de resposta para JSON
        val objectMapper = ObjectMapper()
        val jsonResponse = objectMapper.writeValueAsString(responseBody)

        // Escreve o JSON na resposta
        response?.writer?.write(jsonResponse)
        response?.writer?.flush()
    }

}
