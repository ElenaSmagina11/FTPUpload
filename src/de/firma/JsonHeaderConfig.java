package de.firma;

import java.nio.file.Paths;

import org.json.simple.JSONObject;

/**
 * @author Elena Smagina
 * Class JsonHeaderConfig stores setup information from the json file
 *
 */
public class JsonHeaderConfig {

	private String jobname;
	  
    private String extention;
   
	private String logfile;          
           
	private String backup;
   
   private String failed;
   
   private String source;
   
   public  JsonHeaderConfig(JSONObject jsonObject){
	   this.jobname=jsonObject.get("jobname").toString();
	   this.extention= jsonObject.get("extention").toString();
	   this.logfile=getPath(jsonObject.get("logfile").toString());
	   this.backup=getPath(jsonObject.get("backup").toString());
	   this.failed=getPath(jsonObject.get("failed").toString());
	   this.source=getPath(jsonObject.get("source").toString());
	   System.out.println(this.source);
	   
//	   this.jobname=jsonObject.get("jobname").toString();
//	   this.extention= jsonObject.get("extention").toString();
//	   this.logfile=jsonObject.get("logfile").toString();
//	   this.backup=jsonObject.get("backup").toString();
//	   this.failed=jsonObject.get("failed").toString();
//	   this.source=jsonObject.get("source").toString();
   }
   static String getPath(String s) {
		String absPath = Paths.get("").toAbsolutePath().toString();
		absPath = absPath.replaceAll("\\\\", "/");
		return absPath + s;

	}
	public String getJobname() {
		return jobname;
	}


	public String getExtention() {
		return extention;
	}

	public String getLogfile() {
		return logfile;
	}


	public String getBackup() {
		return backup;
	}


	public String getFailed() {
		return failed;
	}


	public String getSource() {
		return source;
	}

}
