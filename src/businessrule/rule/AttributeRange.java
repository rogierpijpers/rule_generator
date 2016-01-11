package businessrule.rule;

import businessrule.BusinessRule;

public class AttributeRange extends BusinessRule{

	private String code;
	private String name;
	private double minValue;
	private double maxValue;
	private Operator operator;
	private Value attribute;
}
