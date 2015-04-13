package com.dhemery.ancestors.gedcom;

import com.dhemery.ancestors.genealogy.FamilyTree;
import org.gedcom4j.parser.GedcomParser;
import org.gedcom4j.parser.GedcomParserException;

import java.io.BufferedInputStream;
import java.io.IOException;

public class GedcomLoader {
    public static final FamilyTree TREE;

    static {
        try {
            TREE = tree();
        } catch (IOException | GedcomParserException cause) {
            throw new RuntimeException(cause);
        }
    }

    private static FamilyTree tree() throws IOException, GedcomParserException {
        GedcomParser parser = new GedcomParser();
        parser.load(gedcomStream("/ancestors.ged"));
        return new GedcomFamilyTree(parser.gedcom);
    }

    private static BufferedInputStream gedcomStream(String filename) {
        return new BufferedInputStream(GedcomLoader.class.getResourceAsStream(filename));
    }
}
