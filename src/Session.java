package src;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Session implements Serializable{
    private long sessionID;
    private int accNum;
    private LocalDateTime timeIssued;
    private LocalDateTime validUntil;

    public Session(long sessionID, int accNum, LocalDateTime timeIssued){
        this.sessionID = sessionID;
        this.accNum = accNum;
        this.timeIssued = timeIssued;
        this.validUntil = timeIssued.plusMinutes(5);
    }

    public long getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public int getAccNum() {
        return this.accNum;
    }

    public void setAccNum(int accNum) {
        this.accNum = accNum;
    }

    public LocalDateTime getTimeIssued() {
        return this.timeIssued;
    }

    public void setTimeIssued(LocalDateTime timeIssued) {
        this.timeIssued = timeIssued;
    }

    public LocalDateTime getValidUntil() {
        return this.validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
    
    
}
