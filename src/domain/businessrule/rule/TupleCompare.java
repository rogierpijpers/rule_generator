package domain.businessrule.rule;

import java.util.ArrayList;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;
import domain.businessrule.database.TargetDatabase;

public class TupleCompare extends BusinessRule {

	private String code;
	private String name;
	private Operator operator;
	private ArrayList<Attribute> attributes;

	public TupleCompare(String code, String name, Operator operator, ArrayList<Attribute> attributes) {
		super.setCode(code);
		super.setName(name);
		this.operator = operator;
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

	// ---- fill templates for generation
		@Override
		public ST fillTemplate(ST ruleTemplate) {

			return null;
		}

		@Override
		public TargetDatabase getTargetDatabase() {
			return null;
		}

}
