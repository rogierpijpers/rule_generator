package domain.controller;

import java.util.ArrayList;

import domain.businessrule.BusinessRuleService;
import domain.businessrule.BusinessRuleServiceImpl;
import util.RuleHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UIController {
	private GeneratorController generator;
	private BusinessRuleService ruleService;
	
	public UIController() {
		generator = new GeneratorController();
		ruleService = new BusinessRuleServiceImpl();
	}
	
	public void generateBusinessRules(ObservableList<String> ruleCodes, String saveDirectory, boolean execute){
		for(String ruleCode : ruleCodes){
			generator.generateBusinessRule(ruleCode, saveDirectory, execute);
		}
	}
	
	public ObservableList<String> getAllSets(){
		ObservableList<String> result = FXCollections.observableArrayList(ruleService.getAllSetNames());
		return result;
	}
	
	public ObservableList<RuleHolder> getRuleCodesAndNames(String setName){
		ObservableList<RuleHolder> result = FXCollections.observableArrayList(ruleService.getCodesAndNamesFromSet(setName));
		return result;
	}

}
