package br.com.alura.demo.repository

import br.com.alura.demo.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}