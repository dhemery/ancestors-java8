package com.dhemery.ancestors.gedcom;


public class GedcomID {
	public static Integer parse(String id) {
		return Integer.parseInt(id.split("[^\\d]+")[1]);		
	}
}
