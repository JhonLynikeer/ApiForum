package br.com.alura.demo.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: org.springframework.security.access.AccessDeniedException?
    ) {
        response?.contentType = "application/json"
        response?.status = HttpServletResponse.SC_FORBIDDEN
        response?.writer?.write("""{"error": "Você não tem permissão para acessar este recurso"}""")
    }
}