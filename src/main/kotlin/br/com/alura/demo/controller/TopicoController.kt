package br.com.alura.demo.controller

import br.com.alura.demo.dto.NovoTopicoForm
import br.com.alura.demo.dto.TopicoView
import br.com.alura.demo.dto.UpdateTopicoForm
import br.com.alura.demo.service.TopicoService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): List<TopicoView> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorID(@PathVariable id: Long): TopicoView {
        return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid form: NovoTopicoForm): String {
        service.cadastrar(form)
        return "Cadastro Realizado"
    }

    @PutMapping
    fun atulaizar(@RequestBody @Valid form: UpdateTopicoForm): String {
        service.update(form)
        return "Cadastro Atualziado"
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }


}