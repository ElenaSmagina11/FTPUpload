package de.firma;


/**
 * @author Elena Smagina
 * Class Client stores client info
 *
 */
public class Client {
	 private String clientId;
	 private String name;
	 private String dir;
	 private String user;
	 private String pwd;
	 private String server;
	 
	 public Client(String clientId, String name, String dir, String user, String pwd, String server){
		 this.setName(name);
		 this.setServer(server);
		 if(dir!=null)
		    this.setDir(dir);
		 else
			 this.setDir("");
		 this.setUser(user);
		 this.setPwd(pwd);
		 this.setClientId(clientId);
	 }

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
}
