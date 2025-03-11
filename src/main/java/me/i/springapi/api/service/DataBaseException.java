package me.i.springapi.api.service;

public class DataBaseException extends RuntimeException {
    public DataBaseException(){
        super();
    }

    public DataBaseException(String message){
        super(message);
    }
}
