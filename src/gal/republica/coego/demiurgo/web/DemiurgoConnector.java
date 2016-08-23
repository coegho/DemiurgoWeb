package gal.republica.coego.demiurgo.web;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import gal.republica.coego.demiurgo.lib.ServerInterface;

public class DemiurgoConnector {
	protected static ServerInterface h;

	public static void connectWithDemiurgo() {
		String host = "localhost";
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			h = (ServerInterface) registry.lookup("Demiurgo");
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public static void closeConnection() {
		h = null;
	}

	public static ServerInterface getInterface() {
		return h;
	}
}
