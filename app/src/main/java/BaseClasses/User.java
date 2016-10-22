package BaseClasses;
import android.location.Location;
import android.support.v7.app.AlertDialog;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;

import java.util.Map;

/**
 * Created by marchest on 14.10.2016.
 */
public class User extends Person {
    AlertDialog alertDialog;
    List<ContactPerson> contactPersons;
    Map<ContactPerson,Message> msg; // bunu da map olarak yapmak lazım hangi kişiye hangi mesaj gidecek bu lazım
    Map<Date,Location> locationInfo;

    public User() {
        super();
        msg =new HashMap<>();
        contactPersons = new ArrayList<>();
        locationInfo =new HashMap<>();
    }


    public boolean addContact(ContactPerson cp){
        if (!contactPersons.contains(cp) && contactPersons.size()!=5){
            contactPersons.add(cp);
            return true;
        }
        else{
            alertDialog.setMessage("you can not add more than 5 contact");
            return false;
        }

    }

    public List<ContactPerson> getContactPersons() {
        return contactPersons;
    }

    //Belirli kişiye belirli mesajlar.
    // listi dolasacak adamı bulunca ona mesaj eklicek.
    public boolean addMessage(Message message,String telephone){
        for (ContactPerson c:contactPersons) {
            if (c.getPhoneNumber().equalsIgnoreCase(telephone)){
                msg.put(c,message);
            }
        }
     /*   if (msg.size()!=5){
            if (!msg.contains(message)){
                msg.add(message);
                return true;
            }
            else{
                alertDialog.setMessage("This message already exists");
                return  false;
            }
        }
        else{
            alertDialog.setMessage("you cant add more than 5 message");
                    return false;
        }*/
        return false;
    }

    /*public List<Message> getMsg() {
        return msg;
    }
*/

    public Map<Date, Location> getLocationInfo() {
        return locationInfo;
    }


}
