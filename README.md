# Desafio_Kotlin


Análise estatística de vitórias baseada num jogo similar ao Banco Imobiliário

 

1. Sobre o Desafio
 Dado um tabuleiro com 20 propriedades em sequência (posições) e quatro jogadores, calcular o jogador que mais vence, sendo que cada jogador possui um perfil distinto de comprador de imóvel e cada posição possui um imóvel distinto de todos os outros com características de valor de aluguel e valor de venda. 
 O programa não necessita de dados de INPUT, ele apenas uma mensagem de OUTPUT a cada execução.
 
 

2.Regras de Compra
 
Para todo imóvel livre que os jogadores passarem e o valor de compra for menor ou igual a quantia em dinheiro que possuem é feita a seguinte análise para efetuar a compra:
  
	1 - Aleatório: tem 50% de chance de comprar um imóvel livre pelo qual passar.
  
	2 - Cauteloso: para todo imóvel livre que passar é efetuado a compra se a quantia de dinheiro pós compra for maior ou igual a 80 "coins".
  
	3 - Exigente: para todo imóvel livre que passar é efetuado a compra se o valor de aluguel for maior que 50 "coins".
  
	4 - Impulsivo: por todo imóvel livre que passar é efetuado a compra.
  	
Obs.: Caso o imóvel estiver livre porém o requisito para compra de um determinado jogador não for atendido de acordo com o seu perfil, o imóvel não é comprado. 

 

3. Valores iniciais e outras regras
  
Para cada partida todos os jogadores começam com 300 "coins" e na posição zero.
  
Toda a regra de movimentação é feita através de dado um dado não viciado de 6 faces.
  
Não é possível fazer melhorias nos imóveis de forma que altere as características de valor de aluguel e venda.
  
Todos imóveis começam com o status de imóvel sem dono, cada propriedade pode ter apenas um dono ou estar sem dono, e toda vez que um jogador perde todas as propriedades que estavam em seu nome passam a ser consideradas sem dono novamente. 
Cada imóvel pode ser apenas comprado e alugado, não pode ser vendido.
  
Quando um jogador passa por uma propriedade que possui dono ele deve pagar o aluguel ao dono da propriedade de acordo com o valor estipulado no escopo.
  
Um jogador perde se o seu saldo monetário ficar negativo, uma partida termina quando houver três perdedores ou quando uma partida ultrapassar mil rodadas.
  
A cada rodada cada jogador recebe 10 "coins".
  
Obs.: O programa gera a ordem dos jogadores automaticamente a cada partida.
  
 

4. Sobre o algoritmo
  
O algoritmo foi desenvolvido em Kotlin, para executá-lo utilizando o IntelliJ IDEA:
  
	1. Faça o clone dos arquivos.
  
	2. Descompacte o arquivo .zip.
  
	3. Cole a pasta "Desafio_Kotlin-master" na pasta de projetos do IntelliJ IDEA. Example C:\Users\~\IdeaProjects
  
	4. Execute o arquivo "Main.kt" em C:\Users\~\IdeaProjects\Desafio\src via Intellij IDEA.
