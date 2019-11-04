package de.firma;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Elena Smagina
 * Class JsonController stores information from the json file and parsing methods
 *
 */
public class JsonController {

	private JsonHeaderConfig jsonHeaderConfig;
	private  ArrayList<Client> availableClients;
	private  ArrayList<String> availableClientsIds;
	
	public JsonController() {
		// TODO Auto-generated constructor stub
	}


	public JsonHeaderConfig getJsonHeaderConfig() {
		return jsonHeaderConfig;
	}
	
	public ArrayList<Client> getAvailableClients() {
		return availableClients;
	}
	
	public ArrayList<String> getAvailableClientsIds() {
		return availableClientsIds;
	}
	
	public void parseJson(String jsonFile){
		
		try {
		FileReader reader = new FileReader(jsonFile);
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(reader);
		
		jsonHeaderConfig=new JsonHeaderConfig(jsonObject);
		
		JSONObject target = (JSONObject) new JSONObject(jsonObject).get("target");
		
		availableClients = createClients(target);
		
		availableClientsIds= new ArrayList<>();
		for (Client client : availableClients) {
			availableClientsIds.add(client.getClientId());
		}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	private  ArrayList<Client> createClients(JSONObject target) {
		ArrayList<Client> listClients = new ArrayList<Client>();

		Set<String> clientIdToclientInfos = target.keySet();
		for (String client : clientIdToclientInfos) {
			String clientId = client;

			JSONObject jsonClient = (JSONObject) target.get(client);

			String name = (String) jsonClient.get("name");
			String server = (String) jsonClient.get("server");
			String user = (String) jsonClient.get("user");
			String pwd = (String) jsonClient.get("pwd");
			String dir = (String) jsonClient.get("dir");

			Client newClient = new Client(clientId, name, dir, user, pwd, server);
			listClients.add(newClient);
		}

		return listClients;
	}

	
	public Client getClientForClientIdInFile(String clientIdInFile){
		for (Client client : availableClients) {

			if (client.getClientId().equals(clientIdInFile))
				return client;
		}

		return null;
	}

	


}
