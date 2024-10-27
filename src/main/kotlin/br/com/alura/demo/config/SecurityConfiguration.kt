package br.com.alura.demo.config


import br.com.alura.demo.security.JwtAuthenticationFilter
import br.com.alura.demo.security.JwtLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val jwtUtil: JWTUtil
) {

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {

        http.invoke {
            csrf { disable() }
            authorizeRequests {
                //authorize("/topicos", hasAuthority("LEITURA_ESCRITA"))
                authorize(HttpMethod.POST, "/login", hasAuthority(permitAll))
                authorize(anyRequest, authenticated)
            }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(JwtLoginFilter(authenticationManager!!, jwtUtil))
            addFilterBefore<UsernamePasswordAuthenticationFilter>(JwtAuthenticationFilter(jwtUtil))
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
           // headers { frameOptions { disable() } }
           // httpBasic { }
        }
        return http.build()
    }

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

}