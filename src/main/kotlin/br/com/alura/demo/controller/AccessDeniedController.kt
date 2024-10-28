package br.com.alura.demo.controller

import br.com.alura.demo.dto.ErrorView
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/access-denied")
class AccessDeniedController {

    @GetMapping
    fun handleAccessDenied(request: HttpServletRequest): ResponseEntity<ErrorView> {
        val errorView = ErrorView(
            status = HttpStatus.FORBIDDEN.value(),
            error = HttpStatus.FORBIDDEN.name,
            message = "Você precisa estar autenticado",
            path = request.servletPath
        )
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorView)
    }
}