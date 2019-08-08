import java.text.SimpleDateFormat

public class NotProcessedException extends Exception{
  
  public NotProcessedException(String message){
    super(message)
  }
  
}

File txtFile = new File("../automation/folders_SVODSTB.txt");

if(txtFile.exists()){

  if(txtFile.getText().length() > 0){
  
    throw new NotProcessedException("Directory file hasn't processed yet...aborting")
  
   println "Directory file hasn't processed yet...aborting"
  
    return
  }

}


/*GENERATE FIRST*/

//generate and convert value to string
def svodGenref =  String.valueOf(((int)((Math.random()*999959497)+1))).substring(0, 7)

//uiD setup
def svoduid_full = String.valueOf(((int)((Math.random()*99999)+1)))
def svoduid_4digits = svoduid_full.substring(0, 3)
def svoduid_2digits = svoduid_full.substring(2, 4)
def svoduid = svoduid_4digits + "SVOD" + svoduid_2digits
//uiD setup

def svodSeriesId = String.valueOf(((int)((Math.random()*99999)+1))).substring(0, 4)
def svodSeasonId = String.valueOf(((int)((Math.random()*99999)+1))).substring(0, 4)

/*GENERATE FIRST*/

//read file:
def xmlContents = new File("collections/collection.json").getText()

//where to put new file
def networkPath =  "collections/collection1.json"

//put values in array
def vals = [svodGenref,svoduid,svodSeriesId, svodSeasonId]

def keys = ["{#Project#svodGenref}","{#Project#svoduid}","{#Project#SVODseriesid}", "{#Project#SVODseasonid}"]

def key = keys[0]

for(def i = 0; i < 4; ++i){

	//log.info xmlContents//keys[i] + " " + vals[i]
	
	xmlContents = xmlContents.replace(keys[i],vals[i]);	
	
}

File file = new File(networkPath)

file.text = xmlContents

println ""
println "Done updating xml: genref:" + svodGenref + " udi:" + svoduid + " seriesid:" + svodSeriesId
println ""

println "=================================================================="
println ""

log_genref(svodGenref)

def log_genref(svodGenref){

  File file = new File("../../batch/SVOD_folders.txt")

  file << svodGenref + "\n"

}

/*DROP IMAGE==============================================================*/
/*def soureFileImg = "//172.16.103.220/MediaManager/Automation image ingest/automation.png"
def destFileImg = "//172.16.103.220/MediaManager/Images/"

def extImg =  soureFileImg.substring(soureFileImg.length() - 4)
def seriesIdImg = svodSeriesId

def srcStreamImg = new File(soureFileImg).newDataInputStream()
def dstStreamImg = new File(destFileImg + svodSeriesId + extImg).newDataOutputStream()
dstStreamImg << srcStreamImg
srcStreamImg.close()
dstStreamImg.close()

println "Done dropping image: " + destFileImg + seriesIdImg + extImg

println ""
println "=================================================================="
println ""
*/
/*DROP ASSET==============================================================*/
//specify source & dest

/*def soureFileAss = "//172.16.103.220/Encoder_Area/Ardome/Automation files/automation_ssm.mxf"


def destFileAss = "//172.16.103.220/Encoder_Area/Ardome/AUTOMATION_SYSTEM/"

//get the file extension
def extAss =  soureFileAss.substring(soureFileAss.length() - 4)

//get new file name values
def newfilenameAss = svodGenref + "_" + svoduid + extAss

def srcStreamAss = new File(soureFileAss).newDataInputStream()
def dstStreamAss = new File(destFileAss + newfilenameAss).newDataOutputStream()

dstStreamAss << srcStreamAss
srcStreamAss.close()
dstStreamAss.close()

println ""
println "Done dropping asset: " + newfilenameAss
println ""
println "=================================================================="
println ""

  
if (checkDir("../automation/")){
    logFolders(svodGenref)    
  }




def logFolders(genref){

  File file = new File("../automation/folders_SVODSTB.txt")
  
  file.text = ''
 
  file << genref + "_" + "11103\n" +  genref + "_" + "11203\n" +  genref + "_" + "11303\n" +  genref + "_" + "11403"
  
}

//Update file for approval

//check if dir exists
if(checkDir("../automation/collections/")){

  def jsonContents = new File("collections/SVOD-STB-APPROVE-ALL.json").getText()
  def jsonFilePath = "../automation/collections/SVOD-STB-APPROVE-ALL1.json"

  jsonContents = jsonContents.replace("{genref}",svodGenref);

  File jsonFile = new File(jsonFilePath)

  jsonFile.text = jsonContents

} else {

throw new IOException("Folder path not found: " + "../automation/collections/")

}

def checkDir(def path){

def folder = new File( path )

  if( !folder.exists() ) {
    // Create all folders up-to and including B
    folder.mkdirs()
  } else {
    return true
  }

  return true
} */