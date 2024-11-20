package br.com.alura.demo.integration

import br.com.alura.demo.configuration.DataBaseConfigurationTest
import br.com.alura.demo.dto.TopicoPorCategoriaDto
import br.com.alura.demo.model.TopicoTest
import br.com.alura.demo.repository.TopicoRepository
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TopicoRepositoryTest : DataBaseConfigurationTest() {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    private val topicos = TopicoTest.build()


    @Test
    fun `deve gerar um relatorio`() {
        topicoRepository.save(topicos)
        val relatorio = topicoRepository.relatorio()


        MatcherAssert.assertThat("O relatório não deve ser nulo", relatorio, Matchers.notNullValue())
        MatcherAssert.assertThat(
            "O primeiro item deve ser uma instância de TopicoPorCategoriaDto",
            relatorio.first(),
            Matchers.instanceOf(TopicoPorCategoriaDto::class.java)
        )


    }
    @Test
    fun `deve listar topico pelo nome do curso`() {
        topicoRepository.save(topicos)
        topicoRepository.findByCursoNome(topicos.curso.nome, PageRequest.of(0,1))
        MatcherAssert.assertThat("O relatório não deve ser nulo", topicos, Matchers.notNullValue())

    }

}