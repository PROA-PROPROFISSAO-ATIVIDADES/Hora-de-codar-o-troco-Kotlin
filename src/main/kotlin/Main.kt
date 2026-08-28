import auth.criarSenha
import auth.existeNome
import auth.existeSenha
import auth.pegarNome

fun main() {
    if (existeNome()) pegarNome()
    if (existeSenha()) criarSenha()
    caixa()
}