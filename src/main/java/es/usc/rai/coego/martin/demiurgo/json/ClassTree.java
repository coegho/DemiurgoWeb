package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

public class ClassTree {
	private String className;
	private List<ClassTree> children;
	
	public ClassTree() { }
	
	public ClassTree(String className, List<ClassTree> children) {
		this.className = className;
		this.children = children;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public List<ClassTree> getChildren() {
		return children;
	}
	
	public void setChildren(List<ClassTree> children) {
		this.children = children;
	}
}
