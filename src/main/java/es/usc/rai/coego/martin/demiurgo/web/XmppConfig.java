package es.usc.rai.coego.martin.demiurgo.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="xmpp")
public class XmppConfig {
	private String host;
	private int port;
	private String httpBind;
	
	public String getHost() {
		return host;
	}
	public int getPort() {
		return port;
	}
	public String getHttpBind() {
		return httpBind;
	}
}
