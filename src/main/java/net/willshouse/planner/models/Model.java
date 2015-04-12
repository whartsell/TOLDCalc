package net.willshouse.planner.models;

/**
 * Created by whartsell on 1/23/15.
 */
public abstract class Model {

    public static boolean isBetween(Object value, int min, int max) {
        Integer weight = null;

        try {
            if (value != null) {
                if (value instanceof String && !((String) value).isEmpty()) {
                    weight = Integer.parseInt((String) value);
                } else if (value instanceof Integer) {
                    weight = (Integer) value;
                } else if (value instanceof Double) {
                    weight = ((Double) value).intValue();
                }
                if (weight != null) {
                    return (weight >= min && weight <= max);
                } else return false;
            } else return false;

        } catch (Exception e) {
            System.out.println("I ate it");
            //e.printStackTrace();
            return false;
        }
    }


    public static boolean isBetween(Object value, Double min, Double max) {
        Double weight = null;

        try {
            if (value != null) {
                if (value instanceof String && !((String) value).isEmpty()) {
                    weight = Double.parseDouble((String) value);
                } else if (value instanceof Integer) {
                    weight = (Double) value;
                } else if (value instanceof Double) {
                    weight = (Double) value;
                }
                if (weight != null) {
                    return (weight >= min && weight <= max);
                } else return false;
            } else return false;

        } catch (Exception e) {
            System.out.println("I ate it here");
            //e.printStackTrace();
            return false;
        }
    }
}
