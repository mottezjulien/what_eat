package what.eat.generic.facade;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflitFacadeException extends Exception {

    public ConflitFacadeException(String message) {
        super(message);
    }

}
