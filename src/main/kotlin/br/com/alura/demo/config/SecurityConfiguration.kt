package br.com.alura.demo.config


import br.com.alura.demo.controller.CustomAccessDeniedHandler
import br.com.alura.demo.controller.CustomAuthenticationEntryPoint
import br.com.alura.demo.security.JwtAuthenticationFilter
import br.com.alura.demo.security.JwtLoginFilter
import br.com.alura.demo.service.UsuarioService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val usuarioService: UsuarioService,
    private val jwtUtil: JWTUtil
) {


    @Bean
    fun filterChain(http: HttpSecurity, authManager: AuthenticationManager): SecurityFilterChain {
        http.authorizeHttpRequests { it
            .requestMatchers("/topicos").hasAuthority("LEITURA_ESCRITA")
            .requestMatchers(HttpMethod.POST, "/login").permitAll()
            .requestMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()
            .requestMatchers(HttpMethod.GET, "/swagger-ui.html/").permitAll()
            .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/webjars/swagger-ui/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
            .anyRequest()
            .authenticated()
        }
            .csrf { it.disable() }
            .exceptionHandling { exceptions ->
                exceptions.authenticationEntryPoint(CustomAuthenticationEntryPoint())
                exceptions.accessDeniedHandler(CustomAccessDeniedHandler())
            }
            .addFilterBefore(JwtLoginFilter(authenticationManager = authManager, jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(JwtAuthenticationFilter(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        val authBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authBuilder.userDetailsService(usuarioService).passwordEncoder(bCryptPasswordEncoder())
        return authBuilder.build()
    }



    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
