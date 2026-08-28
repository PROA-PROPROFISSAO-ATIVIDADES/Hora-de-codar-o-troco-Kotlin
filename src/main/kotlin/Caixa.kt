import BD.adicionarExtrato
import BD.listarExtrato
import auth.nome
import auth.verificarSenha
import types.Extrato
import kotlin.system.exitProcess

var saldo = 100.5
var nome = nome

fun caixa() {
    saudacao()
    escolhas()
}

fun saudacao(){
    println("Olá $nome, é um prazer ter você por aqui!")
}

fun escolhas() {
    while (true){
        println("""Escolha uma opção:
        1. - Ver saldo
        2. - Ver Extrato
        3. - Fazer saque
        4. - Fazer depósito
        5. - Transferência
        6. - Sair""".trimIndent())

        val escolha = readlnOrNull()?.toIntOrNull()

        when (escolha) {
            1 -> {
                verificarSenha({ verSaldo() })
            }
            2 -> {
                verificarSenha({ listar_extrato() })
            }
            3 -> {
                verificarSenha({ fazerSaque() })
            }
            4 -> {
                verificarSenha({ fazerDeposito()})
            }
            5 -> {
                verificarSenha({ fazerTransferencia() })
            }
            6 -> sair()
            else -> erro(12)
        }
    }
}

fun verSaldo() {
    println("Seu saldo atual é: $saldo")
}

fun fazerDeposito() {
    val tipo = "deposito"
    val valor = pegarValor(tipo)

    if(!valor.first) {
        erro(404)
    } else {
        saldo += valor.second
        verSaldo()
        adicionarExtrato(Extrato(nome, saldo, tipo, valor.second))
    }
}

fun fazerSaque() {
    val tipo = "saque"
    val valor = pegarValor(tipo)

    if(!valor.first || saldo < valor.second) {
        erro(401)
        if(!valor.first) fazerSaque()
    } else {
        saldo -= valor.second
        verSaldo()
        adicionarExtrato(Extrato(nome, saldo, tipo, valor.second))
    }
}

fun fazerTransferencia(){
    val tipo = "transferencia"
    val valor = pegarValor(tipo)

    if(!valor.first || saldo < valor.second) {
        erro(401)
        if(!valor.first) fazerTransferencia()
    } else {
        println("Digite o numero da conta do destinatario")
        val destinatario = readln().toIntOrNull() ?: return erro()
        saldo -= valor.second
        verSaldo()

        adicionarExtrato(Extrato(nome, saldo, tipo, valor.second, destinatario))
    }
}

fun pegarValor(tipo: String): Pair<Boolean, Double> {
    print("Qual o valor para o $tipo? ")
    val valor = readln().toDoubleOrNull()

    return if(valor == null || valor <= 0) Pair(false, 0.0) else Pair(true, valor)
}

fun listar_extrato(){
    listarExtrato();
}

fun erro(tipo: Int? = null) {

    when(tipo){
        12 -> println("Por favor, informe um número entre 1 a 6.")
        401 -> println("Operação não autorizada.")
        403 -> println("Senha invalida")
        404 -> println("Por favor, informe um valor válido.")
        else -> println("Ocorreu um erro!")
    }
}

fun sair() {
    print("Você deseja sair? (S/N)")
    val confirma = readln().uppercase()

    when (confirma) {
        "S" -> {
            println("Tchau $nome, volte sempre!")
            exitProcess(0)
        }
        "N" -> return
        else -> sair()
    }
}