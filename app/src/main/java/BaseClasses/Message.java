package baseClasses;

import java.util.Date;

/**
 * Created by marchest on 14.10.2016.
 */
public class Message {

    public String messageTitle;
    public String messageText;
    public Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Message(String messageTitle, String messageText)
    {
        this.messageTitle = messageTitle;
        this.messageText = messageText;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
