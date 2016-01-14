package data;

import java.sql.ResultSet;

import util.TemplateDTO;

public class TemplateDAO {

	public TemplateDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public TemplateDTO getTemplate(String name){
		TemplateDTO template = null;
		try(DatabaseConnection connection = new DatabaseConnection()){
			ResultSet result = connection.query("Select * from GeneratorTemplate where name = '"+name+"'");
			if(result.next()){
				String nm = result.getString("name");
				String scr = result.getString("script");
				String arg = result.getString("arguments");
				template = new TemplateDTO(nm, scr, arg);
			}
			result.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return template;
	}

}
