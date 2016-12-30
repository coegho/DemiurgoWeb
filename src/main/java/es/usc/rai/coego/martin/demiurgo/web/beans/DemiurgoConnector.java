package es.usc.rai.coego.martin.demiurgo.web.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import es.usc.rai.coego.martin.demiurgo.json.LoginRequest;

@Component
@Scope(value = "singleton")
public class DemiurgoConnector {
	@Value("${demiurgo.address}")
	protected String url;

	public String login(String username, String pass, String world) {
		RestTemplate restTemplate = new RestTemplate();

		LoginRequest lr = new LoginRequest();
		lr.setName(username);
		lr.setPassword(pass);
		lr.setWorld(world);

		HttpEntity<LoginRequest> request = new HttpEntity<>(lr);

		return restTemplate.postForObject(url + "login", request, String.class);
	}
	
	public <T> T doGet(String token, String action, Class<T> responseType) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		if(token != null) {
			headers.set("Authorization", "Bearer " + token);
		}
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<T> res = restTemplate.exchange(url + action, HttpMethod.GET, entity, responseType);
		return res.getBody();
	}

	public <T> T doGet(String token, String action, MultiValueMap<String, String> params, Class<T> responseType) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + action);

		builder.queryParams(params);

		headers.set("Authorization", "Bearer " + token);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<T> res = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity,
				responseType);
		return res.getBody();
	}

	public <T, R> T doPost(String token, String action, R request, Class<R> requestType, Class<T> responseType) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		if(token != null) {
			headers.set("Authorization", "Bearer " + token);
		}
		HttpEntity<R> entity = new HttpEntity<>(request, headers);

		ResponseEntity<T> res = restTemplate.exchange(url + action, HttpMethod.POST, entity, responseType);
		return res.getBody();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if (!url.endsWith("/")) {
			url += "/";
		}
		this.url = url;
	}
}
