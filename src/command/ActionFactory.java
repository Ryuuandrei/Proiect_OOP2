package command;



import changepage.*;
import database.DatabaseAdd;
import database.DatabaseDelete;
import features.*;
import fileio.ActionInput;

public final class ActionFactory {
    /**
     *
     * @param actionInput the action from the input
     * @return Specific action type
     */
    public Action getAction(final ActionInput actionInput) {
        if (actionInput.getType().equals("change page")) {

            if (actionInput.getPage().equals("login")) {
                return new ChPgLogin(actionInput);
            }

            if (actionInput.getPage().equals("register")) {
                return new ChPgRegister(actionInput);
            }

            if (actionInput.getPage().equals("upgrades")) {
                return new ChPgUpgrades(actionInput);
            }

            if (actionInput.getPage().equals("movies")) {
                return new ChPgMovies(actionInput);
            }

            if (actionInput.getPage().equals("see details")) {
                return new ChPgSeeDetails(actionInput);
            }

            if (actionInput.getPage().equals("logout")) {
                return new ChPgLogout(actionInput);
            }

            return new ActionChangePage(actionInput);
        }

        if (actionInput.getType().equals("on page")) {

            if (actionInput.getFeature().equals("login")) {
                return new LoginFeature(actionInput);
            }

            if (actionInput.getFeature().equals("register")) {
                return new RegisterFeature(actionInput);
            }

            if (actionInput.getFeature().equals("search")) {
                return new Search(actionInput);
            }

            if (actionInput.getFeature().equals("filter")) {
                return new FilterFeature(actionInput);
            }

            if (actionInput.getFeature().equals("buy tokens")) {
                return new BuyTokens(actionInput);
            }

            if (actionInput.getFeature().equals("buy premium account")) {
                return new BuyPremiumAccount(actionInput);
            }

            if (actionInput.getFeature().equals("purchase")) {
                return new Purchase(actionInput);
            }

            if (actionInput.getFeature().equals("rate")) {
                return new Rate(actionInput);
            }

            if (actionInput.getFeature().equals("watch")) {
                return new Watch(actionInput);
            }

            if (actionInput.getFeature().equals("like")) {
                return new Like(actionInput);
            }

            if (actionInput.getFeature().equals("subscribe")) {
                return new Subscribe(actionInput);
            }

            return new ActionOnPage(actionInput);
        }

        if (actionInput.getType().equals("back")) {
            return new Back();
        }

        if (actionInput.getType().equals("database")) {

            if (actionInput.getFeature().equals("add")) {
                return new DatabaseAdd(actionInput);
            }

            if (actionInput.getFeature().equals("delete")) {
                return new DatabaseDelete(actionInput);
            }
            return new Database(actionInput);
        }
        return null;
    }
}
