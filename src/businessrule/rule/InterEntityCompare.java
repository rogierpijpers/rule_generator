package businessrule.rule;

import java.util.ArrayList;

import businessrule.BusinessRule;

public class InterEntityCompare extends BusinessRule{

	private String code;
	private String name;
	private Operator operator;
	private ArrayList<Value> attributes = new ArrayList<>();
}
