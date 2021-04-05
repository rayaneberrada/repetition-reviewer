package fr.rayane.repetitionreviewer.exception;

public class QualityOutsideOfBondsException extends Exception{

    public QualityOutsideOfBondsException(int qualityValue) {
        super("Quality should be a number between 0 and 5 included. Instead it was: " + qualityValue);
    }
}
