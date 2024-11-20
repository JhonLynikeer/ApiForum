package br.com.alura.demo.service

import br.com.alura.demo.model.Resposta
import br.com.alura.demo.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository,
    private val emailService: EmailService
) {

    fun salvar(resposta: Resposta) {
        respostaRepository.save(resposta)
        val emailAutor = resposta.topico.autor.email
        emailService.notificar(emailAutor)
    }

}