package br.com.alura.demo.controller

import br.com.alura.demo.dto.*
import br.com.alura.demo.model.Topico
import br.com.alura.demo.service.TopicoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder


//@RestController
//@RequestMapping("/register")
//class UserController(private val service: TopicoService) {
//
//    @PostMapping
//    @Transactional
//    @CacheEvict(value = ["register"], allEntries = true)
//    fun cadastrar(
//        @RequestBody @Valid form: NovoUsuarioForm,
//        uriBuilder: UriComponentsBuilder
//    ): ResponseEntity<TopicoView> {
//        val topicoView = service.cadastrar(form)
//        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
//        return ResponseEntity.created(uri).body(topicoView)
//    }
//
//
//
//}