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
        return "${this.nome}"
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
        if(this.ativo) {
            this.posicao = movimento(this.posicao, gerador)
            if (casa[this.posicao].dono == null && casa[this.posicao].precoVenda <= this.qtdDinheiro) {
                //Dependendo do tipo de jogador a sua forma especifica de comprar uma residencia é acionada
                if(this is jogadorExigente){this.setDeCompa(casa)}
                else if(this is jogadorAleatorio){this.setDeCompa(casa)}
                else if(this is jogadorCauteloso){this.setDeCompa(casa)}
                else if(this is jogadorImpulsivo){this.setDeCompa(casa)}
            } else if (casa[this.posicao].dono != null){
                this.pagarAluguel(this, casa[this.posicao].precoAluguel, casa[this.posicao].dono)
            }
            //Caso o jogador perder
            if (this.qtdDinheiro < 0) {this.statusJogador(this, casa.filter { it.dono == this })}
        }
    }
}

class jogadorImpulsivo(nome: String) : jogador(nome){
    fun setDeCompa(casa: List<propriedade>){this.modoDeComprar(casa[this.posicao].precoVenda,casa[this.posicao])    }
    //Impulsivo compra em qualquer casa livre que passar
    fun modoDeComprar(preco: Int, dono: propriedade){
        this.qtdDinheiro = this.qtdDinheiro - preco
        dono.dono = this
    }
}

class jogadorExigente(nome: String) : jogador(nome){
    fun setDeCompa(casa: List<propriedade>){this.modoDeComprar(casa[this.posicao].precoVenda, casa[this.posicao].precoAluguel, casa[this.posicao])}
    //Exigente só compra se o aluguel for maior que 50
    fun modoDeComprar(preco: Int, aluguel: Int, dono: propriedade){
        if(aluguel>50){
            this.qtdDinheiro = this.qtdDinheiro - preco
            dono.dono = this
        }
    }
}

class jogadorCauteloso(nome: String) : jogador(nome){
    fun setDeCompa(casa: List<propriedade>){this.modoDeComprar(casa[this.posicao].precoVenda, casa[this.posicao] )}
    //Cauteloso só o vlaor compra remanescente após a compra foir maior que 80
    fun modoDeComprar(preco: Int, dono: propriedade){
        if((this.qtdDinheiro-preco)>=80){
            this.qtdDinheiro = this.qtdDinheiro - preco
            dono.dono = this
        }
    }
}

class jogadorAleatorio(nome: String) : jogador(nome){
    val jogaAleatorio = SecureRandom()
    fun setDeCompa(casa: List<propriedade>){this.modoDeComprar(casa[this.posicao].precoVenda,casa[this.posicao])}
    //Aleatório comra uma vez sim e outra vez não em cada casa livre que passar
    fun modoDeComprar(preco: Int, dono: propriedade){
        if(jogaAleatorio.nextBoolean()) {
            this.qtdDinheiro = this.qtdDinheiro - preco
            dono.dono = this
        }
    }
}