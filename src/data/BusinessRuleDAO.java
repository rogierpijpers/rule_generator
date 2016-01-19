package data;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import util.RuleHolder;
import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleType;
import domain.businessrule.Category;
import domain.businessrule.CodeType;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;
import domain.businessrule.database.DatabaseType;
import domain.businessrule.database.OracleTargetDatabase;
import domain.businessrule.database.Table;
import domain.businessrule.database.TargetDatabase;
import domain.businessrule.rule.AttributeCompare;
import domain.businessrule.rule.AttributeList;
import domain.businessrule.rule.AttributeOther;
import domain.businessrule.rule.AttributeRange;
import domain.businessrule.rule.EntityOther;
import domain.businessrule.rule.InterEntityCompare;
import domain.businessrule.rule.Modify;
import domain.businessrule.rule.Operator;
import domain.businessrule.rule.TupleCompare;
import domain.businessrule.rule.TupleOther;

public class BusinessRuleDAO {

	public BusinessRuleDAO() {

	}

	public static ArrayList<RuleHolder> getAllCodesAndNames(){
		ArrayList<RuleHolder> codesAndNames = new ArrayList<RuleHolder>();
		DatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection();
			ResultSet result = connection
					.query("SELECT code, name FROM BUSINESSRULE ORDER BY id");

			while (result.next()) {
				codesAndNames.add(new RuleHolder(result.getString(1), result
						.getString(2)));
			}
			result.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return codesAndNames;
	}

