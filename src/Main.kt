import java.io.File
import java.io.PrintWriter
import java.lang.System.out
import java.security.SecureRandom
import java.util.*

fun contValorResidencia(casas: ArrayList<propriedade>, jogador: jogador): Int {
    var valor = 0
    casas.forEach {
        if(it.dono == jogador){valor+=it.precoVenda}
    }
    return valor
}
fun qtdDeResidencias(casas: ArrayList<propriedade>, jogador: jogador): Int{
    var valor = 0
    casas.forEach {
        if(it.dono == jogador){valor+=1}
    }
    return valor
}

fun main(args: Array<String>) {
    val casa = ArrayList<propriedade>()
    val gerador = SecureRandom()
    val Aleatorio = jogadorAleatorio("Aleatorio")
    val Exigente = jogadorExigente("Exigente")
    val Cauteloso = jogadorCauteloso("Cauteloso")
    val Impulsivo = jogadorImpulsivo("Impulsivo")
    val jogadores = arrayListOf<jogador>(Aleatorio,Cauteloso,Exigente,Impulsivo)
    var arquivo = PrintWriter("Arquivo_Analise.txt")
    tabuleiro(casa) //Declara as casas e suas features

    //variaveis do escopo externo
    var estouroMil = 0
    var contGeralExterno = 0
    val i = 1000

    var analiseTurnos = 0
    var maiorPartida = 0

    for(contGeralExterno in 1..i){

        //variaveis do escopo interno
        val vencedores = arrayListOf<jogador>()
        var contGeralInterno = 0
        var numeroPerdedores = 0
        Collections.shuffle(jogadores)
        arquivo.println("Inicio")
        while (contGeralInterno<=1000 && numeroPerdedores<3){
            //Jogada de casa tipo de jogador e sempre contando o número de perdedores
            jogadores.forEach {
                it.jogada(casa,gerador.nextInt(6) + 1)
                if(!it.ativo && !it.perdeu){
                    numeroPerdedores+=1
                    it.perdeu = !it.perdeu
                }
            }
            jogadores.forEach { it.qtdDinheiro += 10 }

//            println("Rodada ${contGeralInterno+1}")
//            println(casa.filter { it.dono != null })
//            println("Stat - Exigente: ${Exigente.ativo} - Cauteloso: ${Cauteloso.ativo} - Aleatório ${Aleatorio.ativo} - Impulsivo ${Impulsivo.ativo}")
//            println("Posi - Exigente: ${Exigente.posicao} - Cauteloso: ${Cauteloso.posicao} - Aleatório ${Aleatorio.posicao} - Impulsivo ${Impulsivo.posicao}")
//            println("Dinh - Exigente: ${Exigente.qtdDinheiro} - Cauteloso: ${Cauteloso.qtdDinheiro} - Aleatório ${Aleatorio.qtdDinheiro} - Impulsivo ${Impulsivo.qtdDinheiro}\n")
            arquivo.print("Partida: [${contGeralExterno}] - Rodada ${contGeralInterno+1}")
            jogadores.forEach { arquivo.print(" - Tipo: ${it.nome} e saldo: ${it.qtdDinheiro} e casas: ${qtdDeResidencias(casa,it)}") }
            arquivo.println("")
            contGeralInterno+=1
        }
        analiseTurnos+=contGeralInterno //Contador de qtd rodadas
        //Analise de maior numero de partidas
        if (maiorPartida == 0){ maiorPartida = contGeralInterno }
        if (contGeralInterno>maiorPartida){maiorPartida = contGeralInterno}

        //Analise se houve estouro de rodadas e quem é o vencedor
        if(contGeralInterno>1000){
            estouroMil+=1

            jogadores.forEach { if(it.ativo==true && !it.perdeu){ vencedores.add(it) } }
            vencedores.forEach { it.qtdDinheiro=it.qtdDinheiro + contValorResidencia(casa, it) }
            vencedores.sortByDescending { it.qtdDinheiro }
            vencedores[0].vitorias+=1
            arquivo.println("Vencedor: ${vencedores[0].nome}")
        }
        else{
            jogadores.forEach { if(it.ativo) {it.vitorias+=1;arquivo.println("Vencedor: ${it.nome}") }}
        }
        arquivo.println("Fim")
        //Reseta parametros das casa e dos jogadores mas nao o número de vitórias
        jogadores.forEach {
            it.ativo = true
            it.posicao = 0
            it.qtdDinheiro = 300
            it.perdeu = false
        }
        casa.forEach { it.dono = null }
    }
    //Analisa o mais vitorioso
    jogadores.sortBy { it.vitorias }

    println("Em $i partidas a média de duração de cada é de "+"%.4f".format(analiseTurnos/(i.toDouble()))+" rodadas.")
    println("Sendo que $estouroMil partida(s) terminou(aram) automaticamente por utrapassar(em) mil rodadas.")
    println("A maior partida durou $maiorPartida rodadas.")
    println("O perfil ${jogadores[3].nome.toString()} foi o mais vitorioso.")
    println("De $i partidas as vitórias se diviram da seguinte forma:")
    jogadores.forEach { println("-   O perfil ${it.nome} venceu ${it.vitorias} partida(s)- "+"%.4f".format(it.vitorias/(i.toDouble()) * 100)+"%.") }
    arquivo.close()
}