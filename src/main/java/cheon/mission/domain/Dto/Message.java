package cheon.mission.domain.Dto;

import lombok.Data;

@Data
public class Message {

    private String message = "";
    private String href = "";

    public Message(String message, String href) {
        this.message = message;
        this.href = href;
    }
}