	public static ArrayList<RuleHolder> getAllCodesAndNamesFromSet(
			String setName){
		ArrayList<RuleHolder> codesAndNames = new ArrayList<RuleHolder>();
		DatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection();
			ResultSet result = connection
					.query("SELECT BUSINESSRULE.code, BUSINESSRULE.name FROM BUSINESSRULE, BUSINESSRULESET, SETB where BUSINESSRULE.id = BUSINESSRULESET.BUSINESSRULEID and BUSINESSRULESET.SETID = SETB.ID and SETB.name = '"
							+ setName + "' ORDER BY BUSINESSRULE.id");

			while (result.next()) {
				codesAndNames.add(new RuleHolder(result.getString("code"),
						result.getString("name")));
			}
			result.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return codesAndNames;
	}
	/*
	 * Wordt aan gewerkt en verbeterd, geen paniek rogier ;)
	 */
	public static BusinessRule getDetails(String ruleCode){

		Operator operator;
		BusinessRuleType businessRuleType;
		Category category;
		BusinessRule rule;
		
		DatabaseConnection connection = null;
				
		String query = "SELECT * FROM BUSINESSRULE WHERE code = '" + ruleCode + "'";
		 try{
			connection = new DatabaseConnection();
			ResultSet result = connection.query(query);
			while(result.next()){
				int id = result.getInt(1);
				String name = result.getString(2);
				String code = result.getString(3);
				String failureMessage = result.getString(4);
				double minValue = result.getDouble(5);
				double maxValue = result.getDouble(6);
				double value = result.getDouble(7);
				String plSql = result.getString(8);
				int operatorID = result.getInt(9);
				int businessRuleTypeID = result.getInt(10);
				int attributeID1 = result.getInt(11);
				int attributeID2 = result.getInt(12);
				
				
			}
			
			query = "SELECT * FROM BUSINESSRULE B, OPERATOR O WHERE B.operatorid = O.id AND B.code = '"+ ruleCode + "'";
			result = connection.query(query);
			while(result.next()){
				String operatorName = result.getString(2);
				String operatorCharacter = result.getString(3);
				operator = new Operator(operatorName, operatorCharacter);
				//ergens aan toevoegen
			}
			query = "SELECT * FROM BUSINESSRULE B, BUSINESSRULETYPE BT WHERE B.businessruletypeid = BT.id AND B.code = '"
					+ ruleCode + "'";
			result = connection.query(query);
			while(result.next()){
				String businessRuleTypeCode = result.getString(2);
				String businessRuleTypeDescription = result.getString(3);
				String businessRuleTypeExample = result.getString(4);
				CodeType businessRuleTypeCodeType = parseToCodeType(result.getString(5));
				businessRuleType = new BusinessRuleType(businessRuleTypeCode,
						businessRuleTypeDescription, businessRuleTypeExample, category,
						businessRuleTypeCodeType);
				//ergens aan toevoegen
			}
			query = "SELECT C.name FROM BUSINESSRULE B, BUSINESSRULETYPE BT, CATEGORY C WHERE B.businessruletypeid = BT.id AND BT.categoryid = C.id AND B.code = '"
					+ ruleCode + "'";
			result = connection.query(query);
			while(result.next()){
				String categoryName = result.getString(2);
				category = new Category(categoryName);
				//ergens toevoegen
			}
			query = "SELECT A.name FROM BUSINESSRULE B, ATTRIBUTE A WHERE B.attributeid1 = A.id AND B.code = '"
					+ ruleCode + "'";
			result = connection.query(query);
			while (result.next()){
				String attribute1Name = result.getString(2);
			}
			
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 finally{
			 connection.close();
		 }
		
		
		
		Attribute attribute1;
		String attribute1Name = getString("SELECT A.name FROM BUSINESSRULE B, ATTRIBUTE A WHERE B.attributeid1 = A.id AND B.code = '"
				+ ruleCode + "'");
		
		Table attribute1Table;
		String attribute1TableName = getString("SELECT T.name FROM BUSINESSRULE B, ATTRIBUTE A, TARGETTABLE T WHERE B.attributeid1 = A.id AND A.tableid = T.id AND B.code = '"
				+ ruleCode + "'");
		
		TargetDatabase attribute1TargetDatabase;
		String attribute1TargetDatabaseType = getString("SELECT T.databasetype FROM BUSINESSRULE B, ATTRIBUTE A, TARGETTABLE T WHERE B.attributeid1 = A.id AND A.tableid = T.id AND B.code = '"
				+ ruleCode + "'");
		attribute1TargetDatabase = parseToTargetDatabase(attribute1TargetDatabaseType);
		attribute1Table = new Table(attribute1TableName,
				attribute1TargetDatabase);
		attribute1 = new Column(attribute1Name, attribute1Table);
		
		Attribute attribute2;
		String attribute2Name = getString("SELECT A.name FROM BUSINESSRULE B, ATTRIBUTE A WHERE B.attributeid2 = A.id AND B.code = '"
				+ ruleCode + "'");
		
		Table attribute2Table;
		String attribute2TableName = getString("SELECT T.name FROM BUSINESSRULE B, ATTRIBUTE A, TARGETTABLE T WHERE B.attributeid2 = A.id AND A.tableid = T.id AND B.code = '"
				+ ruleCode + "'");
		
		TargetDatabase attribute2TargetDatabase;
		String attribute2TargetDatabaseType = getString("SELECT T.databasetype FROM BUSINESSRULE B, ATTRIBUTE A, TARGETTABLE T WHERE B.attributeid2 = A.id AND A.tableid = T.id AND B.code = '"
				+ ruleCode + "'");
		attribute2TargetDatabase = parseToTargetDatabase(attribute2TargetDatabaseType);
		
		attribute2Table = new Table(attribute2TableName,
				attribute2TargetDatabase);
		attribute2 = new Column(attribute2Name, attribute2Table);

		ArrayList<String> listValues = getListValues(ruleCode);

		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(attribute1);
		attributes.add(attribute2);

		switch (businessRuleTypeCode) {
		case "ARNG":
			rule = new AttributeRange(code, name, minValue, maxValue, operator,
					businessRuleType, attribute1);
			break;
		case "ACMP":
			rule = new AttributeCompare(code, name, value, operator,
					businessRuleType, attribute1);
			break;
		case "ALIS":
			rule = new AttributeList(code, name, attribute1, operator, businessRuleType,
					listValues);
			break;
		case "AOTH":
			rule = new AttributeOther(code, name, businessRuleType, attribute1,
					plSql);
			break;
		case "TCMP":
			rule = new TupleCompare(code, name, operator, businessRuleType,
					attributes);
			break;
		case "TOTH":
			rule = new TupleOther(code, name, businessRuleType, attributes);
			break;
		case "ICMP":
			rule = new InterEntityCompare(code, name, businessRuleType,
					attributes);
			break;
		case "EOTH":
			rule = new EntityOther(code, name, businessRuleType, attributes);
			break;
		case "MODI":
			rule = new Modify(code, name, businessRuleType, attributes);
			break;
		}

		return rule;
	}

	private static String getString(String query){
		ResultSet result;
		DatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection();
			result = connection.query(query);
			result.next();
			
			String resString = result.getString(1);
			result.close();
			
			return resString;
		} catch (Exception e) {
			return null;
		}
		finally{
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static double getDouble(String query){
		ResultSet result;
		DatabaseConnection connection = null;
		try{
			connection = new DatabaseConnection();
			result = connection.query(query);
			result.next();

			double resDouble = result.getDouble(1);
			result.close();
			
			return resDouble;
		} catch (Exception e) {
			return -999999999;
		}
		finally{
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static CodeType parseToCodeType(String codeType) {
		if (codeType == null || codeType.equals("")) {
			return null;
		} else {
			for (CodeType cT : CodeType.values()) {
				if (codeType.equals(cT.toString())) {
					return cT;
				}
			}
			return null;
		}
	}

	private static TargetDatabase parseToTargetDatabase(String databaseType) {
		TargetDatabase targetDatabase = null;

		if(databaseType != null){
			if (databaseType.equals("ORACLE")) {
				targetDatabase = new OracleTargetDatabase();
				targetDatabase.setType(DatabaseType.ORACLE);
			}
		}

		return targetDatabase;
	}

	private static ArrayList<String> getListValues(String ruleCode){
		ArrayList<String> listValues = new ArrayList<String>();
		DatabaseConnection connection = null;
		ResultSet result;
		try {
			connection = new DatabaseConnection();
			result = connection
					.query("SELECT L.value FROM BUSINESSRULE B, LISTVALUE L WHERE  L.businessruleid = B.id AND B.code = '"
							+ ruleCode + "'");
			while (result.next()) {
				listValues.add(result.getString(1));
			}
			result.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listValues;
	}
}