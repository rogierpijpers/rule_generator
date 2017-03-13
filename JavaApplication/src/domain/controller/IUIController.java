package domain.controller;

import javafx.collections.ObservableList;
import util.RuleHolder;

/**
 * Created by Rogier on 28-2-2017.
 */
public interface IUIController {
    void generateBusinessRules(ObservableList<String> ruleCodes, String saveDirectory, boolean execute);

    ObservableList<String> getAllSets();

    ObservableList<RuleHolder> getRuleCodesAndNames(String setName);
}
