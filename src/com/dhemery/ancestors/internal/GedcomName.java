package com.dhemery.ancestors.internal;

import java.util.Optional;
import java.util.StringJoiner;

import org.gedcom4j.model.PersonalName;

import com.dhemery.ancestors.Name;

public class GedcomName implements Name {
	private Optional<String> prefix;
	private String basic;
	private Optional<String> suffix;

	public GedcomName(PersonalName name) {
		prefix = Optional.ofNullable(name.prefix).map(Object::toString);
		basic = name.basic;
		suffix = Optional.ofNullable(name.suffix).map(Object::toString);
	}

	@Override
	public Optional<String> prefix() {
		return prefix;
	}

	@Override
	public String full() {
        StringJoiner joiner = new StringJoiner(" ");
        prefix.ifPresent(joiner::add);
        joiner.add(basic);
        suffix.ifPresent(joiner::add);
        return joiner.toString();
	}

	@Override
	public Optional<String> suffix() {
		return suffix;
	}

	@Override
	public String toString() {
        return basic;
	}

	@Override
	public int compareTo(Name other) {
		return this.full().compareTo(other.full());
	}
}
