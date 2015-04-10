package com.dhemery.ancestors.internal;

import java.util.Optional;

import org.gedcom4j.model.PersonalName;

import com.dhemery.ancestors.Name;

public class GedcomName implements Name {
	private String basicName;
	private Optional<String> prefix;
	private Optional<String> givenName;
	private Optional<String> surnamePrefix;
	private Optional<String> surname;
	private Optional<String> suffix;

	public GedcomName(PersonalName name) {
		basicName = name.basic;
		prefix = Optional.ofNullable(name.prefix).map(Object::toString);
		givenName = Optional.ofNullable(name.givenName).map(Object::toString);
		surnamePrefix = Optional.ofNullable(name.surnamePrefix).map(Object::toString);
		surname = Optional.ofNullable(name.surname).map(Object::toString);
		suffix = Optional.ofNullable(name.suffix).map(Object::toString);
	}

	@Override
	public Optional<String> prefix() {
		return prefix;
	}

	@Override
	public Optional<String> givenName() {
		return givenName;
	}

	@Override
	public Optional<String> surnamePrefix() {
		return surnamePrefix;
	}

	@Override
	public Optional<String> surname() {
		return surname;
	}

	@Override
	public Optional<String> suffix() {
		return suffix;
	}

	@Override
	public String toString() {
		return basicName;
	}
}
