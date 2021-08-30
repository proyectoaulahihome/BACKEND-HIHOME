
package Models;

/**
 *
 * @author Faroukmn_97
 */
public class Notificationsmodel {
    
    private String notification_id = "-2";
    private String data_id = "-2";
    private String title = "-2";
    private String message = "-2";
    
    

    public String getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(String notification_id) {
        this.notification_id = notification_id;
    }

    public String getData_id() {
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
  
}
