package cocontravariance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaxAccountant {
    public static void prepareTaxes(Taxable t) {}
    //                              Co-variance
    public static void prepareTaxes(List<? extends Taxable> lt) {
//        lt.add(new Corporation());
//        lt.add(new Individual());
        for (Taxable taxable : lt) {
            prepareTaxes(taxable);
        }
    }

    public static void prepareTaxes(Taxable[] ta) {
        System.out.println("Adding individual...");
        ta[0] = new Individual();
//        System.out.println("Adding corporation...");
//        ta[1] = new Corporation();
        for (Taxable taxable : ta) {
            prepareTaxes(taxable);
        }
    }

    // contravariance
    public static void addIndividualClient(List<? super Individual> li) {
        Individual ind = new Individual();
        li.add(ind);
    }
    public static void main(String[] args) {
        List<Taxable> lt = new ArrayList<>(Arrays.asList(
                new Individual(),
                new Corporation(),
                new Individual()
        ));

        addIndividualClient(lt);
        prepareTaxes(lt);

        List<Individual> li = new ArrayList<>(Arrays.asList(
                new Individual(),
                new Individual(),
                new Individual()
        ));

        addIndividualClient(li);
        prepareTaxes(li);

        Individual [] ia = {
                new Individual(),
                new Individual()
        };
        prepareTaxes(ia);

        System.out.println("Type of List<Individual> is " + li.getClass().getName());
        System.out.println("Type of Individual[] is " + ia.getClass().getName());
    }
}
