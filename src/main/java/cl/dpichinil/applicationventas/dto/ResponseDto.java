package cl.dpichinil.applicationventas.dto;

public class ResponseDto {
    private int id;
    private String message;
    private Object data;

    public ResponseDto() {

    }
    public ResponseDto(int id, String message) {
        this.id = id;
        this.message = message;
    }
    public ResponseDto(int id, String message, Object data) {
        this.id = id;
        this.message = message;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
