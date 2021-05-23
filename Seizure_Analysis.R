library("MASS")
library("class")
library("glmnet")
library("ggplot2")
library("e1071")  #tune library, riadl svm
library("randomForest")

library("caret")
library("boot")
library("tidyverse")
library("dplyr")
library("rbenchmark")

getwd()
#setwd("F:\\Users\\Kenny\\Downloads\\f19\\9891\\project\\XOR_Arbiter_PUFs\\XOR_Arbiter_PUFs\\6xor_64bit")
#setwd("F:\\Users\\Kenny\\Downloads\\f19\\9891\\project\\dataset_diabetes")
setwd("F:\\Users\\Kenny\\Downloads\\f19\\9891\\project\\siezures")
getwd()

data = read.csv("data.csv")
data = na.omit(data)

# 1 = seizure
# 2,3,4,5, no seizure
data = data[,-1]

data$y[data$y != 1] = 0
data[,-179] = scale(data[,-179])
data = data[order(data$y), ]
table(data$y)

imbalance     =     TRUE   

L = 179

data[,L] = as.factor(data[,L])
colnames(data)[L] = 'y'

table(data$y)

n = nrow(data)
p = ncol(data)-1

errors = data.frame(matrix(ncol = 10, nrow = 100))

colnames(errors) =     c("Logistic Train", "Radial Train",
                         "Lasso Train", "Ridge Train", "Random Forest Train", 
                         "Logistic Test",  "Radial Test",
                         "Lasso Test", "Ridge Test", "Random Forest Test")

time = data.frame(matrix(ncol = 5, nrow = 100))

colnames(time) =     c("Logistic", "Radial",
                       "Lasso", "Ridge", "Random Forest")

#data = data[sample(n, n*.25),]
#n = nrow(data)

nsample = .5

modelWeights = ifelse(data$y == 0,
                      (1/table(data$y)[1]) * 0.5,
                      (1/table(data$y)[2]) * 0.5)

itr = 25
for(j in 1:2){
  if(j == 1){
    nsample=.5
  }
  if(j == 2){
    nsample=.9
  }
  
  #svm
  for(i in 1:10){
    
    subset         = sample(n, n*nsample)
    a = table(modelWeights)[1]
    b = table(modelWeights)[2]
    
    if(i == 1){
      prev= Sys.time()
      wt = c("0" = "inverse", "1" = "inverse")
      radial.tune = tune.svm(y~., data=data[subset ,], kernel ="radial", 
                             cost = 10^seq(-2,2,length.out = 5),
                             gamma = 10^seq(-2,2,length.out = 5),
                             class.weights = wt) 
      #plot(radial.tune, main = "SVM performance, Cost = 100, Gamma = 0.01, Time = 37.28 minutes")
      cur  = Sys.time()
      radial.tune.time = cur - prev
      
      print(radial.tune.time)
      
      print(radial.tune)
      
      coef(radial.tune)
    }
    
    prev= Sys.time()
    radial.fit = svm(y ~ .,data = data, kernel="radial", 
                     cost=radial.tune$best.model$cost, 
                     gamma = radial.tune$best.model$gamma,
                     scale=T, subset = subset)
    cur  = Sys.time()
    
    time$`Radial`[i] = cur-prev
    
    radial.train = predict(radial.fit ,     newdata =data[subset ,])
    radial.test  = predict(radial.fit ,     newdata =data[-subset ,])
    
    errors$`Radial Train`[i] = mean(radial.train != data[subset,L])
    errors$`Radial Test`[i]  = mean(radial.test  != data[-subset,L])
    
    print(paste("Radial", i, round(errors$`Radial Train`[i], 3), round(errors$`Radial Test`[i], 3), round(cur - prev, 2)))
  }
  
  oldtime = time
  olderror = errors
}




boxplot(errors[c(2,7)])

boxplot(errors[1:5], ylab = "Error Rates", main = "Train Error for 5 Classifcation Methods")
boxplot(errors[6:10], ylab = "Error Rates", main = "Test Error for 5 Classifcation Methods")

boxplot(time)




######
summary(lasso.cv$glmnet.fit$beta)
install.packages("plotmo")
library("plotmo")

par(mfrow=c(1,2))

plot_glmnet(lasso.cv$glmnet.fit, main = "Lasso")
plot_glmnet(ridge.cv$glmnet.fit, main = "Ridge")
#plot(y = lasso.cv$cvm, x = lasso.cv$lambda)
par(mfrow=c(1,2))

plot(lasso.cv)
title("Error rate vs. log(Lambda) - Lasso, time = 2.22 mins", line = 2)
plot(ridge.cv)
title("Error rate vs. log(Lambda) - Ridge, time = 2.18 mins", line = 2)

######


#plot(lasso.fit)

#plot(coef(lasso.fit))
#box(lasso.fit$beta)

