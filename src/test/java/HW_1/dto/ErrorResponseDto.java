package hw_1.dto;

import java.util.Objects;

public class ErrorResponseDto {
    private int code;
    private String type;
    private String message;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponseDto that = (ErrorResponseDto) o;
        return code == that.code &&
                Objects.equals(type, that.type) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type, message);
    }

    @Override
    public String toString() {
        return "ErrorResponseDto{" +
                "code=" + code +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}