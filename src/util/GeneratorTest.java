package util;

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
import domain.businessrule.rule.AttributeRange;
import domain.businessrule.rule.Operator;
import domain.generator.GeneratorService;
import domain.generator.GeneratorServiceImpl;

public class GeneratorTest {

	public static void main(String[] args){
		
		TargetDatabase targetDatabase = new OracleTargetDatabase();
		targetDatabase.setType(DatabaseType.ORACLE);
		targetDatabase.setName("target");
		
		GeneratorService service = new GeneratorServiceImpl(targetDatabase);
		
		// ARNG example
		BusinessRule ARNGrule = getARNGExample(targetDatabase);
		
		
		String ARNGscript = service.generateBusinessRule(ARNGrule);	
		System.out.println(ARNGscript);
		
		service.executeScript(ARNGscript); // ONGELDIGE ALTER TABLE OPTIE !!!
		
		// service.writeFile(ARNGscript, ARNGrule.getCode(), "C:\\Program Files", "sql");
		
		// ACMP example
		/*
		BusinessRule ACMPrule = getACMPExample(targetDatabase);
				
		String ACMPscript = service.generateBusinessRule(ACMPrule);	
		System.out.println(ACMPscript);
		*/
		
	}
	
	public static BusinessRule getARNGExample(TargetDatabase targetDatabase){
		Table table = new Table("EMPLOYEE", targetDatabase);
		
		Attribute column = new Column("SALARY", table);
		
		Operator operator = new Operator();
		operator.setName("between or not");
		operator.setCharacter("BETWEEN");
		
		Category category = new Category("rules");
		BusinessRuleType type = new BusinessRuleType("ARNG", "", "", category, CodeType.CONSTRAINT);
		
		BusinessRule rule = new AttributeRange("RULE_CODE_FOR_TESTING", "Testregel", 10000, 100000, operator, column);
		rule.setType(type);
		
		return rule;
	}
	
	public static BusinessRule getACMPExample(TargetDatabase targetDatabase){
		return null;
	}
}
