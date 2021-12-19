package what.eat.generic.facade;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundFacadeException extends Exception {

    public NotFoundFacadeException(String message) {
        super(message);
    }

}
