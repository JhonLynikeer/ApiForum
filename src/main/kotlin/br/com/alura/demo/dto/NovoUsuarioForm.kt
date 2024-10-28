package br.com.alura.demo.dto

import br.com.alura.demo.model.Role
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NovoUsuarioForm(
        @field: NotEmpty(message = "Titulo nao poder ser em branco")
        val nome: String,
        @field: NotEmpty(message = "Email nao pode ser vazio")
        val email: String,
        @field: NotEmpty(message = "Password nao pode ser vazio")
        @field: Size(min = 8, max = 10, message = "Deve ter entre 8 e 10 caracteres")
        val password: String,
        @field: NotEmpty(message = "Role nao pode ser vazio")
        val role: List<Role> = mutableListOf()
)