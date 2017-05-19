dados = read.csv("C:/Users/Alisson/Documents/Git/discretequeensproblem/Experimentos Complexidade/dados.csv",header=T)

plot(log(dados$Iteracoes)~dados$N)
fit <- lm(log(dados$Iteracoes)~dados$N)
summary(fit)



regressao = lm(log(dados$Iteracoes)~dados$N)

plot()