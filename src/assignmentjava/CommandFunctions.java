package assignmentjava;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CommandFunctions {
    
    public static String pathInput() {  
      System.out.print("Input path: ");  
      Scanner u = new Scanner (System.in);  
      String input = u.nextLine();  
     return input;  
    } 
    
    
    public static void list(){
        System.out.println("Folder view options");
        File path=new File(pathInput());
        if(path.exists() && path.isDirectory()){
            String []strings=path.list();
            for(int i=0;i<strings.length;i++){
                System.out.println(strings[i]+" ");
            }
        }
         else{
             System.out.println("The selected path doesn't exist.");
           }
                    
    }
    
    
    public static void info(){
        System.out.println("Info:");
        File path=new File(pathInput());
        if(path.exists()){
        System.out.println("Name of the file is " + path.getName());
        System.out.println("Path of the file is " + path.getPath());
        System.out.println("Size of the file is " + path.length()+ " bytes");
        BasicFileAttributes attrs;
        try {
            attrs = Files.readAttributes(path.toPath(), BasicFileAttributes.class);
            FileTime time = attrs.creationTime();
            FileTime timeMod = attrs.lastModifiedTime();
            
		    
            String pattern = "yyyy-MM-dd HH:mm:ss";
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String formatted = simpleDateFormat.format( new Date( time.toMillis() ));
            String formattedMod = simpleDateFormat.format( new Date( timeMod.toMillis() ));
            

            System.out.println( "The file creation date and time is: " + formatted );
            System.out.println( "Last modification on file was made: " + formattedMod );
	} catch (IOException e) {
		e.printStackTrace();
	}
    }
        else{
        System.out.println("Selected file does not exist.");  
   
        }
    }
    
    
    public static void create_dir(){
        File path=new File(pathInput());
        if(!path.exists()){
            path.mkdir();
        System.out.println("Folder " + path.getName() + " Successfully created.");  
   
        }
        else {
          System.out.println("Folder can not be created " + path.getName());  

        }
    }
    
    
    public static void rename(){
        System.out.println("Enter the path to the file you want to rename: ");
        File oldPath = new File(pathInput());
        if(!oldPath.exists()){
            System.out.println(oldPath.getName()+ " doesn't exsits.");
            return;
        }
        
        System.out.println("Enter new name of the file...");
        File newPath = new File(pathInput()); 
        
        if(newPath.exists()){
            System.out.println(newPath.getName() + " already exists.");
            return;
        }
        if(oldPath.renameTo(newPath)){
            System.out.println("Rename was successful.");
        }
        else{
            System.out.println("Rename failed.");
        }
        
    }
    
    public static void copy(){  
      System.out.println("Copying files/folders...");
      System.out.println("Enter the path of the file you want to copy:");
      File sourcePath = new File(pathInput());
      System.out.println("Enter destination path:");
      File destinationPath = new File(pathInput()); 
      if(sourcePath.exists() && sourcePath.isDirectory()){
          destinationPath.mkdir();
          for(String f: sourcePath.list()){
              File f1=new File(sourcePath,f);
              File f2=new File(destinationPath,f);
              
              try(FileInputStream inStr = new FileInputStream(f1);  
                 FileOutputStream outStr = new FileOutputStream(f2);) {  
                    byte[] buffer = new byte[1024];  
                    int length;  
                    while((length=inStr.read(buffer))>0) {  
                     outStr.write(buffer,0,length);  
             }  
          } catch (IOException e) {  
            System.out.println("Copying folder has failed.");  
          }  
     }
           System.out.println("Folder successfully copied.");  
      }
      if(sourcePath.exists() && sourcePath.isFile()) {  
            try (FileInputStream inS = new FileInputStream(sourcePath);  
                 FileOutputStream ouS = new FileOutputStream(destinationPath);) {  
                    byte[] buffer = new byte[1024];  
                    int len;  
                    while((len=inS.read(buffer))>0) {  
                     ouS.write(buffer,0,len);  
                    }  
              System.out.println("File successfully copied.");  
            }catch (IOException e) { 
                System.out.println("Copying file  has failed.");  
         }  
      }  
    }
     
    public static void move() {  
      System.out.println("Moving files from one path to another: "); 
      System.out.println("Enter the path of the file you want to move:");
      File sourcePath = new File(pathInput());
      System.out.println("Enter new destination:");
      File destinationPath = new File(pathInput());
      if(sourcePath.exists() && sourcePath.isDirectory()){
          destinationPath.mkdir();
          for(String f: sourcePath.list()){
              File f1=new File(sourcePath,f);
              File f2=new File(destinationPath,f);
              
              try(FileInputStream inStr = new FileInputStream(f1);  
                 FileOutputStream outStr = new FileOutputStream(f2);) {  
                    byte[] buffer = new byte[1024];  
                    int length;  
                    while((length=inStr.read(buffer))>0) {  
                     outStr.write(buffer,0,length);  
             }  
          } catch (IOException e) {  
            System.out.println("Moving folder has failed.");  
          }  
     }
      if(sourcePath.length() !=0){
          for(String x:sourcePath.list()){
                File xFile=new File(sourcePath,x);
                xFile.delete();
           }
      }
      sourcePath.delete();
       System.out.println("Moving folder was successful.");
      
     
    }
      if(sourcePath.exists() && sourcePath.isFile()) {  
            try (FileInputStream inS = new FileInputStream(sourcePath);  
                 FileOutputStream ouS = new FileOutputStream(destinationPath);) {  
                    byte[] buffer = new byte[1024];  
                    int len;  
                    while((len=inS.read(buffer))>0) {  
                     ouS.write(buffer,0,len);  
                    }  
            }catch (IOException e) { 
                System.out.println("Moving file  has failed.");  
          } 
            sourcePath.delete();
            System.out.println("File successfully moved.");
      
       }
    }
    
    public static void delete(){
        System.out.println("Enter the file path you wish to delete: ");
        File path=new File(pathInput());
        if(path.exists() && path.isDirectory()){
            if(path.length()!=0){
                for(String x:path.list()){
                    File xFile=new File(path,x);
                    xFile.delete();
                }
                
            }
            path.delete();
            System.out.println("Folder successfully deleted.");
           
        } 
        if(path.exists() && path.isFile()){
            path.delete();
            System.out.println("File successfully deleted");
        }

    }
    

}

     

