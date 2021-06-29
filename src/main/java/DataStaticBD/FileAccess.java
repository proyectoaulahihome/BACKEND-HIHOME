/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStaticBD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

/**
 */
public class FileAccess {

    public FileAccess() {
    }

    public String readFileText(String location) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String result = "";
        try {
            archivo = new File(location);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                result += linea;
            }
        } catch (Exception e) {
            result = "";
            System.out.println("Error in read File project");
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                System.out.println("Error in ModelProjectController.readFileText.fr.close()");
            }
        }
        return result;
    }

    public boolean writeFileText(String location, String structure) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(location);
            pw = new PrintWriter(fichero);
            pw.println(structure);
        } catch (Exception e) {
            System.out.println("Error in save File project");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.out.println("Error in ModelProjectController.writeFileText.fichero.close()");
            }
        }
        return true;
    }

    public boolean SaveImg(String base64, String rutaImagen) {
        File file = new File(rutaImagen);
        return writeOutputStream(base64, file);
    }

    private boolean writeOutputStream(String value, File outputStream) {
        String[] partes = value.split(",");
        try {
            byte[] imgBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(partes[1]);
            BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imgBytes));
            ImageIO.write(bufImg, "png", outputStream);
            return true;
        } catch (Exception e) {
            System.out.println("Error creating image: " + e.getMessage());
            return false;
        }
    }
}
