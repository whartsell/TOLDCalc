package net.willshouse.planner.models;

/**
 * Created by whartsell on 1/23/2015.
 */
public class Validators {

    public static boolean isBetween(Object value, int min, int max) {
        Integer weight = null;

        try {
            if (value != null) {
                if (value instanceof String) {
                    weight = Integer.parseInt((String) value);
                } else if (value instanceof Integer) {
                    weight = (Integer) value;
                } else if (value instanceof Double) {
                    weight = ((Double) value).intValue();
                }
                if (weight != null) {
                    return (weight >= 30000 && weight <= 50000);
                } else return false;
            } else return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
