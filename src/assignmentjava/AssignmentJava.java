package assignmentjava;

import java.util.Scanner;

public class AssignmentJava {

    public static void main(String[] args) {
        
        System.out.println("Enter the command you want to perform:");
        printOptions();
        Scanner scanner=new Scanner(System.in);
        
            while(true){
            try{
                
                Command command=readCommand(scanner);
                System.out.println("Choosen command accepted ");
                switch (command){  
                case LIST:  
                 CommandFunctions.list();
                   break;  
                case INFO :  
                 CommandFunctions.info();
                   break;  
                case CREATE_DIR :  
                 CommandFunctions.create_dir();
                   break;  
                case RENAME:  
                 CommandFunctions.rename(); 
                  break;  
                case COPY :  
                 CommandFunctions.copy(); 
                  break;  
                case MOVE :  
                 CommandFunctions.move(); 
                  break;  
                case DELETE :  
                 CommandFunctions.delete(); 
                  break;
                default : 
                   System.out.println("No such thing.");
                   break;
                  
                }
              
            }catch (Exception e) {   
                   System.out.println("Operation does not exist.");
             
        }
           
           System.out.println();
           System.out.println("Enter another command");
           
           
    }
    }
     private static void printOptions(){
        for(int i=0; i<Command.values().length;i++){
            Command command=Command.values()[i];
            System.out.println(command.name()+" - " +command.getDescription());
            
        }
     }
        
     private static Command readCommand(Scanner scanner){
         Command command= null;
         while(command== null){
             String line=scanner.nextLine();
            command=Command.of(line);
             if(command==null){
                 System.out.println("Not a valid command.");
             }
         }
         return command;
            
            
        }
    }
    
    

