package com.picklesgame.main;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class LinearCongruentialGenerator {
	//Change these number for different effect
	private BigInteger seed = new BigInteger("1");
	private BigInteger a = new BigInteger("1103515245");
	private BigInteger c = new BigInteger("12345");
	private BigInteger m = new BigInteger("2147483648"); //2^31
	
	public LinearCongruentialGenerator(String seed, String a, String c, String m){
		this.seed = new BigInteger(seed);
		this.a =  new BigInteger(a);
		this.m =  new BigInteger(m);
		this.c =  new BigInteger(c);
		
	}
	public LinearCongruentialGenerator(String seed){
		this.seed = new BigInteger(seed);
	}
	
	public BigInteger nextRand(){
		BigInteger l = a.multiply(seed).add(c).mod(m);
		//BigInteger x = a.multiply(seed);
		//BigInteger y = x.add(c);
		//BigInteger mod = y.mod(m);
		seed = l;
		//System.out.println(seed);
		return seed;
	}
	
	public float nextFloatRand(){
		BigDecimal rand = new BigDecimal(nextRand());
		BigDecimal dm = new BigDecimal(m);
		//System.out.println(m.toString());
		return rand.divide(dm, 2, RoundingMode.HALF_UP).floatValue();
	}
	
	
}
