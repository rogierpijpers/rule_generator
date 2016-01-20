package data;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import util.RuleDTO;
import util.RuleHolder;
import util.RuleTypeDTO;
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

	public ArrayList<RuleDTO> getAllBusinessRules() {
		ArrayList<RuleDTO> ruleDTOList = new ArrayList<>();
		DatabaseConnection connection = null;

		String query = "SELECT * "
				+ "FROM BUSINESSRULE B, OPERATOR O, BUSINESSRULETYPE R, CATEGORY C, ATTRIBUTE A, TARGETTABLE T, LISTVALUE L"
				+ " WHERE B.OPERATORID = O.ID AND B.BUSINESSRULETYPEID = R.ID AND R.CATEGORYID = C.ID AND B.ATTRIBUTEID1 = A.ID AND A.TABLEID = T.ID";
		try {
			connection = new DatabaseConnection();
			ResultSet result = connection.query(query);
			while (result.next()) {
				// id = result.getInt(1);
				String code = result.getString(3);
				String name = result.getString(2);
				String businessRuleTypeCode = result.getString(19);
				String failureMessage = result.getString(4);
				String minValue = result.getString(5);
				String maxValue = result.getString(6);
				String value = result.getString(7);
				String listValue = result.getString(35);
				String plSql = result.getString(8);
				String primaryKey = result.getString(13);
				String foreignKey = result.getString(14);
				// operatorID = result.getInt(9);
				String operatorName = result.getString(16);
				String operatorCharacter = result.getString(17);
				// businessRuleTypeID = result.getInt(10);
				// attributeID1 = result.getInt(11);
				String attribute1Name = result.getString(27);
				String attribute1TableName = result.getString(31);
				String attribute1TargetDatabase = result.getString(32);
				String attribute1TargetDatabaseType = result.getString(33);
				// attributeID2 = result.getInt(12);
				String attribute2Name = result.getString(27);
				String attribute2TableName = result.getString(31);
				String attribute2TargetDatabase = result.getString(32);
				String attribute2TargetDatabaseType = result.getString(33);

				ruleDTOList.add(new RuleDTO(code, name, businessRuleTypeCode, failureMessage, minValue, maxValue,
						value, listValue, plSql, primaryKey, foreignKey, operatorName, operatorCharacter,
						attribute1Name, attribute1TableName, attribute1TargetDatabase, attribute1TargetDatabaseType,
						attribute2Name, attribute2TableName, attribute2TargetDatabase, attribute2TargetDatabaseType));
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

		return ruleDTOList;
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

		String query = "SELECT B.ID AS RULE_ID, B.CODE AS RULE_CODE, B.NAME AS RULE_NAME, R.CODE AS RULETYPE_CODE, C.NAME AS CATEGORY, B.FAILUREMESSAGE AS FAILUREMESSAGE, B.MINVALUE AS MINVALUE, B.MAXVALUE AS MAXVALUE, B.VALUE AS VALUE, B.PLSQL AS PLSQL, B.PRIMARYKEY AS PK, B.FOREIGNKEY AS FK," 
		+"O.NAME AS OPERATOR_NAME, O.CHARACTER AS OPERATOR_CHAR, B.ATTRIBUTEID1 AS ATTRIBUTE_1, B.ATTRIBUTEID2 AS ATTRIBUTE_2 "
+"FROM BUSINESSRULE B, BUSINESSRULETYPE R, OPERATOR O, CATEGORY C"
+"WHERE B.BUSINESSRULETYPEID = R.ID"
+"AND B.OPERATORID = O.ID"
+"AND R.CATEGORYID = C.ID"
+"AND B.CODE = '"+ruleCode+"'";
		try {
			connection = new DatabaseConnection();
			ResultSet result = connection.query(query);
			while (result.next()) {
				int ruleID = result.getInt("RULE_ID");
				String code = result.getString("RULE_CODE");
				String name = result.getString("RULE_NAME");
				String businessRuleTypeCode = result.getString("RULETYPE_CODE");
				String failureMessage = result.getString("FAILUREMESSAGE");
				String minValue = result.getString("MINVALUE");
				String maxValue = result.getString("MAXVALUE");
				String value = result.getString("VALUE");
				String plSql = result.getString("PLSQL");
				String primaryKey = result.getString("PK");
				String foreignKey = result.getString("FK");
				String operatorName = result.getString("OPERATOR_NAME");
				String operatorCharacter = result.getString("OPERATOR_CHAR");
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
				
				if(attributeID1 != 0){
					String attQuery = "SELECT A.NAME AS ATTRIBUTE_NAME, T.NAME AS TABLE_NAME, T.ORACLETARGETDATABASEID AS DB_NAME, T.DATABASETYPE AS DB_TYPE"
							+"FROM ATTRIBUTE A, TARGETTABLE T"
							+"WHERE A.TABLEID = T.ID"
							+"AND A.ID = "+attributeID1+"";
					ResultSet attRes = connection.query(attQuery);
					while(attRes.next()){
						attribute1Name = attRes.getString("ATTRIBUTE_NAME");
						attribute1TableName = attRes.getString("TABLE_NAME");
						attribute1TargetDatabase = attRes.getString("DB_NAME");
						attribute1TargetDatabaseType =attRes.getString("DB_TYPE");
					}
				}
				
				if(attributeID2 != 0){
					String attQuery = "SELECT A.NAME AS ATTRIBUTE_NAME, T.NAME AS TABLE_NAME, T.ORACLETARGETDATABASEID AS DB_NAME, T.DATABASETYPE AS DB_TYPE"
							+"FROM ATTRIBUTE A, TARGETTABLE T"
							+"WHERE A.TABLEID = T.ID"
							+"AND A.ID = "+attributeID2+"";
					ResultSet attRes = connection.query(attQuery);
					while(attRes.next()){
						attribute2Name = attRes.getString("ATTRIBUTE_NAME");
						attribute2TableName = attRes.getString("TABLE_NAME");
						attribute2TargetDatabase = attRes.getString("DB_NAME");
						attribute2TargetDatabaseType =attRes.getString("DB_TYPE");
					}
				}
				

				ruleDTO = new RuleDTO(code, name, businessRuleTypeCode, failureMessage, minValue, maxValue,
						value, listValue, plSql, primaryKey, foreignKey, operatorName, operatorCharacter,
						attribute1Name, attribute1TableName, attribute1TargetDatabase, attribute1TargetDatabaseType,
						attribute2Name, attribute2TableName, attribute2TargetDatabase, attribute2TargetDatabaseType);
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
	

}