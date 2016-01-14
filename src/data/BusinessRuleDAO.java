package data;

import java.sql.ResultSet;
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
import domain.businessrule.rule.AttributeType;
import domain.businessrule.rule.EntityOther;
import domain.businessrule.rule.InterEntityCompare;
import domain.businessrule.rule.Modify;
import domain.businessrule.rule.Operator;
import domain.businessrule.rule.TupleCompare;
import domain.businessrule.rule.TupleOther;
import domain.businessrule.rule.Value;

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

		String businessruletypecode = getString("SELECT BT.code FROM BUSINESSRULE B, BUSINESSRULETYPE BT WHERE B.code = '" + rulecode
							+ "' AND B.businessruletypeid = BT.id");

		String name = getString("SELECT name FROM BUSINESSRULE WHERE code = '" + rulecode + "'");
		String failureMessage = getString("SELECT failuremessage FROM BUSINESSRULE WHERE code = '" + rulecode + "'");
		double minValue = getDouble("SELECT minvalue FROM BUSINESSRULE WHERE code = '" + rulecode + "'");
		double maxValue = getDouble("SELECT maxvalue FROM BUSINESSRULE WHERE code = '" + rulecode + "'");
		double value = getDouble("SELECT value FROM BUSINESSRULE WHERE code = '" + rulecode + "'");
		String plSql = getString("SELECT plsql FROM BUSINESSRULES WHERE code = '" + rulecode + "'");
		Operator operator;
			String operatorName = getString("SELECT O.name FROM BUSINESSRULE B, OPERATOR O WHERE B.operatorid = O.id AND B.code = '" + rulecode + "'");
			String operatorCharacter = getString("SELECT O.character FROM BUSINESSRULE B, OPERATOR O WHERE B.operatorid = O.id AND B.code = '" + rulecode + "'");
			operator = new Operator(operatorName, operatorCharacter);
		BusinessRuleType businessRuleType;
			String businessruletypeCode = getString("SELECT BT.code FROM BUSINESSRULE B, BUSINESSRULETYPE BT WHERE B.businessruletypeid = BT.id AND B.code = '" + rulecode + "'");
			String businessruletypeDescription = getString("SELECT BT.description FROM BUSINESSRULE B, BUSINESSRULETYPE BT WHERE B.businessruletypeid = BT.id AND B.code = '" + rulecode + "'");
			String businessruletypeExample = getString("SELECT BT.example FROM BUSINESSRULE B, BUSINESSRULETYPE BT WHERE B.businessruletypeid = BT.id AND B.code = '" + rulecode + "'");
			CodeType businessruletypeCodeType = parseToCodeType(getString("SELECT BT.codetype FROM BUSINESSRULE B, BUSINESSRULETYPE BT WHERE B.businessruletypeid = BT.id AND B.code = '" + rulecode + "'"));
			Category category;
				String categoryName = getString("SELECT C.name FROM BUSINESSRULE B, BUSINESSRULETYPE BT, CATEGORY C WHERE B.businessruletypeid = BT.id AND BT.categoryid = C.id AND B.code = '" + rulecode + "'");
				category = new Category(categoryName);
			businessRuleType = new BusinessRuleType(businessruletypeCode, businessruletypeDescription, businessruletypeExample, category, businessruletypeCodeType);
		Attribute attribute1;
			String attribute1Name = getString("SELECT A.name FROM BUSINESSRULE B, ATTRIBUTE A WHERE B.attributeid1 = A.id AND B.code = '" + rulecode + "'");
			AttributeType attribute1Type;
				String attribute1TypeName = getString("SELECT AT.name FROM BUSINESSRULE B, ATTRIBUTE A, ATTRIBUTETYPE AT WHERE B.attributeid1 = A.id AND A.attributetypeid = AT.id AND B.code = '" + rulecode + "'");
				attribute1Type = new AttributeType(attribute1TypeName);
			Table attribute1Table;
				String attribute1TableName = getString("SELECT T.name FROM BUSINESSRULE B, ATTRIBUTE A, TABLE T WHERE B.attributeid1 = A.id AND A.tableid = T.id AND B.code = '" + rulecode + "'");
				TargetDatabase attribute1TargetDatabase;
					String attribute1TargetDatabaseName = getString("SELECT T.databasename FROM BUSINESSRULE B, ATTRIBUTE A, TABLE T WHERE B.attributeid1 = A.id AND A.tableid = T.id AND B.code = '" + rulecode + "'");
					String attribute1TargetDatabaseType = getString("SELECT T.databasetype FROM BUSINESSRULE B, ATTRIBUTE A, TABLE T WHERE B.attributeid1 = A.id AND A.tableid = T.id AND B.code = '" + rulecode + "'");
					attribute1TargetDatabase = parseToTargetDatabase(attribute1TargetDatabaseName, attribute1TargetDatabaseType);
				attribute1Table = new Table(attribute1TableName, attribute1TargetDatabase);
			attribute1 = new Column(attribute1Name, attribute1Table);
			//	Attribute atrtibute2;
			//		String attribute2Name;
			//		AttributeType attribute2Type;
			//			String attribute2TypeName;
			//		Table attribute2Table;
			//			String attribute2TableName;
			//			String attribute2TableOracleTargetDatabaseID;
			//			String attribute2TableDatabasetype;
			
			switch (businessruletypecode) {
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
		
		return rule;
	}
	
	private static String getString(String query){
		ResultSet result;
		try (DatabaseConnection connection = new DatabaseConnection()) {
			result = connection.query(query);
			result.next();
			return result.getString(1);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private static int getInt(String query){
		ResultSet result;
		try (DatabaseConnection connection = new DatabaseConnection()) {
			result = connection.query(query);
			result.next();
			return result.getInt(1);
		} catch(Exception e){
			e.printStackTrace();
			return -999999999;
		}
	}
	
	private static double getDouble(String query){
		ResultSet result;
		try (DatabaseConnection connection = new DatabaseConnection()) {
			result = connection.query(query);
			result.next();
			return result.getDouble(1);
		} catch(Exception e){
			e.printStackTrace();
			return -999999999;
		}
	}
	
	private static CodeType parseToCodeType(String codeType){
		if(codeType == null || codeType.equals("")){
			return null;
		} else{
			for(CodeType cT : CodeType.values()){
				if(codeType.equals(cT.toString())){
					return cT;
				}
			}
			return null;
		}
	}
	
	private static TargetDatabase parseToTargetDatabase(String databaseName, String databaseType){
		TargetDatabase targetDatabase = null;
		
		if(databaseType.equals("ORACLE")){
			targetDatabase = new OracleTargetDatabase();
			targetDatabase.setName(databaseName);
			targetDatabase.setType(DatabaseType.ORACLE);
		}
		
		return targetDatabase;
	}
}