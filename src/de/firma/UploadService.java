package de.firma;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ntp.TimeStamp;

/**
 * @author Elena Smagina
 * Class UploadService is responsible for the ftp upload
 *
 */
public class UploadService implements IUploadService {

	
	private UploadConfig uploadConfig;
	private JsonHeaderConfig jsonHeaderConfig;
	private Logger LOGGER;
	
	
	public UploadService(UploadConfig uploadConfig, JsonHeaderConfig jsonHeaderConfig, Logger logger){
		this.uploadConfig=uploadConfig;
		this.jsonHeaderConfig=jsonHeaderConfig;	
		this.LOGGER=logger;
	}
	
	@Override
		public boolean startUpload() {	
		boolean result=false;
		try {
					
			FTPClient con = new FTPClient();
		//	con.connect(uploadConfig.getServer());

			if (con.login(uploadConfig.getUser(), uploadConfig.getPwd())) {
				con.enterLocalPassiveMode(); // important!
				con.setFileType(FTP.BINARY_FILE_TYPE);


				FileInputStream in = new FileInputStream(new File(jsonHeaderConfig.getSource() + uploadConfig.getFileName()));

				
				if (!uploadConfig.getDir().equals("")) {
				
					boolean newDir=con.makeDirectory(uploadConfig.getDir());
					result = con.storeFile(uploadConfig.getDir()+"/"+uploadConfig.getFileName(), in);
				}
				else{
					 result = con.storeFile(uploadConfig.getFileName(), in);
				}		
				
				in.close();
				
				con.logout();
				con.disconnect();
				
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LOGGER.log(Level.INFO,"New Upload");
		LOGGER.log(Level.INFO,"Client number: " + uploadConfig.getClientId());
		LOGGER.log(Level.INFO,"User: "+ uploadConfig.getUser());
		LOGGER.log(Level.INFO,"File name: "+ uploadConfig.getFileName());
		LOGGER.log(Level.INFO,"Dir: "+ uploadConfig.getDir());
		if(result){
			LOGGER.log(Level.INFO,"Status: "+ "upload was successful");
		}
		else{
			LOGGER.log(Level.INFO,"Status: "+ "upload was not successful");
		}		
		 	 
		 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		 LOGGER.log(Level.INFO,"Timestamp: "+ timeStamp);
		
		return result;
	}

}
