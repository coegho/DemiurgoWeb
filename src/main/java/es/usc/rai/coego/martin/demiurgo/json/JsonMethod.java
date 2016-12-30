package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

public class JsonMethod {
	private String name;
	private List<String> args; //arguments ordered by num
	private String returnArg;
	
	public JsonMethod() { }
	
	public JsonMethod(String name, List<String> args, String returnArg) {
		super();
		this.name = name;
		this.args = args;
		this.returnArg = returnArg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getArgs() {
		return args;
	}
	public void setArgs(List<String> args) {
		this.args = args;
	}
	public String getReturnArg() {
		return returnArg;
	}
	public void setReturnArg(String returnArg) {
		this.returnArg = returnArg;
	}
}
