/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author m4tuu
 */
public class FileManager {
    public static String getDefaultFolder() {
        File defaultDirectory = new JFileChooser().getFileSystemView().getDefaultDirectory();
        File zProjectsFolder = new File(defaultDirectory, "zProjects");
        System.out.println("Default folder: " + zProjectsFolder.getPath());
        return zProjectsFolder.getPath();
    }
    
    public static boolean checkUserFolder() {
        File f = new File(getDefaultFolder());
        if(!f.exists()) {
            f.mkdir();
        }
        if(f.isDirectory() && f.canWrite()) {
            File tmp = new File(f, "tmp");
            if (!tmp.exists()) {
                tmp.mkdir();
            }
            return true;
        }
        return false;
    }
    
    public static File getPath(String[] paths) {
        File result = new File(getDefaultFolder());
        for(String str:paths){
            result = new File(result, str);
        }
        System.out.println("get folder: " + result.getPath());
        return result;
    }
    
    public static File getPathDirectory(String[] paths, boolean createIt) {
        File result = getPath(paths);
        if(!result.exists() && createIt){
            result.mkdirs();
        }
        return result;
    }
    
    public static File getPath(String path) {
        return new File(getDefaultFolder(), path);
    }
    
    public static boolean copyfile(String srFile, String dtFile){
        File f1 = new File(srFile);
        File f2 = new File(dtFile);
        return copyfile(f1, f2);
    }
    public static boolean copyfile(File f1, File f2){
        try{
            
            InputStream in = new FileInputStream(f1);
  
            //For Append the file.
            //  OutputStream out = new FileOutputStream(f2,true);

            //For Overwrite the file.
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
            return true;
        } catch(FileNotFoundException ex){
            System.out.println(ex.getMessage() + " in the specified directory.");
            
        } catch(IOException e){
            System.out.println(e.getMessage());  
        }
        return false;
    }

    public static File getTmpFolder() {
        return new File(getDefaultFolder(), "tmp");
    }
}
