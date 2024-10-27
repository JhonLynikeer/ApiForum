package br.com.alura.demo.service

import br.com.alura.demo.model.Curso
import br.com.alura.demo.repository.CursoRepository
import br.com.alura.demo.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository) {
    fun buscarPorId(id: Long): Curso {
        return repository.getOne(id)
    }

}
