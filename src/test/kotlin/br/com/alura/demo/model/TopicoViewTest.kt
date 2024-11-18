package br.com.alura.demo.model

import br.com.alura.demo.dto.TopicoView
import java.time.LocalDate
import java.time.LocalDateTime

object TopicoViewTest {
    fun build() = TopicoView(
        id = 1,
        titulo = "Kotlin Basico",
        mensagem = "Aprendendo kotlin basico",
        status = StatusTopico.NAO_RESPONDIDO,
        dataCriacao = LocalDateTime.now(),
        dataAlteracao = LocalDate.now()
    )
}