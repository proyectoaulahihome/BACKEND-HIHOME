
package DAO;

import DataStaticBD.Conection;
import Models.Devicemodel;
import Models.Usermodel;

/**
 *
 * @author Faroukmn_97
 */
public class DeviceDAO {
   Conection con;
   String sentence; 
   
   public DeviceDAO(){
      con = new Conection();
   }
   
  public String selectDevice(){
        sentence = "select * from tbldevice";
      String json = con.getRecordsInJson(sentence);
      return json;
  }  
  
   public boolean comprobeUniqueDevice(Devicemodel devicemodel) {
        String sentency = String.format("select * from tbldevice where device_id='%s';", devicemodel.getDevice_id());
        return (((con.returnRecord(sentency)).getRowCount() <= 0));
    }
  
  public boolean insertDevice(Devicemodel devicemodel){
        String structure = String.format(
            "<device>"
                + "<namedevice>"+devicemodel.getNamedevice()+"</namedevice>"
                + "<state>"+devicemodel.getState()+"</state>"
               + "</device>");
        
        String sentency = "Select * from insertdevice('"+structure+"')";
        System.out.println(structure);
        return con.modifyBD(sentency);
    }
}
