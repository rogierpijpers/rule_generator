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
	private ArrayList<Attribute> attributes;

	public TupleCompare(String code, String name, Operator operator,
			BusinessRuleType businessRuleType, ArrayList<Attribute> attributes) {
		super.setCode(code);
		super.setName(name);
		this.operator = operator;
		super.setType(businessRuleType);
		attributes = new ArrayList<Attribute>();
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

	public ArrayList<Attribute> getAttribute() {
		return attributes;
	}

	public void setAttribute(ArrayList<Attribute> attribute) {
		this.attributes = attribute;
	}


	@Override
	public TargetDatabase getTargetDatabase() {
		return null;
	}

}
