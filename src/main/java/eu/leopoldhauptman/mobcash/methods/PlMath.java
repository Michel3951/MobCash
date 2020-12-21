package eu.leopoldhauptman.mobcash.methods;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class PlMath {
	
	private static double round(double value, int places) {
	    if (places < 0) places = 0;
	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static double limit(Double input, Double min, Double max) {
		if (input < min) return min;
		else if (input > max) return max;
		else return input;
	}
	
	public static double getRandomCash(Double Min, Double Max) {
		Double d1 = random(Min, Max);
		Double d2 = round(d1, 2);
		return limit(d2, Min, Max);
	}
	
	public static double random(double min, double max) {
	    return min + new Random().nextDouble() * (max-min);
	}
}