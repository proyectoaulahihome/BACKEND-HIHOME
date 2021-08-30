
package DAO;

import DataStaticBD.Conection;
import Models.Notificationsmodel;

/**
 *
 * @author Faroukmn_97
 */
public class NotificationDAO {
    Conection con;
    String sentence;
    
    public NotificationDAO(){
       con = new Conection();
    }
    
    public String selectNotification() {
        sentence = "select * from tblnotifications";
        String json = con.getRecordsInJson(sentence);
        return json;
    }
    
     public boolean insertNotification(Notificationsmodel notificationsmodel) {
        String structure = String.format(
                "<notification>"
                + "<data_id>" + notificationsmodel.getData_id() + "</data_id>"
                + "<title>" + notificationsmodel.getTitle() + "</title>"
                + "<message>" + notificationsmodel.getMessage() + "</message>"
                + "</notification>");

        String sentency = "Select * from insertnotification('" + structure + "')";
        System.out.println(structure);
        return con.modifyBD(sentency);
    }
    
}
