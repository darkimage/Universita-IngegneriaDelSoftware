package com.lucafaggion
//import org.springframework.context.MessageSource
import org.springframework.beans.factory.annotation.Autowired

class ControllerException extends RuntimeException {
    public String code
    public String additionalMessage
    public ArrayList args

    //MessageSource messageSource

    def getExceptionMessage(){
        return [code:code,args:args];
    }

    def getAdditionalMessage(){
        return additional_message 
    }
}