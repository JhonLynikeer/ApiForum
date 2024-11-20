package br.com.alura.demo.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notificar(emailAutor: String){

        val message = SimpleMailMessage()

        message.subject = "[Alura] Resposta Recebida"
        message.text = "Ola, seu topico foi respondido. Vamos la conferir?"
        message.setTo(emailAutor)

        javaMailSender.send(message)

    }

}