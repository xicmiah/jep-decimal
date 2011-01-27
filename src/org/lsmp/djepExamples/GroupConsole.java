/* @author rich
 * Created on 21-Mar-2005
 *
 * See LICENSE.txt for license information.
 */
package org.lsmp.djepExamples;

import org.lsmp.djep.groupJep.GroupI;
import org.lsmp.djep.groupJep.GroupJep;
import org.lsmp.djep.groupJep.groups.*;
import org.lsmp.djep.groupJep.interfaces.RingI;
import org.lsmp.djep.groupJep.values.HasComplexValueI;
import org.lsmp.djep.groupJep.values.Polynomial;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.type.Complex;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Console application with handling for abstract groups
 *
 * @author Rich Morris
 *         Created on 21-Mar-2005
 */
public class GroupConsole extends Console {
    private static final long serialVersionUID = -3097491397108691409L;

    public static void main(String args[]) {
        Console c = new GroupConsole();
        c.run(args);
    }

    public String getPrompt() {
        return "GroupJep > ";
    }

    public void initialise() {
        j = new GroupJep(new Rationals());
        j.setAllowAssignment(true);
        j.setAllowUndeclared(true);
        j.setImplicitMul(true);
        j.addStandardConstants();
        j.addStandardFunctions();
    }

    public void initialise(Group g) {
        j = new GroupJep(g);
        j.setAllowAssignment(true);
        j.setAllowUndeclared(true);
        j.setImplicitMul(true);
        j.addStandardConstants();
        j.addStandardFunctions();
    }

    public void printIntroText() {
        println("GroupJep: evaluation over abstract groups");
        printGroup();
        super.printStdHelp();
    }

    public void printGroup() {
        println("Current Group: " + ((GroupJep) j).getGroup().toString());
    }

    public void processEquation(Node node) throws ParseException {
        Object value = j.evaluate(node);
        if (value instanceof HasComplexValueI)
            println(value.toString() + "="
                    + ((HasComplexValueI) value).getComplexValue());
        else
            println(value);
    }

    public boolean testSpecialCommands(String command) {
        GroupJep gj = (GroupJep) j;
        if (!super.testSpecialCommands(command)) return false;
        String words[] = split(command);
        if (words.length == 0) return true;
        if (words[0].equals("group")) {
            if (words.length == 1) {
            } else if (words[1].equals("Z")) {
                initialise(new Integers());
            } else if (words[1].equals("Q")) {
                initialise(new Rationals());
            } else if (words[1].equals("R") && words.length == 3) {
                initialise(new BigReals(
                        Integer.parseInt(words[2]),
                        BigDecimal.ROUND_HALF_EVEN));
            } else if (words[1].equals("R") && words.length == 2) {
                initialise(new Reals());
            } else if (words[1].equals("P") && words.length == 3) {
                initialise(new PermutationGroup(
                        Integer.parseInt(words[2]))
                );
            } else if (words[1].equals("Zn") && words.length == 3) {
                initialise(new Zn(new BigInteger(words[2])));
            } else if (words[1].equals("Qu")) {
                initialise(new Quaternions());
            } else {
                println("invalid group spec " + command);
                return false;
            }
            printGroup();
            return false;
        }


        if (words[0].equals("extend")) {
            RingI ring = (RingI) gj.getGroup();

            if (words.length < 2)
                println("extend must have at least one argument");
            else if (words.length == 2) /* Add a free variable */ {
                initialise(new ExtendedFreeGroup(ring, words[1]));
            } else /* extend by an algebraic number */ {
                int deg = words.length - 3;
                Number coeffs[] = new Number[deg + 1];
                for (int i = 0; i <= deg; ++i)
                    coeffs[i] = ring.valueOf(words[words.length - i - 1]);
                Polynomial p1 = new Polynomial(ring, words[1], coeffs);

                initialise(new AlgebraicExtension(ring, p1));
            }
            printGroup();
            return false;
        }

        if (words[0].equals("setRootVal")) {
            String symbol = words[1];
            Complex val = new Complex(Double.parseDouble(words[2]), Double.parseDouble(words[3]));
            GroupI g = gj.getGroup();
            if (g instanceof FreeGroup) {
                boolean flag = ((FreeGroup) g).setRootVal(symbol, val);
                if (!flag) println("Failed to set root value, could not find symbol");
            }
            return false;
        }
        return true;
    }

    public void printHelp() {
        super.printHelp();
        println("'group'\tprints the current group");
        println("'group G'\tchanges underlying group to G");
        println("'group Z'\tintegers (arbitrary precision)");
        println("'group Q'\trationals");
        println("'group R'\treals, represented as Doubles.");
        println("'group R 3'\treals represented as BigDecimals with 3 decimal places");
        println("'group P 3'\tpermutation group on three symbols");
        println("\t[1,3,2]+[3,2,1] -> (3,1,2)");
        println("'group Zn 5'\tintegers modulo 5");
        println("'group Qu'\tQuarternions");
        println("'extend x'\textends current group by adding symbol x, i.e. a free group");
        println("\tsuch a group can be considered as the ring of polynomials");
        println("\tsimplification happens automatically");
        println("'extend t a b c'\talgebraic extensions generated by t");
        println("\twhere t is a root of the polynomial a t^2 + b t +c=0");
        println("\te.g  group extend t 1 0 1 gives complex numbers, t^2+1=0.");
        println("\tfor these groups there is a natural mapping to complex numbers and complex result is also printed.");
        println("'setRootVal t re im'\tsets the value of free variable 't' in a free group to complex number re+i im");
    }

}
