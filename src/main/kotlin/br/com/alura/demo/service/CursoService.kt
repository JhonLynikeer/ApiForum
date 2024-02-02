package br.com.alura.demo.service

import br.com.alura.demo.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(var cursos: List<Curso>) {

    init {
        val curso = Curso(
                id = 1,
                nome = "Koltin",
                categoria = "Rpogramaçao"
        )
        cursos = listOf(curso)
    }

    fun buscarPorId(id: Long): Curso {
        return cursos.stream().filter {
            it.id == id
        }.findFirst().get()
    }

}
