package br.com.alura.demo.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NovoTopicoForm(
        @field: NotEmpty(message = "Titulo nao poder ser em branco")
        @field: Size(min = 5, max = 100, message = "Deve ter entre 5 e 100 caracteres")
        val titulo: String,
        @field: NotEmpty(message = "Mensagem nao poder ser em branco")
        val mensagem: String,
        @field: NotNull
        val idCurso: Long,
        @field: NotNull
        val idAutor: Long
)