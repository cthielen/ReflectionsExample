package ReflectionsExample.loggers;

import ReflectionsExample.annotations.Logger;
import ReflectionsExample.entities.FundItem;

@Logger
public class FundItemLogger implements EntityLogger {

    @Override
    public String serialize(Object o) {
        FundItem item = (FundItem)o;

        return "Fund: " + item.getDescription() + " (" + item.getAmount() + ")";
    }

    @Override
    public boolean supports(Object o) {
        if(o instanceof FundItem) {
            return true;
        }
        return false;
    }
}
