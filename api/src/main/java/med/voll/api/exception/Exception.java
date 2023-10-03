package med.voll.api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class Exception {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> error400(MethodArgumentNotValidException e){

        List<DatosError> errores  = e.getFieldErrors().stream().map(DatosError::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

 private record DatosError(String campo, String error){
        public DatosError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

 }
}
