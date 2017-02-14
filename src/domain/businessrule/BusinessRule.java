package domain.businessrule;

import java.util.ArrayList;


import util.RuleDTO;
import util.RuleHolder;
import data.BusinessRuleDAO;
import data.SetDAO;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;
import domain.businessrule.database.DatabaseType;
import domain.businessrule.database.TargetDatabase;
import domain.businessrule.database.Table;
import domain.businessrule.rule.AttributeCompare;
import domain.businessrule.rule.AttributeList;
import domain.businessrule.rule.AttributeOther;
import domain.businessrule.rule.AttributeRange;
import domain.businessrule.rule.InterEntityCompare;
import domain.businessrule.rule.Operator;
import domain.businessrule.rule.TupleCompare;
import domain.businessrule.rule.TupleOther;

public abstract class BusinessRule {

	private String code;
	private String name;
	private String failureMessage;
	private BusinessRuleType type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	public BusinessRuleType getType() {
		return type;
	}

	public void setType(BusinessRuleType type) {
		this.type = type;
	}

	// ---- fill templates for generation

	public abstract TargetDatabase getTargetDatabase();

	// ---- for retrieving from database

	public static ArrayList<RuleHolder> getAllCodesAndNames() {
		return BusinessRuleDAO.getAllCodesAndNames();
	}

	public static BusinessRule getDetails(String code) {
		RuleDTO dto = BusinessRuleDAO.getBusinessRule(code);
		
		switch(dto.getRuleTypeCode()){
		case "ARNG":
				return getARNGrule(dto);	
		case "ACMP":
				return getACMPrule(dto);
		case "ALIS":
				return getALISrule(dto);
		case "AOTH":
				return getAOTHrule(dto);
		case "TCMP":
				return getTCMPrule(dto);
		case "TOTH":
				return getTOTHrule(dto);
		case "ICMP":
				return getICMPrule(dto);
		case "EOTH":
				return getEOTHrule(dto);
		case "MODI":
				return getMODIrule(dto);
		}
		return null;	
	}

	public static ArrayList<RuleHolder> getCodesAndNamesFromSet(String setName) {
		return BusinessRuleDAO.getAllCodesAndNamesFromSet(setName);
	}
	
	
	
	public static ArrayList<String> getAllSetNames(){
		return SetDAO.getAllSetNames();
	}
	
	private static BusinessRule getARNGrule(RuleDTO dto){
		TargetDatabase targetDatabase = new TargetDatabase();
		targetDatabase.setName(dto.getDatabaseName_1());
		targetDatabase.setType(DatabaseType.ORACLE);
		
		Table table = new Table(dto.getTableName_1(), targetDatabase);
		
		Attribute column = new Column(dto.getAttributeName_1(), table);
		
		Operator operator = new Operator(dto.getOperatorName(), dto.getOperatorChar());
		
		Category category = new Category(dto.getCategoryName());
		CodeType codeType = null;
		if(dto.getCodeType().equals("CONSTRAINT")){
			codeType = CodeType.CONSTRAINT;
		}else if(dto.getCodeType().equals("TRIGGER")){
			codeType = CodeType.TRIGGER;
		}
		
		BusinessRuleType type = new BusinessRuleType(dto.getRuleTypeCode(), "", "", category, codeType);
		
		BusinessRule rule = new AttributeRange(dto.getCode(), dto.getName(), dto.getMinValue(), dto.getMaxValue(), operator, type, column);
		
		return rule;
	}
	
	private static BusinessRule getACMPrule(RuleDTO dto){
		TargetDatabase targetDatabase = new TargetDatabase();
		targetDatabase.setName(dto.getDatabaseName_1());
		targetDatabase.setType(DatabaseType.ORACLE);
		
		Table table = new Table(dto.getTableName_1(), targetDatabase);
		
		Attribute column = new Column(dto.getAttributeName_1(), table);
		
		Operator operator = new Operator(dto.getOperatorName(), dto.getOperatorChar());
		
		Category category = new Category(dto.getCategoryName());
		CodeType codeType = null;
		if(dto.getCodeType().equals("CONSTRAINT")){
			codeType = CodeType.CONSTRAINT;
		}else if(dto.getCodeType().equals("TRIGGER")){
			codeType = CodeType.TRIGGER;
		}
		
		BusinessRuleType type = new BusinessRuleType(dto.getRuleTypeCode(), "", "", category, codeType);
		
		BusinessRule rule = new AttributeCompare(dto.getCode(), dto.getName(), dto.getValue(), operator, type, column);
		
		return rule;
	}
	
	private static BusinessRule getALISrule(RuleDTO dto){
		TargetDatabase targetDatabase = new TargetDatabase();
		targetDatabase.setName(dto.getDatabaseName_1());
		targetDatabase.setType(DatabaseType.ORACLE);
		
		Table table = new Table(dto.getTableName_1(), targetDatabase);
		
		Attribute column = new Column(dto.getAttributeName_1(), table);
		
		Operator operator = new Operator(dto.getOperatorName(), dto.getOperatorChar());
		
		Category category = new Category(dto.getCategoryName());
		CodeType codeType = null;
		if(dto.getCodeType().equals("CONSTRAINT")){
			codeType = CodeType.CONSTRAINT;
		}else if(dto.getCodeType().equals("TRIGGER")){
			codeType = CodeType.TRIGGER;
		}
		
		BusinessRuleType type = new BusinessRuleType(dto.getRuleTypeCode(), "", "", category, codeType);
		
		ArrayList<String> values = dto.getListValue();
		
		BusinessRule rule = new AttributeList(dto.getCode(), dto.getName(), column, operator, type, values);
		
		return rule;
	}
	
