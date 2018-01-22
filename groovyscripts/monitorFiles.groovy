//check if file exists

import groovy.io.FileType

public class NoProcessedException extends Exception{
  
  public NoProcessedException(String message){
    super(message)
  }
  
}

def logPaths = new File("../folders_SVODSTB.txt")
def checkText = new File("../folders_SVODSTB.txt").getText()

if (!(checkText.length() > 0)) {
  
  println "Directory file not populated yet... aborting"
  
 // hudson.model.Result.SUCCESS;
  return
}

def a1 = 0;

def a2 = 0;

def a3 = 0;

def a4 = 0;
def missingFolders = ""
println "" 
println "Folder(s) scanned: " 

logPaths.withReader { reader ->
  while ((line = reader.readLine())!=null) {
           
 	foldername = "//172.16.103.220/MediaManager/irdeto.com/ContentGroup/" + line
    
	def list = []

	def dir = new File(foldername)
     println " " + foldername 
    
    if (dir.exists()){
    
		dir.eachFileRecurse (FileType.FILES) { file ->
       	
          println "   " + file.path

          if (file.path.indexOf("11103") > 0){
            if (file.getName().indexOf("m1.mpg") > 0){
                a1++
              }
            }
          
          if (file.path.indexOf("11203") > 0){
          
            if ((file.getName().indexOf("m1.mpg") > 0) || (file.getName().indexOf(".mpg.idx") > 0)){
                a2++
              }
            }

          if (file.path.indexOf("11303") > 0){
            if ((file.getName().indexOf("m1.mpg") > 0) || (file.getName().indexOf(".mpg.idx") > 0)){
                a3++
              }
            }
          

           if (file.path.indexOf("11403") > 0){
              
            if ((file.getName().indexOf("m1.mpg") > 0) || (file.getName().indexOf(".mpg.idx") > 0)){
                a4++
              }
            }      
          
  			list << file
	  	}
    }  else {
      missingFolders += foldername + "\n"
      }
    
         }
      }

def fileExists(def path){
  
 return new File(path).exists()
  
}
println ""
if(a1 >= 2){println "SD - YES"} else {println "SD - X"}
if(a2 >= 3){println "HD - YES"} else {println "HD - X"}
if(a3 >= 3){println "EXP - YES"} else {println "EXP - X"}
if(a4 >= 3){println "NAN - YES"} else {println "NAN - X"}
println ""

if(missingFolders.length() > 0){
  println "The following folder(s) doesn't/don't exist:"
  println " " + missingFolders
}

if((a1 >= 1) && (a2 >= 3) && (a3 >= 3) && (a4 >= 3)) {
  
  logPaths.text = ''
  
 // hudson.model.Result.SUCCESS;
} else {

  throw new NoProcessedException("Not all files were processed...");
  //hudson.model.Result.FAILURE;
}
