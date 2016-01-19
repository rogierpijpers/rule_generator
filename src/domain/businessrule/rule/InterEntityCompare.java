package domain.businessrule.rule;


import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleType;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.TargetDatabase;

public class InterEntityCompare extends BusinessRule {
	private Operator operator;
	private Attribute attribute1;
	private Attribute attribute2;

	public InterEntityCompare(String code, String name, BusinessRuleType businessRuleType, Operator operator, Attribute attribute1, Attribute attribute2) {
		super.setCode(code);
		super.setName(name);
		super.setType(businessRuleType);
		this.operator = operator;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
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