	private static BusinessRule getAOTHrule(RuleDTO dto){
		TargetDatabase targetDatabase = new TargetDatabase();
		targetDatabase.setName(dto.getDatabaseName_1());
		targetDatabase.setType(DatabaseType.ORACLE);
		
		Table table = new Table(dto.getTableName_1(), targetDatabase);
		
		Attribute column = new Column(dto.getAttributeName_1(), table);
		
		Category category = new Category(dto.getCategoryName());
		CodeType codeType = null;
		if(dto.getCodeType().equals("CONSTRAINT")){
			codeType = CodeType.CONSTRAINT;
		}else if(dto.getCodeType().equals("TRIGGER")){
			codeType = CodeType.TRIGGER;
		}
		
		BusinessRuleType type = new BusinessRuleType(dto.getRuleTypeCode(), "", "", category, codeType);	
		
		String plsql = dto.getPlsql();
		
		BusinessRule rule = new AttributeOther(dto.getCode(), dto.getName(), type, column, plsql);
		
		return rule;
	}
	
	private static BusinessRule getTCMPrule(RuleDTO dto){
		TargetDatabase targetDatabase1 = new TargetDatabase();
		targetDatabase1.setName(dto.getDatabaseName_1());
		targetDatabase1.setType(DatabaseType.ORACLE);
		
		Table table1 = new Table(dto.getTableName_1(), targetDatabase1);
		
		Attribute column1 = new Column(dto.getAttributeName_1(), table1);
		
		TargetDatabase targetDatabase2 = new TargetDatabase();
		targetDatabase2.setName(dto.getDatabaseName_2());
		targetDatabase2.setType(DatabaseType.ORACLE);
		
		Table table2 = new Table(dto.getTableName_2(), targetDatabase2);
		
		Attribute column2 = new Column(dto.getAttributeName_2(), table2);
		
		Operator operator = new Operator(dto.getOperatorName(), dto.getOperatorChar());
		
		Category category = new Category(dto.getCategoryName());
		CodeType codeType = null;
		if(dto.getCodeType().equals("CONSTRAINT")){
			codeType = CodeType.CONSTRAINT;
		}else if(dto.getCodeType().equals("TRIGGER")){
			codeType = CodeType.TRIGGER;
		}
		
		BusinessRuleType type = new BusinessRuleType(dto.getRuleTypeCode(), "", "", category, codeType);
		BusinessRule rule = new TupleCompare(dto.getCode(), dto.getName(), operator, type, column1, column2);
		
		return rule;
	}
	
	private static BusinessRule getTOTHrule(RuleDTO dto){
		TargetDatabase targetDatabase = new TargetDatabase();
		targetDatabase.setName(dto.getDatabaseName_1());
		targetDatabase.setType(DatabaseType.ORACLE);
		
		Table table = new Table(dto.getTableName_1(), targetDatabase);
		
		Attribute column = new Column(dto.getAttributeName_1(), table);
		
		String plsql = dto.getPlsql();
		
		
		Category category = new Category(dto.getCategoryName());
		CodeType codeType = null;
		if(dto.getCodeType().equals("CONSTRAINT")){
			codeType = CodeType.CONSTRAINT;
		}else if(dto.getCodeType().equals("TRIGGER")){
			codeType = CodeType.TRIGGER;
		}
		BusinessRuleType type = new BusinessRuleType(dto.getRuleTypeCode(), "", "", category, codeType);
		BusinessRule rule = new TupleOther(dto.getCode(), dto.getName(),type, column, plsql);
		rule.setFailureMessage(dto.getFailureMessage());
		
		return rule;
	}
	
	private static BusinessRule getICMPrule(RuleDTO dto){
		TargetDatabase targetDatabase1 = new TargetDatabase();
		targetDatabase1.setName(dto.getDatabaseName_1());
		targetDatabase1.setType(DatabaseType.ORACLE);
		
		Table table1 = new Table(dto.getTableName_1(), targetDatabase1);
		
		Attribute column1 = new Column(dto.getAttributeName_1(), table1);
		
		TargetDatabase targetDatabase2 = new TargetDatabase();
		targetDatabase2.setName(dto.getDatabaseName_2());
		targetDatabase2.setType(DatabaseType.ORACLE);
		
		Table table2 = new Table(dto.getTableName_2(), targetDatabase2);
		
		Attribute column2 = new Column(dto.getAttributeName_2(), table2);
		
		Operator operator = new Operator(dto.getOperatorName(), dto.getOperatorChar());
		
		Category category = new Category(dto.getCategoryName());
		CodeType codeType = null;
		if(dto.getCodeType().equals("CONSTRAINT")){
			codeType = CodeType.CONSTRAINT;
		}else if(dto.getCodeType().equals("TRIGGER")){
			codeType = CodeType.TRIGGER;
		}
		
		BusinessRuleType type = new BusinessRuleType(dto.getRuleTypeCode(), "", "", category, codeType);
		BusinessRule rule = new InterEntityCompare(dto.getCode(), dto.getName(), type, operator, column1, column2, dto.getPrimaryKey(), dto.getForeignKey());
		rule.setFailureMessage(dto.getFailureMessage());
		
		return rule;
	}
	
	private static BusinessRule getEOTHrule(RuleDTO dto){
		
		return null;
	}
	
	private static BusinessRule getMODIrule(RuleDTO dto){
		
		return null;
	}

}
