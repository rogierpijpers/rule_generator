package domain.generator;

import org.stringtemplate.v4.ST;

import domain.businessrule.BusinessRule;
import domain.businessrule.database.Column;
import domain.businessrule.rule.AttributeCompare;
import domain.businessrule.rule.AttributeList;
import domain.businessrule.rule.AttributeOther;
import domain.businessrule.rule.AttributeRange;
import domain.businessrule.rule.InterEntityCompare;
import domain.businessrule.rule.TupleCompare;
import domain.businessrule.rule.TupleOther;

public class OracleScriptBuilder implements ScriptBuilder {
	private TemplateBuilder templateBuilder;

	public OracleScriptBuilder() {
		templateBuilder = new OracleTemplateBuilder();
	}

	@Override
	public String createScript(BusinessRule businessRule) {
		ST template = templateBuilder.getTemplate(businessRule.getType().getCode(), businessRule.getType().getCodeType());

		template = fill(template, businessRule);

		return template.render();
	}
	
	private ST fill(ST template, BusinessRule businessRule){		
		template.add("code", businessRule.getCode());		
		switch (businessRule.getType().getCode()) {
		case "ARNG":
			template =  fillARNG(template, businessRule);		
			break;
		case "ACMP":
			template = 	fillACMP(template, businessRule);
			break;
		case "ALIS":
			template = 	fillALIS(template, businessRule);
			break;
		case "AOTH":
			template = 	fillAOTH(template, businessRule);
			break;
		case "TCMP":
			template = 	fillTCMP(template, businessRule);
			break;
		case "TOTH":
			template = 	fillTOTH(template, businessRule);
			break;
		case "ICMP":
			template = 	fillICMP(template, businessRule);
			break;
		case "EOTH":
			template =  fillEOTH(template, businessRule);	
			break;
		case "MODI":
			template = 	fillMODI(template, businessRule);
			break;
		}	
		return template;
	}
	
	private ST fillARNG(ST template, BusinessRule businessRule){
		AttributeRange ARNGrule = (AttributeRange) businessRule;
		Column ARNGcolumn = (Column) ARNGrule.getAttribute();
		template.add("targetTable", ARNGcolumn.getTable().getName());
		template.add("column", ARNGcolumn.getName());
		template.add("operator", ARNGrule.getOperator().getCharacter());
		template.add("minValue", ARNGrule.getMinValue());
		template.add("maxValue", ARNGrule.getMaxValue());
		return template;
	}
	
	private ST fillACMP(ST template, BusinessRule businessRule){
		AttributeCompare ACMPrule = (AttributeCompare) businessRule;
		Column ACMPcolumn = (Column) ACMPrule.getAttribute();
		template.add("targetTable", ACMPcolumn.getTable().getName());
		template.add("column", ACMPcolumn.getName());
		template.add("operator", ACMPrule.getOperator().getCharacter());
		template.add("value", ACMPrule.getValue());	
		return template;
	}
	
	private ST fillALIS(ST template, BusinessRule businessRule){
		AttributeList ALISrule = (AttributeList) businessRule;
		Column ALIScolumn = (Column) ALISrule.getAttribute();
		template.add("targetTable", ALIScolumn.getTable().getName());
		template.add("column", ALIScolumn.getName());
		template.add("operator", ALISrule.getOperator().getCharacter());
		template.add("value", ALISrule.getValueStr());
		return template;
	}
	
	private ST fillAOTH(ST template, BusinessRule businessRule){
		AttributeOther AOTHrule = (AttributeOther) businessRule;
		Column AOTHcolumn = (Column) AOTHrule.getAttribute();
		template.add("targetTable", AOTHcolumn.getTable().getName());
		template.add("plsql", AOTHrule.getPlsql());
		return template;
	}
	
	private ST fillTCMP(ST template, BusinessRule businessRule){
		TupleCompare TCMPrule = (TupleCompare) businessRule;
		Column TCMPcolumn1 = (Column) TCMPrule.getAttribute1();
		Column TCMPcolumn2 = (Column) TCMPrule.getAttribute2();
		template.add("targetTable", TCMPcolumn1.getTable().getName());
		template.add("column1", TCMPcolumn1.getName());
		template.add("operator", TCMPrule.getOperator().getCharacter());
		template.add("column2", TCMPcolumn2.getName());
		return template;
	}
	
	private ST fillTOTH(ST template, BusinessRule businessRule){
		TupleOther TOTHrule = (TupleOther) businessRule;
		Column TOTHcolumn1 = (Column) TOTHrule.getAttribute1();
		template.add("targetTable", TOTHcolumn1.getTable().getName());
		template.add("plsql", TOTHrule.getPlsql());
		template.add("failureMessage", TOTHrule.getFailureMessage());
		return template;
	}
	
	private ST fillICMP(ST template, BusinessRule businessRule){
		InterEntityCompare ICMPrule = (InterEntityCompare) businessRule;
		Column ICMPcolumn1 = (Column) ICMPrule.getAttribute1();
		Column ICMPcolumn2 = (Column) ICMPrule.getAttribute2();
		template.add("targetTable", ICMPcolumn1.getTable().getName());
		template.add("targetColumn", ICMPcolumn1.getName());
		template.add("column2", ICMPcolumn2.getName());
		template.add("table2", ICMPcolumn2.getTable().getName());
		template.add("table2_pk", ICMPrule.getPrimaryKey());
		template.add("targetTable_fk", ICMPrule.getForeignKey());
		template.add("operator", ICMPrule.getOperator().getCharacter());
		template.add("failureMessage", ICMPrule.getFailureMessage());
		return template;
	}
	
	private ST fillEOTH(ST template, BusinessRule businessRule){
		
		return template;
	}
	
	private ST fillMODI(ST template, BusinessRule businessRule){
		
		return template;
	}
}