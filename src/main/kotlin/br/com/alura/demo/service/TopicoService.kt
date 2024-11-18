package br.com.alura.demo.service

import br.com.alura.demo.dto.*
import br.com.alura.demo.exception.NotFoundExcepetion
import br.com.alura.demo.mapper.TopicoFormMapper
import br.com.alura.demo.mapper.TopicoViewMapper
import br.com.alura.demo.model.Topico
import br.com.alura.demo.repository.TopicoRepository
import br.com.alura.demo.repository.UsuarioRepository
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.stream.Collectors

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundExcepetion: String = "Topico nao encontrado",

) {


    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        val topicos = if (nomeCurso == null){
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map { t ->
            topicoViewMapper.map(t)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow { NotFoundExcepetion(notFoundExcepetion)}
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm) : TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }



    fun update(form: UpdateTopicoForm) : TopicoView{
        val topico = repository.findById(form.id)
            .orElseThrow { NotFoundExcepetion(notFoundExcepetion)}

        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        topico.dataAlteracao = LocalDate.now()
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }

    fun topicosNaoRespondidos(): List<Topico> {
        return repository.topicosNaoRespondidos()
    }


}