package com.picklesgame.main;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class MiddleSquare {
	private BigInteger seed;
	private int digit;
	
	public MiddleSquare(){
		this.seed = new BigInteger(String.valueOf(System.currentTimeMillis() % 4));
		digit = countDigit(seed.toString());
	}

	public MiddleSquare(String seed){
		this.seed = new BigInteger(seed);
		digit = countDigit(seed);
	}
	
	private int countDigit(String n){
		return n.length();
	}
	
	public BigInteger nextRand(){
		BigInteger n = seed.multiply(seed);
		String result= ""+n;
		while(result.length() < digit * 2) result = "0" + result;
		int start = (int) Math.floor(digit / 2);
		int end = start + digit;
		//System.out.println(n);
		seed = new BigInteger(result.substring(start, end));
		//System.out.println(seed);
		return seed;
	}
	
	public float nextFloatRand(){
		String div = "";
		for(int i = 0; i < digit; i++){
			div += "9";
		}
		BigDecimal d = new BigDecimal(new BigInteger(div));
		BigDecimal bd = new BigDecimal(nextRand());
		//float value = bd.divide(d, 2 ,RoundingMode.HALF_UP).floatValue();
		//System.out.println(value);
		return bd.divide(d, 2 ,RoundingMode.HALF_UP).floatValue();
	}
	
}
