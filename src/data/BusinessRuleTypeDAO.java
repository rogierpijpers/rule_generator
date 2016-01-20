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
			ruleTypeDTOList.add(new RuleTypeDTO(result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(8)));
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
			ResultSet result = connection.query("SELECT * FROM BUSINESSRULETYPE WHERE CODE = '"+ code +"'");

			while (result.next()) {
			ruleTypeDTO = new RuleTypeDTO(result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
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
