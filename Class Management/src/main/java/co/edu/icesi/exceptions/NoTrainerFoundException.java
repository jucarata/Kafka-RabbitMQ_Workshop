package co.edu.icesi.exceptions;

public class NoTrainerFoundException extends Exception{
    public NoTrainerFoundException(){
        super("No se ha encontrado registro del entrenador deseado");
    }
}