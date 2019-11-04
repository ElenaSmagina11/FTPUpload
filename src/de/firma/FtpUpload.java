package de.firma;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Elena Smagina
 * Main Class of the application
 *
 */
public class FtpUpload {

	private static String jsonFilePath = "/mainfolder/config.json"; //correct path

	private static JsonController jsonController = new JsonController();
	private static Logger LOGGER = Logger.getLogger(FtpUpload.class.getName());
	

	public static void main(String[] args) {
		try {
			String logPath = "/mainfolder/logs/ftpUploadLog.txt";
			FileHandler handler = new FileHandler(getPath(logPath), true);
			SimpleFormatter formatter = new SimpleFormatter();  
	        handler.setFormatter(formatter); 
			LOGGER.addHandler(handler);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonFilePath = getPath(jsonFilePath);
		System.out.println(jsonFilePath);
		process(jsonFilePath);
	}

	static String getPath(String s) {
		String absPath = Paths.get("").toAbsolutePath().toString();
		absPath = absPath.replaceAll("\\\\", "/");
		return absPath + s;

	}

	static boolean process(String configJsonFile) {

		boolean result = false;
		// parse json file
		jsonController.parseJson(configJsonFile);

		// search for files
		File folder = new File(jsonController.getJsonHeaderConfig().getSource());

		String[] availableFiles = folder.list();
		ArrayList<ClientFile> files = new ArrayList<>();

		for (String fileName : availableFiles) {
			files.add(new ClientFile(fileName));
		}

		// upload files
		for (ClientFile file : files) {

					if (!file.checkFile(jsonController)) {
				try {
					moveFileToFailed(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// upload
				result = upload(file.getName(), file.getClientId());
				try {
					if (result) {
						// getlogString();
						moveFileToBackup(file);// ubrat getName
					} else {
						moveFileToFailed(file);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		return result;
	}

	private static boolean upload(String fileName, String clientIdInFile) {

		boolean result = false;
		Client client = jsonController.getClientForClientIdInFile(clientIdInFile);
		if (client == null) {
			return result;
		}
 
		UploadConfig uploadConfig = new UploadConfig(client, fileName);

		UploadService uploadService = new UploadService(uploadConfig, jsonController.getJsonHeaderConfig(), LOGGER);

		try {
			int maxRetries = 3;
			int retryCounter = 0;
			while (retryCounter < maxRetries) {
				try {
					// FTP Upload
					 result=uploadService.startUpload() ;
					if (result) {

						return result;
					}
					retryCounter++;
				} catch (Exception e) {
					retryCounter++;
				}
				// log if success=true or false
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static void moveFileToBackup(ClientFile file) throws IOException {
		String name=file.getName();
		String from = jsonController.getJsonHeaderConfig().getSource() + "/"  + name;
		String to = jsonController.getJsonHeaderConfig().getBackup() + "/" +file.getClientId()+"/"+ name;

		File theDir = new File(jsonController.getJsonHeaderConfig().getBackup() + "/" +file.getClientId()+"/");
		if(!theDir.exists()) {   
		   		    try{
		        theDir.mkdir();
		    } 
		    catch(SecurityException se){
		        //todo:handle it
		    }  }
		Files.move(Paths.get(from), Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
	}

	private static void moveFileToFailed(ClientFile file) throws IOException {
		String name=file.getName();
		String from = jsonController.getJsonHeaderConfig().getSource() + "/" + name;
		String to = jsonController.getJsonHeaderConfig().getFailed() + "/" + name;

		Files.move(Paths.get(from), Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
	}

}
