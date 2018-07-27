import java.security.SecureRandom

//Funções extra
fun movimento(a: Int, b: Int): Int = if((a+b)>20) (a+b)-20 else a+b

open class jogador(val nome: String, var qtdDinheiro: Int = 300, var posicao: Int = 0, var ativo: Boolean = true, var vitorias: Int = 0, var perdeu: Boolean = false) {
    open fun pagarAluguel(locatario: jogador, valorAluguel: Int, dono: jogador?){
        locatario.qtdDinheiro = locatario.qtdDinheiro - valorAluguel
        if (dono != null) {dono.qtdDinheiro = dono.qtdDinheiro + valorAluguel}
        if (dono != null) {dono.qtdDinheiro = dono.qtdDinheiro}
    }

    override fun toString(): String {
        return nome
    }

    //Se um usuário perder se torna inativo
    //Todas as suas propriedades passam a ficar sem dono
    //Acrescenta um na lista de perdedores
    open fun statusJogador(status: jogador, Propridade: List<propriedade>){
        status.ativo = !status.ativo
        Propridade.forEach{ it.dono = null }
    }

    //Todos jogadoree executaram a função abaixo toda rodada
    open fun jogada(casa: List<propriedade>, gerador: Int){
        if(ativo) {
            posicao = movimento(posicao, gerador)
            if (casa[posicao].dono == null && casa[posicao].precoVenda <= qtdDinheiro) {
                //Dependendo do tipo de jogador a sua forma especifica de comprar uma residencia é acionada
                if(this is jogadorExigente){this.setDeCompa(casa)}
                else if(this is jogadorAleatorio){setDeCompa(casa)}
                else if(this is jogadorCauteloso){setDeCompa(casa)}
                else if(this is jogadorImpulsivo){setDeCompa(casa)}
            } else if (casa[this.posicao].dono != null){
                pagarAluguel(this, casa[posicao].precoAluguel, casa[posicao].dono)
            }
            //Caso o jogador perder
            if (qtdDinheiro < 0) {statusJogador(this, casa.filter { it.dono == this })}
        }
    }
}

class jogadorImpulsivo(nome: String) : jogador(nome){
    fun setDeCompa(casa: List<propriedade>){modoDeComprar(casa[posicao].precoVenda,casa[posicao])    }
    //Impulsivo compra em qualquer casa livre que passar
    fun modoDeComprar(preco: Int, dono: propriedade){
        qtdDinheiro = qtdDinheiro - preco
        dono.dono = this
    }
}

class jogadorExigente(nome: String) : jogador(nome){
    fun setDeCompa(casa: List<propriedade>){modoDeComprar(casa[posicao].precoVenda, casa[posicao].precoAluguel, casa[posicao])}
    //Exigente só compra se o aluguel for maior que 50
    fun modoDeComprar(preco: Int, aluguel: Int, dono: propriedade){
        if(aluguel>50){
            qtdDinheiro = qtdDinheiro - preco
            dono.dono = this
        }
    }
}

class jogadorCauteloso(nome: String) : jogador(nome){
    fun setDeCompa(casa: List<propriedade>){modoDeComprar(casa[posicao].precoVenda, casa[posicao] )}
    //Cauteloso só o vlaor compra remanescente após a compra foir maior que 80
    fun modoDeComprar(preco: Int, dono: propriedade){
        if((qtdDinheiro-preco)>=80){
            qtdDinheiro = qtdDinheiro - preco
            dono.dono = this
        }
    }
}

class jogadorAleatorio(nome: String) : jogador(nome){
    val jogaAleatorio = SecureRandom()
    fun setDeCompa(casa: List<propriedade>){modoDeComprar(casa[posicao].precoVenda,casa[posicao])}
    //Aleatório comra uma vez sim e outra vez não em cada casa livre que passar
    fun modoDeComprar(preco: Int, dono: propriedade){
        if(jogaAleatorio.nextBoolean()) {
            qtdDinheiro = qtdDinheiro - preco
            dono.dono = this
        }
    }
}