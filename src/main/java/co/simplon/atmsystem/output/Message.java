package co.simplon.atmsystem.output;

public class Message {
    private String message;

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void returnMessage(String message) {
        System.out.println(message);
    }
}
