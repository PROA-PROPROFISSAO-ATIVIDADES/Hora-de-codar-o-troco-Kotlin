package types

data class Extrato(
    val cliente: String,
    val saldo: Double,
    val tipo: String,
    val valorOperacao: Double,
    val destinatario: Int? = null
)