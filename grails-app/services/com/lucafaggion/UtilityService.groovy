package com.lucafaggion

import grails.gorm.transactions.Transactional

@Transactional
class UtilityService {
    private Random random = new Random()
    
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
