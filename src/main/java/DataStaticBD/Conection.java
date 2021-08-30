/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStaticBD;

import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * In this class, the necessary methods for connecting, obtaining and sending data to the database are carried out.
 */
public class Conection {
 //  String user = "pdckzidnkeycgc";
  //  String password = "0ae4bc49a06ffe1aba28d9845a6ba9c3fcf72766cc4a88fd8f2c1411a92439ef";
    String user = "appdist";
    String password = "@Aplicaciones@Distribuidas@2021";
    java.sql.Connection conex;
    DefaultTableModel dataModel;
    ResultSet result;
    ResultSetMetaData rsmd;
    java.sql.Statement st;

    public Conection() {

    }

    /** Method for opening connection
     * @return Return a Boolean.
     */
    public boolean openConecction() {
        try {
            Class.forName("org.postgresql.Driver");
           // conex = DriverManager.getConnection("jdbc:postgresql://ec2-35-171-250-21.compute-1.amazonaws.com:5432/dannirq814voba", user, password);
           conex = DriverManager.getConnection("jdbc:postgresql://190.15.134.7:8080/SmartHomeHiHome", user, password);
        } catch (Exception exc) {
            System.out.println("No connection");
            return false;
        }
        return true;
    }

    /** This method closes the connection
     * @return Return a Boolean.
     */
    public boolean closeConnection() {
        try {
            st.close();
            conex.close();
        } catch (Exception exc) {
            System.out.println("Error close connection:" + exc.getMessage());
            return false;
        }
        return true;
    }

    /** This method closes the ResultSet
     * @return Return a Boolean.
     */
    public boolean closeResulSet() {
        try {
            result.close();
        } catch (SQLException ex) {
            System.out.println("error in close resulset:" + ex.getMessage());
            return false;
        }
        return true;
    }

    /** Receives a query and saves it in a table
     * @param sentecy This String variable contains the query.
     * @return Returns a table with the data loaded from the query
     */
    public DefaultTableModel returnRecord(String sentecy) {
        if (openConecction()) {
            try {
                st = conex.createStatement();
                result = st.executeQuery(sentecy);
                dataModel = new DefaultTableModel();
                rsmd = result.getMetaData();
                int n = rsmd.getColumnCount();
                for (int i = 1; i <= n; i++) {
                    dataModel.addColumn(rsmd.getColumnName(i));
                }
                String[] row = new String[n];
                while (result.next()) {
                    for (int i = 0; i < n; i++) {
                        row[i] = (result.getString(rsmd.getColumnName(i + 1)) == null) ? "" : result.getString(rsmd.getColumnName(i + 1));
                    }
                    dataModel.addRow(row);
                }
            } catch (Exception exc) {
                System.out.println("Error return Record:" + exc.getMessage());
                dataModel = null;
            } finally {
                if (result != null) {
                    closeResulSet();
                }
            };
            closeConnection();
        }
        return dataModel;
    }
    
    /** This method receives a query from a function.
     * @param sentecy This String variable, contains a query of a function.
     * @return Return a Boolean.
     */
    public boolean modifyBD(String sentecy) {
        if (openConecction()) {
            try {
                st = conex.createStatement();
                st.execute(sentecy);
            } catch (Exception exc) {
                System.out.println("Error ModifyBD:" + exc.getMessage());
                return false;
            }
            closeConnection();
            return true;
        } else {
            return false;
        }
    }
   /** Method to run an update on the database.
     * @param sentecy This String variable, contains a query of a function.
     * @return an integer, amount of updates.
     */
    public int updateDB(String sentecy) {
        int counts = 0;
        if (openConecction()) {
            try {
                st = conex.createStatement();
                counts = st.executeUpdate(sentecy);
            } catch (Exception exc) {
                System.out.println("Error UpdateBD:" + exc.getMessage());
                counts = 0;
            }
            closeConnection();
        } else {
            counts = 0;
        }
        return counts;
    }
   /** Execute any sentence in the database.
     * @param sentecy this variable contains the sentence that will be executed in the database
     * @return the value obtained when the sentence is executed in the database.
     */
    public String fillString(String sentecy) {
        String a = "";
        if (openConecction()) {
            try {
                st = conex.createStatement();
                result = st.executeQuery(sentecy);
                while (result.next()) {
                    a = result.getString(1);
                }

            } catch (Exception exc) {
                System.out.println("Error fill string:" + exc.getMessage());
                return "";
            } finally {
                if (result != null) {
                    closeResulSet();
                }
            };
            closeConnection();
        }
        return a;
    }
   /** Get the following ID
     * @param sentecy This String variable, contains a query of a function.
     * @return a string, with the following identifier.
     */
    public String getNextID(String sentecy) {
        String a = "-1";
        if (openConecction()) {
            try {
                st = conex.createStatement();
                result = st.executeQuery(sentecy);
                while (result.next()) {
                    a = result.getString(1);
                }
                int numer = 1;
                try {
                    numer = Integer.parseInt(a) + 1;
                } catch (NumberFormatException e) {
                    numer = 1;
                }
                a = numer + "";

            } catch (Exception exc) {
                System.out.println("No next id:" + exc.getMessage());
                a = "1";
            } finally {
                if (result != null) {
                    closeResulSet();
                }
            };
            closeConnection();
        }
        return a;
    }
    /** Obtain data and store it in a json
     * @param sentency This String variable, contains a query of a function.
     * @return a string, containing json.
     */
    public String getRecordsInJson(String sentency)
    {
        String resul = "[";
        DefaultTableModel table = returnRecord(sentency);
        if (table!=null)
        {
            int columCount = table.getColumnCount();
            for (int row = 0; row < table.getRowCount(); row++) {
                String line = "";
                for (int colum = 0; colum < columCount; colum++) {
                    line+="\""+table.getColumnName(colum).trim()+"\":\""+table.getValueAt(row, colum).toString().trim()+"\"";
                    if(colum<columCount-1)
                    {
                        line+=",";
                    }
                }
                if(line.length()>0)
                {
                    resul+="{"+line+"}";
                    if(row<table.getRowCount()-1)
                    {
                        resul+=",";
                    }
                }
            }
            resul += "]";
        }
        else
        {
            resul="[]";
        }
        return resul;
    }
    /** Test the connection to the database.
     * @return a Boolean
     */
    public boolean testConection() {
        boolean test = openConecction();
        if (test) {
            try {
                conex.close();
            } catch (SQLException ex) {
                System.out.println("error test conection:" + ex.getMessage());
            }
        }
        System.out.println("test:" + test);
        return test;
    }

    public Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
