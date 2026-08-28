package auth

import erro

var senha = 0
var nome = ""

fun pegarNome() {
    println("Olá! Digite o seu nome: ")
    nome = readln()
}

fun criarSenha() {
    println("Parece que você é novo por aqui $nome")
    while (true) {
        println("Criar senha (apenas números): ")
        senha = readln().toIntOrNull() ?: continue
        break
    }

    while (true) {
        println("Confirme a sua senha: ")
        val confirm = readln().toIntOrNull()

        if (confirm == senha) break
        println("Senhas não coincidem ou inválidas!")
    }

    println("Senha criada com sucesso!")
}

fun verificarSenha(acao: () -> Unit) {
    println("Digite a sua senha: ")
    val input = readln().toIntOrNull()
    if(input == senha){
        acao()
    } else {
        erro(403)
        verificarSenha(acao)
    }
}

fun existeSenha(): Boolean {
    return senha == 0
}

fun existeNome(): Boolean {
    return nome == ""
}