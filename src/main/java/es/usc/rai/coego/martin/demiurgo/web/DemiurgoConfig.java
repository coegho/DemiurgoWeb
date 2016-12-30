package es.usc.rai.coego.martin.demiurgo.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="demiurgo")
public class DemiurgoConfig {
	private String address;
	
	public String getDemiurgoUrl() {
		return (address.endsWith("/") ? address : address+"/");
	}
}
