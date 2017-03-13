package domain.businessrule;

import data.BusinessRuleDAO;
import util.RuleHolder;

import java.util.ArrayList;

/**
 * Created by Rogier on 28-2-2017.
 */
public class BusinessRuleMoved {
    public static ArrayList<RuleHolder> getAllCodesAndNames() {
        return BusinessRuleDAO.getAllCodesAndNames();
    }

    public static ArrayList<RuleHolder> getCodesAndNamesFromSet(String setName) {
        return BusinessRuleDAO.getAllCodesAndNamesFromSet(setName);
    }
}
