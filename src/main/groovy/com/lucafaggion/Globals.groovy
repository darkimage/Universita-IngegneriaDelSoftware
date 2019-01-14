package com.lucafaggion

class Globals {
    private Random random = new Random()
    Integer test = 1
    def getRandomNumber(Integer num){
        return random.nextInt(num);
    }
    def printClass(obj){
        println obj.properties
                .sort{it.key}
                .collect{it}
                .findAll{!['class', 'active'].contains(it.key)}
                .join('\n')
    }

}