package br.com.alura.demo.service

import br.com.alura.demo.exception.NotFoundExcepetion
import br.com.alura.demo.mapper.TopicoFormMapper
import br.com.alura.demo.mapper.TopicoViewMapper
import br.com.alura.demo.model.TopicoTest
import br.com.alura.demo.model.TopicoViewTest
import br.com.alura.demo.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*


class TopicoServiceTest {

    val topicos = PageImpl(listOf(TopicoTest.build()))

    val paginacao: Pageable = mockk()

    val topicoRepository: TopicoRepository = mockk {
        every {findByCursoNome(any(), any())} returns topicos
        every {findAll(paginacao)}  returns topicos
    }
    val topicoViewMapper: TopicoViewMapper = mockk {
        every { map(any()) } returns TopicoViewTest.build()
    }
    val topicoFormMapper: TopicoFormMapper = mockk()


    val topicoService = TopicoService(
        topicoRepository, topicoViewMapper, topicoFormMapper
    )

    @Test
    fun `deve listar topicos a partir do nome do curso` () {

        topicoService.listar("Kotlin avançado", paginacao)

        verify(exactly = 1) { topicoRepository.findByCursoNome(any(),any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve listar todos os topicos quando o nome do curso for nulo` (){
        topicoService.listar(null, paginacao)

        verify(exactly = 0) { topicoRepository.findByCursoNome(any(),any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 1) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve listar not found excepition quando topico nao for achado` (){
        every { topicoRepository.findById(any()) } returns Optional.empty()

        val atual = assertThrows<NotFoundExcepetion> {
            topicoService.buscarPorId(1)
        }

        MatcherAssert.assertThat(atual.message, atual.message == "Topico nao encontrado")

    }



}