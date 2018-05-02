package devin.drools.util;

import org.drools.core.spi.KnowledgeHelper;

public class DroolsHelp {
    private DroolsHelp() {}

    public static void help(final KnowledgeHelper drools, final String message){
        System.out.println(message);
        System.out.println("\nrule triggered: " + drools.getRule().getName());
    }

    public static void helper(final KnowledgeHelper drools){
        System.out.println("\nrule triggered: " + drools.getRule().getName());
    }
}
