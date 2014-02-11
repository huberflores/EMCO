
package mc.ut.ee.emco.reasoner.controller;

import mc.ut.ee.emco.reasoner.comparator.FuzzyAND;
import mc.ut.ee.emco.reasoner.comparator.FuzzyNOT;
import mc.ut.ee.emco.reasoner.comparator.FuzzyOR;
import mc.ut.ee.emco.reasoner.rules.FuzzyTerm;




public class FuzzyOp {

    public static double and(double a, double b) {
        return Math.min(a, b);
    }

    public static FuzzyTerm and(FuzzyTerm a, FuzzyTerm b) {
        return new FuzzyAND(a, b);
    }

    public static FuzzyTerm or(FuzzyTerm a, FuzzyTerm b) {
        return new FuzzyOR(a, b);
    }

    public static FuzzyTerm not(FuzzyTerm a) {
        return new FuzzyNOT(a);
    }

    public static double or(double a, double b) {
        return Math.max(a, b);
    }

    public static double not(double a) {
        return 1 - a;
    }
}
