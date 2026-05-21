package ngocvt.local.ngocvt.resources;
import java.util.Map;
import java.util.HashMap;

public class ErrorResource {
    private String message;
    private Map<String, String> errors;

    public ErrorResource(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(Map<String, String> errors) {
        this.errors = errors;
    }
}
