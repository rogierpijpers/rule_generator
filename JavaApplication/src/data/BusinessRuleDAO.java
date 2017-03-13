package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.RuleDTO;
import util.RuleHolder;


public class BusinessRuleDAO {

	public BusinessRuleDAO() {

	}

	public static ArrayList<RuleHolder> getAllCodesAndNames() {
		ArrayList<RuleHolder> codesAndNames = new ArrayList<RuleHolder>();
		DatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection();
			ResultSet result = connection.query("SELECT code, name FROM BUSINESSRULE ORDER BY id");

			while (result.next()) {
				codesAndNames.add(new RuleHolder(result.getString(1), result.getString(2)));
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
		return codesAndNames;
	}

	public static ArrayList<RuleHolder> getAllCodesAndNamesFromSet(String setName) {
		ArrayList<RuleHolder> codesAndNames = new ArrayList<RuleHolder>();
		DatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection();
			ResultSet result = connection
					.query("SELECT BUSINESSRULE.code, BUSINESSRULE.name FROM BUSINESSRULE, BUSINESSRULESET, SETB where BUSINESSRULE.id = BUSINESSRULESET.BUSINESSRULEID and BUSINESSRULESET.SETID = SETB.ID and SETB.name = '"
							+ setName + "' ORDER BY BUSINESSRULE.id");

			while (result.next()) {
				codesAndNames.add(new RuleHolder(result.getString("code"), result.getString("name")));
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
		return codesAndNames;
	}

	public static RuleDTO getBusinessRule(String ruleCode) {
		RuleDTO ruleDTO = null;
		DatabaseConnection connection = null;

		String query = "SELECT B.ID AS RULE_ID, B.CODE AS RULE_CODE, B.NAME AS RULE_NAME, B.OPERATORID AS OPERATOR_ID, R.CODE AS RULETYPE_CODE, R.CODETYPE AS RULE_CODETYPE, C.NAME AS CATEGORY, B.FAILUREMESSAGE AS FAILUREMESSAGE, B.MINVALUE AS MINVALUE, B.MAXVALUE AS MAXVALUE, B.VALUE AS VALUE, B.PLSQL AS PLSQL, B.PRIMARYKEY AS PK, B.FOREIGNKEY AS FK," 
		+"B.ATTRIBUTEID1 AS ATTRIBUTE_1, B.ATTRIBUTEID2 AS ATTRIBUTE_2 "
+"FROM BUSINESSRULE B, BUSINESSRULETYPE R, CATEGORY C "
+"WHERE B.BUSINESSRULETYPEID = R.ID "
+"AND R.CATEGORYID = C.ID "
+"AND B.CODE = '"+ruleCode+"'";

		try {
			connection = new DatabaseConnection();
			ResultSet result = connection.query(query);
			while (result.next()) {
				int ruleID = result.getInt("RULE_ID");
				String code = result.getString("RULE_CODE");
				String name = result.getString("RULE_NAME");
				String businessRuleTypeCode = result.getString("RULETYPE_CODE");
				String ruleCodeType = result.getString("RULE_CODETYPE");
				String failureMessage = result.getString("FAILUREMESSAGE");
				String minValue = result.getString("MINVALUE");
				String maxValue = result.getString("MAXVALUE");
				String value = result.getString("VALUE");
				String plSql = result.getString("PLSQL");
				String primaryKey = result.getString("PK");
				String foreignKey = result.getString("FK");
				
				if(minValue != null){
					minValue = parseValue(minValue);
				}
				if(maxValue != null){
					maxValue = parseValue(maxValue);
				}
				if(value != null){
					value = parseValue(value);
				}
				
				String categoryName = result.getString("CATEGORY");
				
				int attributeID1;
				int attributeID2;
				try{
					attributeID1 = result.getInt("ATTRIBUTE_1");
				}catch(NullPointerException e){
					attributeID1 = 0;
				}
				try{
					attributeID2 = result.getInt("ATTRIBUTE_2");
				}catch(NullPointerException e){
					attributeID2 = 0;
				}
				
				int operatorID;
				try{
					operatorID = result.getInt("OPERATOR_ID");
				}catch(NullPointerException e){
					operatorID = 0;
				}
				
				String operatorName = null;
				String operatorCharacter = null;
				
				if(operatorID != 0){
					String operatorQuery = "SELECT NAME AS OPERATOR_NAME, CHARACTER AS OPERATOR_CHAR FROM OPERATOR WHERE ID = "+operatorID+"";
					ResultSet operatorSet = connection.query(operatorQuery);
					while(operatorSet.next()){
						operatorName = operatorSet.getString("OPERATOR_NAME");
						operatorCharacter = operatorSet.getString("OPERATOR_CHAR");
					}
				}
				
				ArrayList<String> listValue = null;
				if(businessRuleTypeCode.equals("ALIS")){
					listValue = new ArrayList<String>();
					String alisQuery = "SELECT VALUE FROM LISTVALUE WHERE BUSINESSRULEID = "+ruleID+"";
					ResultSet listSet = connection.query(alisQuery);
					while(listSet.next()){
						listValue.add(listSet.getString("VALUE"));
					}
				}
				
				String attribute1Name = null;
				String attribute1TableName = null;
				String attribute1TargetDatabase = null;
				String attribute1TargetDatabaseType = null;
				String attribute2Name = null;
				String attribute2TableName = null;
				String attribute2TargetDatabase = null;
				String attribute2TargetDatabaseType = null;

				AttributeDTO attributeDTO = new AttributeDTO(connection, attributeID1, attribute1Name, attribute1TableName, attribute1TargetDatabase, attribute1TargetDatabaseType).invoke();
				attribute1Name = attributeDTO.getAttribute1Name();
				attribute1TableName = attributeDTO.getAttribute1TableName();
				attribute1TargetDatabase = attributeDTO.getAttribute1TargetDatabase();
				attribute1TargetDatabaseType = attributeDTO.getAttribute1TargetDatabaseType();

				if(attributeID2 != 0){
					ResultSet attRes = getAttributeResultSet(connection, attributeID2);
					while(attRes.next()){
						attribute2Name = attRes.getString("ATTRIBUTE_NAME");
						attribute2TableName = attRes.getString("TABLE_NAME");
						attribute2TargetDatabase = attRes.getString("DB_NAME");
						attribute2TargetDatabaseType =attRes.getString("DB_TYPE");
					}
				}
				

				ruleDTO = new RuleDTO(code, name, businessRuleTypeCode, ruleCodeType, failureMessage, minValue, maxValue,
						value, listValue, plSql, primaryKey, foreignKey, operatorName, operatorCharacter,
						attribute1Name, attribute1TableName, attribute1TargetDatabase, attribute1TargetDatabaseType,
						attribute2Name, attribute2TableName, attribute2TargetDatabase, attribute2TargetDatabaseType, categoryName);
			}		
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
		return ruleDTO;
	}

	private static ResultSet getAttributeResultSet(DatabaseConnection connection, int attributeID) {
		String attQuery = "SELECT A.NAME AS ATTRIBUTE_NAME, T.NAME AS TABLE_NAME, T.ORACLETARGETDATABASEID AS DB_NAME, T.DATABASETYPE AS DB_TYPE "
                +"FROM ATTRIBUTE A, TARGETTABLE T "
                +"WHERE A.TABLEID = T.ID "
                +"AND A.ID = "+attributeID+"";
		return connection.query(attQuery);
	}

	private static String parseValue(String value){
		try{
			DateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
			@SuppressWarnings("unused")
			Date input = fmt.parse(value);
			
			value = "to_date ('"+value+"', 'dd-mon-yyyy')";
		}catch(ParseException e){
			try{
				@SuppressWarnings("unused")
				double valueChek = Double.parseDouble(value);
			}catch(NumberFormatException e2){
				value = "'"+value+"'";
			}
			
		}
		return value;
	}


	private static class AttributeDTO {
		private DatabaseConnection connection;
		private int attributeID1;
		private String attribute1Name;
		private String attribute1TableName;
		private String attribute1TargetDatabase;
		private String attribute1TargetDatabaseType;

		public AttributeDTO(DatabaseConnection connection, int attributeID1, String attribute1Name, String attribute1TableName, String attribute1TargetDatabase, String attribute1TargetDatabaseType) {
			this.connection = connection;
			this.attributeID1 = attributeID1;
			this.attribute1Name = attribute1Name;
			this.attribute1TableName = attribute1TableName;
			this.attribute1TargetDatabase = attribute1TargetDatabase;
			this.attribute1TargetDatabaseType = attribute1TargetDatabaseType;
		}

		public String getAttribute1Name() {
			return attribute1Name;
		}

		public String getAttribute1TableName() {
			return attribute1TableName;
		}

		public String getAttribute1TargetDatabase() {
			return attribute1TargetDatabase;
		}

		public String getAttribute1TargetDatabaseType() {
			return attribute1TargetDatabaseType;
		}

		public AttributeDTO invoke() throws SQLException {
			if(attributeID1 != 0){
                ResultSet attRes = getAttributeResultSet(connection, attributeID1);
                while(attRes.next()){
                    attribute1Name = attRes.getString("ATTRIBUTE_NAME");
                    attribute1TableName = attRes.getString("TABLE_NAME");
                    attribute1TargetDatabase = attRes.getString("DB_NAME");
                    attribute1TargetDatabaseType =attRes.getString("DB_TYPE");
                }
            }
			return this;
		}
	}
}