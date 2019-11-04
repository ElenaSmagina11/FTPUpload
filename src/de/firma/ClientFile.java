package de.firma;


import org.apache.commons.lang3.StringUtils;

/**
 * @author Elena Smagina
 * Class ClientFile stores information about a file that should be uploaded
 *
 */
public class ClientFile {

	private String clientId;
	private String name;
	private String extension;
	
	public ClientFile(String fileName){
		name=fileName;
		clientId=StringUtils.substringBefore(fileName, "_");
		extension= "." + StringUtils.substringAfterLast(fileName, ".");
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public String getName(){
		return name;
	}
	public String getExtension() {
		return extension;
	}
	
	/**
	 *  checks if client id exists in the json config file and if the extensionis correct
	 *
	 */
	public boolean checkFile(JsonController jsonController) {
		// check client id
			if (!jsonController.getAvailableClientsIds().contains(clientId))
			return false;

		// check extension
				if (!extension.equals(jsonController.getJsonHeaderConfig().getExtention()))
			return false;

		return true;
	}

}
