package DAO;

import DataStaticBD.Conection;
import Models.Datamodel;

/**
 *
 * @author Faroukmn_97
 */
public class DataDAO {

    Conection con;
    String sentence;

    public DataDAO() {
        con = new Conection();
    }

    public String selectData() {
        sentence = "select mqgas,mlx,mqhumo from tbldata order by data_id DESC LIMIT 1";
        String json = con.getRecordsInJson(sentence);
        return json;
    }

    public String selectDataAll() {
        sentence = "select * from tbldata order by data_id DESC LIMIT 1";
        String json = con.getRecordsInJson(sentence);
        return json;
    }

    public String selectDatabydevice(String id_device) {
        sentence = "select mqgas,mlx,mqhumo from tbldata inner join tbldevice on tbldevice.device_id = tbldata.device_id \n"
                + "where tbldevice.device_id = '"+id_device+"'\n"
                + "order by data_id DESC LIMIT 1";
        String json = con.getRecordsInJson(sentence);
        return json;
    }
    
    
    public String selectDatabydeviceAll(String id_device) {
        sentence = "select * from tbldata inner join tbldevice on tbldevice.device_id = tbldata.device_id \n"
                + "where tbldevice.device_id = '"+id_device+"'\n"
                + "order by data_id DESC LIMIT 1";
        String json = con.getRecordsInJson(sentence);
        return json;
    }

    public boolean insertData(Datamodel datamodel) {
        String structure = String.format(
                "<data>"
                + "<device_id>" + datamodel.getDevice_id() + "</device_id>"
                + "<mqgas>" + datamodel.getMqgas() + "</mqgas>"
                + "<mlx>" + datamodel.getMlx() + "</mlx>"
                + "<mqhumo>" + datamodel.getMqhumo() + "</mqhumo>"
                + "<u_detected>" + datamodel.getU_detected() + "</u_detected>"
                + "</data>");

        String sentency = "Select * from insertdata('" + structure + "')";
        //System.out.println(structure);
        return con.modifyBD(sentency);
    }

}
