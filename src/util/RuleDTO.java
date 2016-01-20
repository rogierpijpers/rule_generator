package util;

import java.util.ArrayList;

import domain.businessrule.BusinessRule;

public class RuleDTO {
	private String code;
	private String name;
	
	private String ruleTypeCode;
	
	private String failureMessage;
	
	private String minValue;
	private String maxValue;
	private String value;
	private String listValue;
	private String plsql;
	private String primaryKey;
	private String foreignKey;
	
	private String operatorName;
	private String operatorChar;
	
	private String attributeName_1;
	private String tableName_1;
	private String databaseName_1;
	private String databaseType_1;
	
	private String attributeName_2;
	private String tableName_2;
	private String databaseName_2;
	private String databaseType_2;
	


	public RuleDTO(String code, String name, String ruleTypeCode, String failureMessage, String minValue,
			String maxValue, String value, String listValue, String plsql, String primaryKey, String foreignKey, String operatorName, String operatorChar,
			String attributeName_1, String tableName_1, String databaseName_1, String databaseType_1,
			String attributeName_2, String tableName_2, String databaseName_2, String databaseType_2) {
		super();
		this.code = code;
		this.name = name;
		this.ruleTypeCode = ruleTypeCode;
		this.failureMessage = failureMessage;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.value = value;
		this.listValue = listValue;
		this.plsql = plsql;
		this.primaryKey = primaryKey;
		this.foreignKey = foreignKey;
		this.operatorName = operatorName;
		this.operatorChar = operatorChar;
		this.attributeName_1 = attributeName_1;
		this.tableName_1 = tableName_1;
		this.databaseName_1 = databaseName_1;
		this.databaseType_1 = databaseType_1;
		this.attributeName_2 = attributeName_2;
		this.tableName_2 = tableName_2;
		this.databaseName_2 = databaseName_2;
		this.databaseType_2 = databaseType_2;
	}

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

	public String getRuleTypeCode() {
		return ruleTypeCode;
	}

	public void setRuleTypeCode(String ruleTypeCode) {
		this.ruleTypeCode = ruleTypeCode;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getListValue() {
		return listValue;
	}

	public void setListValue(String listValue) {
		this.listValue = listValue;
	}

	public String getPlsql() {
		return plsql;
	}

	public void setPlsql(String plsql) {
		this.plsql = plsql;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorChar() {
		return operatorChar;
	}

	public void setOperatorChar(String operatorChar) {
		this.operatorChar = operatorChar;
	}

	public String getAttributeName_1() {
		return attributeName_1;
	}

	public void setAttributeName_1(String attributeName_1) {
		this.attributeName_1 = attributeName_1;
	}

	public String getTableName_1() {
		return tableName_1;
	}

	public void setTableName_1(String tableName_1) {
		this.tableName_1 = tableName_1;
	}

	public String getDatabaseName_1() {
		return databaseName_1;
	}

	public void setDatabaseName_1(String databaseName_1) {
		this.databaseName_1 = databaseName_1;
	}

	public String getDatabaseType_1() {
		return databaseType_1;
	}

	public void setDatabaseType_1(String databaseType_1) {
		this.databaseType_1 = databaseType_1;
	}

	public String getAttributeName_2() {
		return attributeName_2;
	}

	public void setAttributeName_2(String attributeName_2) {
		this.attributeName_2 = attributeName_2;
	}

	public String getTableName_2() {
		return tableName_2;
	}

	public void setTableName_2(String tableName_2) {
		this.tableName_2 = tableName_2;
	}

	public String getDatabaseName_2() {
		return databaseName_2;
	}

	public void setDatabaseName_2(String databaseName_2) {
		this.databaseName_2 = databaseName_2;
	}

	public String getDatabaseType_2() {
		return databaseType_2;
	}

	public void setDatabaseType_2(String databaseType_2) {
		this.databaseType_2 = databaseType_2;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

	
	
	
	

}
