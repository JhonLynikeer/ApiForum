package br.com.alura.demo.model

object UsuarioTest {

    fun build() = Usuario(
        id = 1,
        nome = "ana",
        email = "ana@gmail.com",
        password = "123456789",
        role = listOf(
            Role(
                id = 1,
                nome = "LEITURA"
            )
        )
    )



}
