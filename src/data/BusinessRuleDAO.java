package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import util.RuleHolder;
import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleType;
import domain.businessrule.database.Attribute;
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

		try (DatabaseConnection connection = new DatabaseConnection()) {
			ResultSet result = connection
					.query("SELECT code, name FROM BUSINESSRULE ORDER BY id");

			while (result.next()) {
				codesAndNames.add(new RuleHolder(result.getString(1), result
						.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return codesAndNames;
	}

	public static BusinessRule getDetails(String rulecode) {
		BusinessRule rule = null;

		try (DatabaseConnection connection = new DatabaseConnection()) {
			ResultSet businessruletypecode = connection
					.query("SELECT BT.code FROM BUSINESSRULE B, BUSINESSRULETYPE BT WHERE B.code = '" + rulecode
							+ "' AND B.businessruletypeid = BT.id");

			String name = connection.query("SELECT name FROM BUSINESSRULE WHERE code = '" + rulecode + "'").getString(1);
			/*	String code;
			*	String failureMessage = null;
			*	double minValue = 0;
			*	double maxValue = 0;
			*	double value = 0;
			*	String plSql = null;
			*	Operator operator = null;
			*		String operatorName;
			*		String operatorCharacter;
			*	BusinessRuleType businessRuleType;
			*		String businessruletypeCode;
			*		String businessruletypeDescription;
			*		String businessruletypeExample;
			*		int businessruletypeCodeType;
			*		Category businessruletypeCategory;
			*			String businessruletypeCategoryName;
			*	Attribute attribute1 = null;
			*		String attributeName;
			*		AttributeType attributeAttributeType;
			*			String attributeAttributeTypeName;
			*		Table attributeTable;
			*			String attributeTableName;
			*			String attributeTableOracleTargetDatabaseID;
			*			String attributeTableDatabasetype;
			*	Attribute atrtibute2 = null;
			*		String attributeName;
			*		AttributeType attributeAttributeType;
			*			String attributeAttributeTypeName;
			*		Table attributeTable;
			*			String attributeTableName;
			*			String attributeTableOracleTargetDatabaseID;
			*			String attributeTableDatabasetype;
			*/
			
			businessruletypecode.next();
			switch (businessruletypecode.getString(1)) {
			case "ARNG":
				rule = new AttributeRange();
				break;
			case "ACMP":
				rule = new AttributeCompare();
				break;
			case "ALIS":
				rule = new AttributeList();
				break;
			case "AOTH":
				rule = new AttributeOther();
				break;
			case "TCMP":
				rule = new TupleCompare();
				break;
			case "TOTH":
				rule = new TupleOther();
				break;
			case "ICMP":
				rule = new InterEntityCompare();
				break;
			case "EOTH":
				rule = new EntityOther();
				break;
			case "MODI":
				rule = new Modify();
				break;
			}

			if (businessruletypecode.next()) {
				throw new Exception("More than one result fetched while only one was expected!:\n"
						+ "	at data.BusinessRuleDAO.getDetails(BusinessRuleDAO.java:82)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rule;
	}
}