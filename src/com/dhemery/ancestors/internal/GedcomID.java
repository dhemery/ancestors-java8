package com.dhemery.ancestors.internal;


public class GedcomID {
	public static Integer parse(String id) {
		return Integer.parseInt(id.split("[^\\d]+")[1]);		
	}
}
