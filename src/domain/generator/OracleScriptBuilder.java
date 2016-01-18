package domain.generator;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.database.Column;
import domain.businessrule.rule.AttributeCompare;
import domain.businessrule.rule.AttributeList;
import domain.businessrule.rule.AttributeOther;
import domain.businessrule.rule.AttributeRange;
import domain.businessrule.rule.TupleCompare;
import domain.businessrule.rule.TupleOther;

public class OracleScriptBuilder implements ScriptBuilder {
	private TemplateBuilder templateBuilder;

	public OracleScriptBuilder() {
		templateBuilder = new OracleTemplateBuilder();
	}

	@Override
	public String createScript(BusinessRule businessRule) {
		ST template = templateBuilder.getTemplate(businessRule.getType().getCode(),
				businessRule.getType().getCodeType());

		template = fill(template, businessRule);

		return template.render();
	}
	
	private ST fill(ST template, BusinessRule businessRule){
		
		template.add("code", businessRule.getCode());
		
		switch (businessRule.getType().getCode()) {
		case "ARNG":
			AttributeRange ARNGrule = (AttributeRange) businessRule;
			Column ARNGcolumn = (Column) ARNGrule.getAttribute();
			template.add("targetTable", ARNGcolumn.getTable().getName());
			template.add("column", ARNGcolumn.getName());
			template.add("operator", ARNGrule.getOperator().getCharacter());
			template.add("minValue", ARNGrule.getMinValue());
			template.add("maxValue", ARNGrule.getMaxValue());		
			break;
		case "ACMP":
			AttributeCompare ACMPrule = (AttributeCompare) businessRule;
			Column ACMPcolumn = (Column) ACMPrule.getAttribute();
			template.add("targetTable", ACMPcolumn.getTable().getName());
			template.add("column", ACMPcolumn.getName());
			template.add("operator", ACMPrule.getOperator().getCharacter());
			template.add("value", ACMPrule.getValue());	
			break;
		case "ALIS":
			AttributeList ALISrule = (AttributeList) businessRule;
			Column ALIScolumn = (Column) ALISrule.getAttribute();
			template.add("targetTable", ALIScolumn.getTable().getName());
			template.add("column", ALIScolumn.getName());
			template.add("operator", ALISrule.getOperator().getCharacter());
			template.add("value", ALISrule.getValueStr());
			break;
		case "AOTH":
			AttributeOther AOTHrule = (AttributeOther) businessRule;
			Column AOTHcolumn = (Column) AOTHrule.getAttribute();
			template.add("targetTable", AOTHcolumn.getTable().getName());
			template.add("plsql", AOTHrule.getPlsql());
			break;
		case "TCMP":
			TupleCompare TCMPrule = (TupleCompare) businessRule;
			Column TCMPcolumn1 = (Column) TCMPrule.getAttribute1();
			Column TCMPcolumn2 = (Column) TCMPrule.getAttribute2();
			template.add("targetTable", TCMPcolumn1.getTable());
			template.add("column1", TCMPcolumn1.getName());
			template.add("operator", TCMPrule.getOperator().getCharacter());
			template.add("column2", TCMPcolumn2.getName());
			break;
		case "TOTH":
			TupleOther TOTHrule = (TupleOther) businessRule;
			Column TOTHcolumn1 = (Column) TOTHrule.getAttribute1();
			Column TOTHcolumn2 = (Column) TOTHrule.getAttribute2();
			template.add("targetTable", TOTHcolumn1.getTable());
			template.add("targetColumn1", TOTHcolumn1.getName());
			template.add("targetColumn2", TOTHcolumn2.getName());
			template.add("plsql", TOTHrule.getPlsql());
			template.add("failureMessage", TOTHrule.getFailureMessage());
			break;
		case "ICMP":
			
			break;
		case "EOTH":
			
			break;
		case "MODI":
			
			break;
		}
		
		return template;
	}
}