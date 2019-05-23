# Objetivo

Implementar um algoritmo para o [n-queens problem](https://en.wikipedia.org/wiki/Eight_queens_puzzle), tendo como objetivo buscar uma solução válida  para um determinando valor de n e realizar rotinas de teste para mensurar a quantidade de iteração necessárias para encontrar tal solução. 

# Implementação

Foi utilizado um algorítmo de busca local de conflito minimo.
![](https://raw.githubusercontent.com/BeholderDEV/discretequeensproblem/master/doc/program.PNG)

# Coleta de Dados

Os dados foram coletados a partir de uma rotina que gera 10 teste para cada tamanho de tabuleiro, e tabuleiros que variam de 5 a 500 colunas.

# Resultados

Comparando os dados podemos concluir que 

Disperção por Iterações

![](https://raw.githubusercontent.com/BeholderDEV/discretequeensproblem/master/doc/dispercao_iteracoes.PNG)

Dispersão por tempo
![](https://raw.githubusercontent.com/BeholderDEV/discretequeensproblem/master/doc/dispercao_tempo.PNG)

Regressão
```math
y = n ^ 1.740
```
![](https://raw.githubusercontent.com/BeholderDEV/discretequeensproblem/master/doc/dispercao_itaracao_regressao.PNG)

![](https://raw.githubusercontent.com/BeholderDEV/discretequeensproblem/master/doc/dispercao_itaracao_regressao_log.PNG)




