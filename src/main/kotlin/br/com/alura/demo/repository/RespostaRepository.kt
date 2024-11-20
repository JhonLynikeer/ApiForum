package br.com.alura.demo.repository

import br.com.alura.demo.model.Resposta
import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository: JpaRepository<Resposta, Long>