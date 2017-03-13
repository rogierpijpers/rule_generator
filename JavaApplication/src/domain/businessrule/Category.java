package domain.businessrule;

import java.util.ArrayList;

public class Category {
	private String name;

	public Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// ---- for retrieving from database

	public static ArrayList<String> getAll(){
		return null;
	}
	
	public static Category getDetails(String name){
		return null;
	}
}
