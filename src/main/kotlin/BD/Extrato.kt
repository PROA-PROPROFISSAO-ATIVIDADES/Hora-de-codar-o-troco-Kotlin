package BD
import types.Extrato

var extrato = arrayListOf(
    Extrato("Jose", 150.0, "deposito", 150.0),
    Extrato("Jose", 120.0, "compra", 30.0),
    Extrato("Jose", 90.0, "saque", 30.0),
    Extrato("Jose", 60.0, "transferencia", 30.0, 123456),
    Extrato("Jose", 100.5, "deposito", 40.5)
)

fun adicionarExtrato(item: Extrato){
    extrato.add(item)
}

fun listarExtrato(){
    extrato.takeLast(10).forEach { item ->
        println(item)
    }
}