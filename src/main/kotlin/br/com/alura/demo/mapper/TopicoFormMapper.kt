package br.com.alura.demo.mapper

import br.com.alura.demo.dto.NovoTopicoForm
import br.com.alura.demo.model.Topico
import br.com.alura.demo.service.CursoService
import br.com.alura.demo.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
        private val cursoService: CursoService,
        private val usuarioService: UsuarioService
): Mapper<NovoTopicoForm, Topico> {

    override fun map(t: NovoTopicoForm): Topico {
      return Topico(
                titulo = t.titulo,
                mensagem = t.mensagem,
                curso = cursoService.buscarPorId(t.idCurso),
                autor = usuarioService.buscarPorId(t.idAutor),
        )
    }

}
