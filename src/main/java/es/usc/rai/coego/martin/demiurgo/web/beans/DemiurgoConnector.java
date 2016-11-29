package es.usc.rai.coego.martin.demiurgo.web.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Scope(value = "singleton")
public class DemiurgoConnector {
	@Value(value = "http://localhost:5324/demiurgo/webservice/")
	protected String url;

	public <T> T doGet(String token, String action, Class<T> responseType) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<T> res = restTemplate.exchange(url+action, HttpMethod.GET, entity,
				responseType);
		return res.getBody();
	}

	public <T,R> T doPost(String token, String action, R request, Class<R> requestType, Class<T> responseType) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<R> entity = new HttpEntity<>(request, headers);

		ResponseEntity<T> res = restTemplate.exchange(url+action, HttpMethod.POST, entity, responseType);
		return res.getBody();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if(!url.endsWith("/")) {
			url+="/";
		}
		this.url = url;
	}
}
