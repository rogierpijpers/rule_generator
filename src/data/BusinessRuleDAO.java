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


	public static BusinessRule getDetails(String ruleCode) {

		Operator operator = null;
		BusinessRuleType businessRuleType = null;
		Category category = null;
		BusinessRule rule = null;
		Attribute attribute1 = null, attribute2 = null;
		Table attribute1Table = null, attribute2Table = null;
		TargetDatabase attribute1TargetDatabase = null, attribute2TargetDatabase = null;
		DatabaseConnection connection = null;

		int id, operatorID = 0, businessRuleTypeID = 0, attributeID1 = 0, attributeID2 = 0, categoryID = 0,
				tableID1 = 0, tableID2 = 0;
		String name = null, code = null, failureMessage = null, minValue = null, maxValue = null, value = null,
				plSql = null, businessRuleTypeCode = null, attribute1Name = null, attribute2Name = null,
				attribute1TableName = null, attribute2TableName = null, attribute1TargetDatabaseType = null,
				attribute2TargetDatabaseType = null, primaryKey = null, foreignKey = null;
		ArrayList<String> listValues = getListValues(ruleCode);
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();

		String query = "SELECT * FROM BUSINESSRULE WHERE code = '" + ruleCode + "'";
		try {
			connection = new DatabaseConnection();
			ResultSet result = connection.query(query);
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				code = result.getString(3);
				failureMessage = result.getString(4);
				minValue = result.getString(5);
				maxValue = result.getString(6);
				value = result.getString(7);
				plSql = result.getString(8);
				operatorID = result.getInt(9);
				businessRuleTypeID = result.getInt(10);
				attributeID1 = result.getInt(11);
				attributeID2 = result.getInt(12);
				primaryKey = result.getString(13);
				foreignKey = result.getString(14);
			}

			query = "SELECT * FROM OPERATOR WHERE ID = '" + operatorID + "'";
			result = connection.query(query);
			while (result.next()) {
				String operatorName = result.getString(2);
				String operatorCharacter = result.getString(3);
				operator = new Operator(operatorName, operatorCharacter);

			}
			query = "SELECT * FROM BUSINESSRULETYPE WHERE ID = '" + businessRuleTypeID + "'";
			result = connection.query(query);
			while (result.next()) {
				businessRuleTypeCode = result.getString(2);
				String businessRuleTypeDescription = result.getString(3);
				String businessRuleTypeExample = result.getString(4);
				CodeType businessRuleTypeCodeType = parseToCodeType(result.getString(5));
				categoryID = result.getInt(6);
				businessRuleType = new BusinessRuleType(businessRuleTypeCode, businessRuleTypeDescription,
						businessRuleTypeExample, category, businessRuleTypeCodeType);

			}
			query = "SELECT * FROM CATEGORY WHERE ID = '" + categoryID + "'";
			result = connection.query(query);
			while (result.next()) {
				String categoryName = result.getString(2);
				category = new Category(categoryName);

			}
			query = "SELECT * FROM ATTRIBUTE WHERE ID = '" + attributeID1 + "'";
			result = connection.query(query);
			while (result.next()) {
				attribute1Name = result.getString(2);
				int attributeTypeID = result.getInt(3);
				tableID1 = result.getInt(4);

			}
			query = "SELECT * FROM ATTRIBUTE WHERE ID = '" + attributeID2 + "'";
			result = connection.query(query);
			while (result.next()) {
				attribute2Name = result.getString(2);
				int attributeTypeID2 = result.getInt(3);
				tableID2 = result.getInt(4);

			}
			query = "SELECT * FROM TARGETTABLE WHERE ID = '" + tableID1 + "'";
			result = connection.query(query);
			while (result.next()) {
				attribute1TableName = result.getString(2);
				attribute1TargetDatabase = parseToTargetDatabase(result.getString(3));
				attribute1TargetDatabaseType = result.getString(4);

			}
			query = "SELECT * FROM TARGETTABLE WHERE ID = '" + tableID2 + "'";
			result = connection.query(query);
			while (result.next()) {
				attribute2TableName = result.getString(2);
				attribute2TargetDatabase = parseToTargetDatabase(result.getString(3));
				attribute2TargetDatabaseType = result.getString(4);
			}

			attribute1 = new Column(attribute1Name, attribute1Table);
			attribute1Table = new Table(attribute1TableName, attribute1TargetDatabase);
			attribute2 = new Column(attribute2Name, attribute2Table);
			attribute2Table = new Table(attribute2TableName, attribute2TargetDatabase);
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

		attributes.add(attribute1);
		attributes.add(attribute2);

		switch (businessRuleTypeCode) {
		case "ARNG":
			rule = new AttributeRange(code, name, minValue, maxValue, operator, businessRuleType, attribute1);
			break;
		case "ACMP":
			rule = new AttributeCompare(code, name, value, operator, businessRuleType, attribute1);
			break;
		case "ALIS":
			rule = new AttributeList(code, name, attribute1, operator, businessRuleType, listValues);
			break;
		case "AOTH":
			rule = new AttributeOther(code, name, businessRuleType, attribute1, plSql);
			break;
		case "TCMP":
			rule = new TupleCompare(code, name, operator, businessRuleType, attribute1, attribute2);
			break;
		case "TOTH":
			rule = new TupleOther(code, name, businessRuleType, attribute1, plSql);
			break;
		case "ICMP":

			rule = new InterEntityCompare(code, name, businessRuleType, operator, attribute1, attribute2, primaryKey,foreignKey);
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

		if (databaseType != null) {
			if (databaseType.equals("ORACLE")) {
				targetDatabase = new OracleTargetDatabase();
				targetDatabase.setType(DatabaseType.ORACLE);
			}
		}

		return targetDatabase;
	}

	private static ArrayList<String> getListValues(String ruleCode) {
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
		} finally {
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