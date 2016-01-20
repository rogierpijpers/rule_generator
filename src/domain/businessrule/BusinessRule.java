package domain.businessrule;

import java.util.ArrayList;

import org.stringtemplate.v4.ST;

import util.RuleDTO;
import util.RuleHolder;
import data.BusinessRuleDAO;
import data.SetDAO;
import domain.businessrule.database.TargetDatabase;

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
		return RuleDTO.getAllCodesAndNames();
	}
	
	public static ArrayList<RuleHolder> getCodesAndNamesFromSet(String setName){
		return RuleDTO.getAllCodesAndNamesFromSet(setName);
	}
	
	public static ArrayList<String> getAllSetNames(){
		return SetDAO.getAllSetNames();
	}

	public static BusinessRule getDetails(String code) {
		return RuleDTO.getDetails(code);
	}
}
