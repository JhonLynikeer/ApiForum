package br.com.alura.demo.service

import br.com.alura.demo.model.Curso
import br.com.alura.demo.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(var usuarios: List<Usuario>) {

    init {
        val usuario = Usuario(
                id = 1,
                nome = "Ana Loka",
                email = "Ana@dadad.com"
        )
        usuarios = listOf(usuario)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter {
            it.id == id
        }.findFirst().get()
    }

}
