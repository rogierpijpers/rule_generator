package domain.businessrule.rule;

import java.util.ArrayList;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleType;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.TargetDatabase;

public class TupleCompare extends BusinessRule {

	private String code;
	private String name;
	private Operator operator;
	private Attribute attribute1;
	private Attribute attribute2;

	public TupleCompare(String code, String name, Operator operator,
			BusinessRuleType businessRuleType, Attribute attribute1, Attribute attribute2) {
		super.setCode(code);
		super.setName(name);
		this.operator = operator;
		super.setType(businessRuleType);
		this.setAttribute1(attribute1);
		this.setAttribute2(attribute2);
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

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public Attribute getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(Attribute attribute1) {
		this.attribute1 = attribute1;
	}

	public Attribute getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(Attribute attribute2) {
		this.attribute2 = attribute2;
	}

	@Override
	public TargetDatabase getTargetDatabase() {
		return null;
	}


}
