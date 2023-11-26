package cibertec.edu.pe.DSWII_T3_MarcoBegazo.Exception;

public class MaxUploadSizeExceedException extends RuntimeException {
    public MaxUploadSizeExceedException (String message){
        super(message);
    }
}