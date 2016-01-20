package data;

import java.sql.ResultSet;
import java.util.ArrayList;
import util.RuleTypeDTO;

public class BusinessRuleTypeDAO {

	private RuleTypeDTO ruleTypeDTO;
	
	public ArrayList<RuleTypeDTO> getAllBusinessRules(){
	ArrayList<RuleTypeDTO> ruleTypeDTOList = new ArrayList<>();
	DatabaseConnection connection = null;
	try {
		connection = new DatabaseConnection();
		String query = "SELECT * FROM BUSINESSRULETYPE B, CATEGORY C WHERE B.CATEGORYID = C.ID";
		ResultSet result = connection.query(query);
		
		while (result.next()) {
			String ruleCode = result.getString(2);
			String description = result.getString(3);
			String example = result.getString(4);
			String codeType = result.getString(5);
			String categoryName =result.getString(8);
			ruleTypeDTOList.add(new RuleTypeDTO(ruleCode, description, example, codeType, categoryName));
			
		}
		
		result.close();

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return ruleTypeDTOList;
		
	}
	public RuleTypeDTO getRuleType(String code){
		DatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection();
			ResultSet result = connection.query("SELECT * FROM BUSINESSRULETYPE B, CATEGORY C WHERE B.CATEGORYID = C.ID AND WHERE CODE = '"+ code +"'");

			while (result.next()) {
				String ruleCode = result.getString(2);
				String description = result.getString(3);
				String example = result.getString(4);
				String codeType = result.getString(5);
				String categoryName =result.getString(8);
			ruleTypeDTO = new RuleTypeDTO(ruleCode, description, example, codeType, categoryName);
			}
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ruleTypeDTO;
		
		
	}
}
