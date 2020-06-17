package project3simse;

/*
The Fraction class defines the specifications of the Fraction variable that is one of the two
used by the BinarySearchTree. It implements Comparable<Fraction> so that the fraction that is being
inserted can be compared to the fraction of the node in question. This is implemented in the 
overriding compareTo() method.
*/
public class Fraction implements Comparable<Fraction> {
    
    String value;

    Fraction(String T) {
        value = T;
    }


    @Override
    public String toString() {
        return value;
    }

    
    @Override
    public int compareTo(Fraction t) {
        String str =t.toString();
        String comparatorValues[] =str.split("/");
        Float result = Float.valueOf(comparatorValues[0])/Float.valueOf(comparatorValues[1]);
        String[] comparorValues = value.split("/");
        Float f =Float.valueOf(comparorValues[0])/Float.valueOf(comparorValues[1]);
        return f.compareTo(result);

    }


}
