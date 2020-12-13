package me.Feazes.plugins.mobcash.methods;

import java.util.Random;

import org.junit.Test;

public class PlMath {
	
	public Double randoM(Double min, Double max) {
		return  max - (Math.random() * max);
	}
	public Integer random(Integer min, Integer max) {
		return  Integer.valueOf((int) (max - (Math.random() * max)));}
	
	
	public double round(Double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public double limit(Double input, Double min, Double max) {
	if (input <= min) {return min;}
	else if (input >= max) {return max;}
	else {return input;}
	}
	
	public double setRandomCash(Double Min, Double Max) {
		Double d1 = random(Min, Max);
		Double d2 = round(d1, 2);
		return limit(d2, Min, Max);}
	
	@Test
	public double random(double min, double max) {
	    return max - new Random().nextDouble() * (max);
	}
}