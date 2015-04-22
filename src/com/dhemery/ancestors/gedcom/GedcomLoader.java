package com.dhemery.ancestors.gedcom;

import com.dhemery.ancestors.genealogy.FamilyTree;
import org.gedcom4j.parser.GedcomParser;
import org.gedcom4j.parser.GedcomParserException;

import java.io.BufferedInputStream;
import java.io.IOException;

import static java.lang.String.format;

public class GedcomLoader {
    public static final FamilyTree TREE = tree();
    public static final String ANCEST0RS_FILE = "/ancestors.ged";


    private static FamilyTree tree() {
        BufferedInputStream stream = gedcomStream(ANCEST0RS_FILE);
        GedcomParser parser = new GedcomParser();
        try {
            parser.load(stream);
        } catch (IOException cause) {
            throw new RuntimeException(format("Could not read the GEDCOM file %s from classpath", ANCEST0RS_FILE), cause);
        } catch (GedcomParserException cause) {
            throw new RuntimeException(format("Could not parse the GEDCOM file %s from classpath", ANCEST0RS_FILE), cause);
        }
        return new GedcomFamilyTree(parser.gedcom);
    }

    private static BufferedInputStream gedcomStream(String filename) {
        return new BufferedInputStream(GedcomLoader.class.getResourceAsStream(filename));
    }
}
