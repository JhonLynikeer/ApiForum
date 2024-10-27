package br.com.alura.demo.repository

import br.com.alura.demo.model.Curso
import br.com.alura.demo.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
    fun findByEmail(username: String?): Usuario?


}