package assignmentjava;
public enum Command {
    

  LIST("Review content of the folder"),
  INFO("Display information about file or folder"),
  CREATE_DIR("Create new folder"),
  RENAME("Rename file or folder"),
  COPY("Copy file or folder"),
  MOVE("Move file or folder"),
  DELETE("Delete file or folder");

    
    
  private final String description;
  
   Command(String description){
     this.description=description;
     
   }
   
   public String getDescription() {
        return description;
    }
   
   public static Command of(String command) {
    if (command == null || command.length() == 0) {
      return null;
    }
    try {
      return Command.valueOf(command.toUpperCase());
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
    
}

