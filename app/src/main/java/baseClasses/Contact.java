package baseClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marchest on 26.11.2016.
 */
public class Contact {
    private int contactId;
    private String contactName;
    private String contactSurname;
    private String Phone;
    private int userId;
    private List<Messages> userMessages;

    public Contact() {
        userMessages = new ArrayList<Messages>();
    }

    public List<Messages> getUserMessages() {
        return userMessages;
    }

    public Contact(String contactName, String contactSurname, String phone, int userId) {
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        Phone = phone;
        this.userId = userId;
    }

    public void setUserMessages(List<Messages> userMessages) {
        this.userMessages = userMessages;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactSurname() {
        return contactSurname;
    }

    public void setContactSurname(String contactSurname) {
        this.contactSurname = contactSurname;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
