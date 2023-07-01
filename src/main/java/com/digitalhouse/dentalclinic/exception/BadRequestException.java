package com.digitalhouse.dentalclinic.exception;

public class BadRequestException extends Exception{

    public BadRequestException(String mensaje){

        super(mensaje);
    }

}
