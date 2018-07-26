open class propriedade(val numero: Int, val precoVenda: Int, val precoAluguel: Int, var dono: jogador ?= null){
    override fun toString(): String {
        return "Casa $numero - jogador ${dono?.nome}"
    }
}

fun tabuleiro(casa: ArrayList<propriedade>){
    casa.add(propriedade(0,0,0))
    casa.add(propriedade(1, 60, 20))
    casa.add(propriedade(2, 80, 20))
    casa.add(propriedade(3, 100, 20))
    casa.add(propriedade(4, 120, 30))
    casa.add(propriedade(5, 140, 30))
    casa.add(propriedade(6, 160, 30))
    casa.add(propriedade(7, 180, 45))
    casa.add(propriedade(8, 200, 48))
    casa.add(propriedade(9, 220, 51))
    casa.add(propriedade(10, 240, 53))
    casa.add(propriedade(11, 260, 56))
    casa.add(propriedade(12, 280, 58))
    casa.add(propriedade(13, 300, 60))
    casa.add(propriedade(14, 320, 62))
    casa.add(propriedade(15, 340, 62))
    casa.add(propriedade(16, 360, 62))
    casa.add(propriedade(17, 380, 62))
    casa.add(propriedade(18, 400, 62))
    casa.add(propriedade(19, 420, 62))
    casa.add(propriedade(20, 440, 62))
}