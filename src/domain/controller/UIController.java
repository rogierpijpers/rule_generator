package domain.controller;

import java.util.ArrayList;

import util.RuleHolder;
import javafx.collections.ObservableList;

public class UIController {
	private GeneratorController generator;
	
	public UIController() {
		generator = new GeneratorController();
	}
	
	public void generateBusinessRules(ObservableList<String> ruleCodes, String saveDirectory, boolean execute){
		for(String ruleCode : ruleCodes){
			generator.generateBusinessRule(ruleCode, saveDirectory, execute);
		}
	}
	
	public ObservableList<String> getAllSets(){
		
		return null;
	}
	
	public ObservableList<RuleHolder> getRuleCodesAndNames(String setName){
		
		return null;
	}

}
