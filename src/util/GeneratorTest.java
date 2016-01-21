package util;

import java.util.ArrayList;

import data.BusinessRuleDAO;
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
import domain.businessrule.rule.InterEntityCompare;
import domain.businessrule.rule.Operator;
import domain.businessrule.rule.TupleCompare;
import domain.businessrule.rule.TupleOther;
import domain.generator.GeneratorService;
import domain.generator.GeneratorServiceImpl;

public class GeneratorTest {

	public static void main(String[] args){
		
		TargetDatabase targetDatabase = new OracleTargetDatabase();
		targetDatabase.setType(DatabaseType.ORACLE);
		targetDatabase.setName("target");
		
		@SuppressWarnings("unused")
		GeneratorService service = new GeneratorServiceImpl(targetDatabase);
		
		@SuppressWarnings("unused")
		BusinessRule rule = null;
		
		//rule = getARNGExample(targetDatabase);
		//rule = getACMPExample(targetDatabase);
		//rule = getALISExample(targetDatabase);
		//rule = getAOTHExample(targetDatabase);
		//rule = getTCMPExample(targetDatabase);
		//rule = getTOTHExample(targetDatabase);
		//rule = getICMPExample(targetDatabase);
		
		//rule = getEOTHExample(targetDatabase);		\\ NYI
		//rule = getMODIExample(targetDatabase);		\\ NYI
		
		//String script = service.generateBusinessRule(rule);
		
		//System.out.println(script);
		
		//service.executeScript(script);
		
		RuleDTO dto = BusinessRuleDAO.getBusinessRule("BRG_VBMG_ARNG_170");
		System.out.println(dto.getCode());
		System.out.println(dto.getName());
		
	}
	
	public static BusinessRule getARNGExample(TargetDatabase targetDatabase){
		Table table = new Table("EMPLOYEE", targetDatabase);
		
		Attribute column = new Column("SALARY", table);
		
		Operator operator = new Operator("name", "BETWEEN");
		operator.setName("between or not");
		operator.setCharacter("BETWEEN");
		
		Category category = new Category("rules");
		BusinessRuleType type = new BusinessRuleType("ARNG", "", "", category, CodeType.CONSTRAINT);
		
		BusinessRule rule = new AttributeRange("ARNG_TEST", "Testregel", "10000", "100000", operator, type, column);
		
		return rule;
	}
	
	public static BusinessRule getACMPExample(TargetDatabase targetDatabase){
		Table table = new Table("EMPLOYEE", targetDatabase);
		
		Attribute column = new Column("SALARY", table);

		Operator operator = new Operator("groter dan", ">");
		
		Category category = new Category("rules");
		BusinessRuleType type = new BusinessRuleType("ACMP", "", "", category, CodeType.CONSTRAINT);
		
		BusinessRule rule = new AttributeCompare("ACMP_TEST", "Testregel", "1000", operator, type, column);
		
		return rule;
	}
	
	public static BusinessRule getALISExample(TargetDatabase targetDatabase){
		Table table = new Table("DEPARTMENT", targetDatabase);
		
		Attribute column = new Column("DNAME", table);
		
		Operator operator = new Operator("name", "IN");
		
		Category category = new Category("rules");
		BusinessRuleType type = new BusinessRuleType("ALIS", "", "", category, CodeType.CONSTRAINT);
		
		ArrayList<String> values = new ArrayList<String>();
		values.add("Administration");
		values.add("Headquarters");
		
		BusinessRule rule = new AttributeList("ALIS_TEST", "naampie", column, operator, type, values);
		
		return rule;
	}
	
	public static BusinessRule getAOTHExample(TargetDatabase targetDatabase){
		Table table = new Table("EMPLOYEE", targetDatabase);
		
		Attribute column = new Column("SALARY", table);
		
		Category category = new Category("rules");
		BusinessRuleType type = new BusinessRuleType("AOTH", "", "", category, CodeType.CONSTRAINT);		
		
		String plsql = "substr(salary, 1,1) between '1' and '9'";
		
		BusinessRule rule = new AttributeOther("AOTH_TEST", "naampie",type, column, plsql);
		
		return rule;
	}
	
	public static BusinessRule getTCMPExample(TargetDatabase targetDatabase){
		Table table = new Table("PROJECT", targetDatabase);
		
		Attribute column1 = new Column("PNUMBER", table);
		Attribute column2 = new Column("DNUM", table);
		Operator operator = new Operator("bigger", "<");
		
		Category category = new Category("rules");
		BusinessRuleType type = new BusinessRuleType("TCMP", "", "", category, CodeType.CONSTRAINT);		
		
		BusinessRule rule = new TupleCompare("TCMP_TEST", "naampie",operator, type, column1, column2);
		
		return rule;
	}
	
	public static BusinessRule getTOTHExample(TargetDatabase targetDatabase){
		Table table = new Table("EMPLOYEE", targetDatabase);
		
		Attribute column = new Column("SALARY", table);
		
		Category category = new Category("rules");
		BusinessRuleType type = new BusinessRuleType("TOTH", "", "", category, CodeType.TRIGGER);		
		
		String plsql = ":new.BDATE < SYSDATE";
		
		BusinessRule rule = new TupleOther("TOTH_TEST", "naampie",type, column, plsql);
		rule.setFailureMessage("TOTH_MESSAGE");
		
		return rule;
	}
	
	public static BusinessRule getICMPExample(TargetDatabase targetDatabase){
		Table table1 = new Table("DEPARTMENT", targetDatabase);
		Attribute column1 = new Column("MGRSTARTDATE", table1);
		String foreignKey = "MGRSSN";
		
		Table table2 = new Table("EMPLOYEE", targetDatabase);
		Attribute column2 = new Column("BDATE", table2);
		String primaryKey = "SSN";
		
		Operator operator = new Operator("smaller than", ">");
		
		Category category = new Category("rules");
		BusinessRuleType type = new BusinessRuleType("ICMP", "", "", category, CodeType.TRIGGER);
		
		BusinessRule rule = new InterEntityCompare("ICMP_TEST", "naampie", type, operator, column2, column1, foreignKey, primaryKey);
		rule.setFailureMessage("ICMP_MESSAGE");
		
		return rule;
	}
	
	public static BusinessRule getEOTHExample(TargetDatabase targetDatabase){
		
		return null;
	}
	
	public static BusinessRule getMODIExample(TargetDatabase targetDatabase){
		
		return null;
	}
}
