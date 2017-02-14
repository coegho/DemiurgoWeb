package es.usc.rai.coego.martin.demiurgo.web.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class VarTypeToCss {
	public String getCss(String type) {
		if(type.endsWith("[]")) {
			return "arrayvar";
		}
		if(type.startsWith("OBJECT")) {
			return "objvar";
		}
		return type.toLowerCase() + "var";
	}
}
