package br.com.alura.demo.mapper

import br.com.alura.demo.dto.TopicoView
import br.com.alura.demo.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: Mapper<Topico, TopicoView> {
    override fun map(t: Topico): TopicoView {
       return TopicoView(
               id = t.id,
               titulo = t.titulo,
               mensagem = t.mensagem,
               dataCriacao = t.dataCriacao,
               status = t.status
       )
    }
}