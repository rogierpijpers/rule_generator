package domain.businessrule.rule;

import java.util.ArrayList;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.BusinessRuleType;
import domain.businessrule.database.Attribute;
import domain.businessrule.database.Column;
import domain.businessrule.database.TargetDatabase;

public class TupleOther extends BusinessRule {
	private Attribute attribute1;
	private String plsql;

	public TupleOther(String code, String name,
			BusinessRuleType businessRuleType, Attribute attribute1, String plsql) {
		super.setCode(code);
		super.setName(name);
		super.setType(businessRuleType);
		this.attribute1 = attribute1;
		this.plsql = plsql;
	}


	public Attribute getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(Attribute attribute1) {
		this.attribute1 = attribute1;
	}


	public String getPlsql() {
		return plsql;
	}

	public void setPlsql(String plsql) {
		this.plsql = plsql;
	}

	@Override
	public TargetDatabase getTargetDatabase() {
		Column column = (Column) this.getAttribute1();
		return column.getTable().getTargetDatabase();
	}

}
