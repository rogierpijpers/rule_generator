package domain.businessrule.rule;

import domain.businessrule.BusinessRuleType;
import domain.businessrule.database.Attribute;

import java.util.ArrayList;

public class AttributeListParameterObject {
    private final String code;
    private final String name;
    private final Attribute attribute;
    private final Operator operator;
    private final BusinessRuleType businessRuleType;
    private final ArrayList<String> value;

    public AttributeListParameterObject(String code, String name, Attribute attribute, Operator operator, BusinessRuleType businessRuleType, ArrayList<String> value) {
        this.code = code;
        this.name = name;
        this.attribute = attribute;
        this.operator = operator;
        this.businessRuleType = businessRuleType;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public Operator getOperator() {
        return operator;
    }

    public BusinessRuleType getBusinessRuleType() {
        return businessRuleType;
    }

    public ArrayList<String> getValue() {
        return value;
    }
}
