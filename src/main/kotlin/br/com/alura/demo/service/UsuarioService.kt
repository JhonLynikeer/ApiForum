package br.com.alura.demo.service

import br.com.alura.demo.model.Curso
import br.com.alura.demo.model.Usuario
import br.com.alura.demo.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) : UserDetailsService {


    fun buscarPorId(id: Long): Usuario {
        return repository.getOne(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
       val usuario = repository.findByEmail(username) ?: throw RuntimeException()
       return UserDetail(usuario)
    }

}
