dados = read.csv("C:/Users/Alisson/Documents/Git/discretequeensproblem/Experimentos Complexidade/dados.csv",header=T)

regressao = lm(log(dados$iteracoes)~log(dados$N))