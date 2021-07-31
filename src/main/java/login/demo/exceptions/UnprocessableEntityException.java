package login.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/* This exception is thrown when data validation fails.
* @HttpStatus 422
*/
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends Exception {
    public UnprocessableEntityException(String msg) {
        super(msg);
    }

}
