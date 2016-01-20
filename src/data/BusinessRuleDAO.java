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

		String query = "SELECT * "
				+ "FROM BUSINESSRULE B, OPERATOR O, BUSINESSRULETYPE R, CATEGORY C, ATTRIBUTE A, TARGETTABLE T, LISTVALUE L"
				+ " WHERE B.OPERATORID = O.ID AND B.BUSINESSRULETYPEID = R.ID AND R.CATEGORYID = C.ID AND B.ATTRIBUTEID1 = A.ID AND A.TABLEID = T.ID AND CODE = '"+ruleCode+"'";
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