data class Pedido(
    val id: Int,
    val status: String
)

interface PedidoStrategy {
    fun processar(pedido: Pedido)
}

class NovoPedidoStrategy : PedidoStrategy {
    override fun processar(pedido: Pedido) {
        println("Pedido em processamento: ${pedido.id}")
        Thread.sleep(2000)
    }
}

class PedidoProcessadoStrategy : PedidoStrategy {
    override fun processar(pedido: Pedido) {
        println("Pedido já processado: ${pedido.id}")
    }
}

class PedidoDesconhecidoStrategy : PedidoStrategy {
    override fun processar(pedido: Pedido) {
        println("Status desconhecido do pedido: ${pedido.id}")
    }
}

class PedidoProcessor {

    private val strategies = mapOf(
        "novo" to NovoPedidoStrategy(),
        "processado" to PedidoProcessadoStrategy()
    )

    fun processarPedido(pedido: Pedido) {
        val strategy = strategies[pedido.status]
            ?: PedidoDesconhecidoStrategy()

        strategy.processar(pedido)
    }
}

fun main() {
    val processor = PedidoProcessor()

    val pedido1 = Pedido(1, "novo")
    val pedido2 = Pedido(2, "processado")
    val pedido3 = Pedido(3, "cancelado")

    processor.processarPedido(pedido1)
    processor.processarPedido(pedido2)
    processor.processarPedido(pedido3)
}
