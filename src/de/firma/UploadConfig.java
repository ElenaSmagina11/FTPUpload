package de.firma;

/**
 * @author Elena Smagina
 * Class UploadConfig stores setup information from the json file  for one client
 *
 */
public class UploadConfig {
	
	private String server;
	private String dir;
	private String user;
	private String pwd;

	private String fileName;
	private String clientId;
	
public String getServer() {
		return server;
	}

	public String getDir() {
		return dir;
	}

	public String getUser() {
		return user;
	}


	public String getPwd() {
		return pwd;
	}


	public String getFileName() {
		return fileName;
	}
	
	public String getClientId() {
		return clientId;
	}


public UploadConfig(Client client, String fileName){
	this.server=client.getServer();
	this.dir=client.getDir();
	this.user=client.getUser();
	this.pwd=client.getPwd();
	this.fileName=fileName;
	this.clientId=client.getClientId();
}



}
