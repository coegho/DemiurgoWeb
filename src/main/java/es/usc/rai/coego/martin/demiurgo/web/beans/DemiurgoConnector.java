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

@Component
@Scope(value = "singleton")
public class DemiurgoConnector {
	//TODO: hardcoded
	@Value(value = "http://localhost:5324/demiurgo/")
	protected String url;

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
