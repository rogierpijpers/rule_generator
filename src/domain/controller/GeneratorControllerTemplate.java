package domain.controller;

import domain.businessrule.BusinessRule;
import domain.generator.GeneratorService;

/**
 * Created by Rogier on 14-2-2017.
 */
public abstract class GeneratorControllerTemplate {
    protected GeneratorService service;

    public BusinessRule getRuleAndCreateService(String ruleCode){
        BusinessRule businessRule = getBusinessRuleFromDatabase(ruleCode);
        instantiateGeneratorService(businessRule);
        return businessRule;
    }

    protected abstract BusinessRule getBusinessRuleFromDatabase(String ruleCode);
    protected abstract void instantiateGeneratorService(BusinessRule businessRule);
}
