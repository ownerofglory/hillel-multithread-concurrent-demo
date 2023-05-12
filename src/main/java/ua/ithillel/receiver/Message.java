package ua.ithillel.receiver;

import java.time.LocalDateTime;

public class Message {
    private String data;
    private LocalDateTime time = LocalDateTime.now();

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "data='" + data + '\'' +
                ", time=" + time +
                '}';
    }
}
