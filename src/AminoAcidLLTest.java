import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AminoAcidLLTest {
    @Test
    public void aminoAcidTest1() {
        char[] expected = {'P', 'T'};
        AminoAcidLL test = AminoAcidLL.createFromRNASequence("GCGGCGGCGUGAAAGGU");
        assertArrayEquals(expected, test.aminoAcidList());
    }

    @Test
    public void aminoAcidCount1() {
        int[] expected = {3};
        AminoAcidLL test = AminoAcidLL.createFromRNASequence("AGGAAGCTGAGGGAGAGG");
        assertArrayEquals(expected, test.aminoAcidCounts());
    }

    @Test
    public void aminoAcidCount2(){
        int[] expected = {1,2};
        AminoAcidLL test = AminoAcidLL.createFromRNASequence("AAAGGCCUUUGAGAUAGAUAG");
        assertArrayEquals(expected, test.aminoAcidCounts());
    }

    @Test
    public void aminoAcidCount3(){
        int[] expected = {2,3};
        AminoAcidLL test = AminoAcidLL.createFromRNASequence("AAAGGGAUAG");
        assertArrayEquals(expected, test.aminoAcidCounts());
    }

    @Test
    public void sequence1(){
        String current = "XYZ";
        AminoAcidLL test = AminoAcidLL.createFromRNASequence("AAGGUUCCAACUGCA");
        for (int i = 0; i < current.length(); i++) {
            assertEquals(current.charAt(i), test.aminoAcid);
            test = test.next;
        }
    }

    @Test
    public void sequence2(){
        String current = "";
        AminoAcidLL test = AminoAcidLL.createFromRNASequence("GGGUUGCCAAAGGG");
        for (int i = 0; i < current.length(); i++) {
            assertEquals(current.charAt(i), test.aminoAcid);
            test = test.next;
        }
    }

    @Test
    public void sequence3(){
        AminoAcidLL head = AminoAcidLL.createFromRNASequence("UGUGGUUGCCCAUUUCCCUUACCU");
        AminoAcidLL sorted = AminoAcidLL.sort(head);
        int[] expected = {3,2,2,2,2,2};
        int[] actual = sorted.aminoAcidCounts();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void sequence4(){
        AminoAcidLL head = AminoAcidLL.createFromRNASequence("UGUGGUUGCCCAUUUCCCUUACCU");
        AminoAcidLL sorted = AminoAcidLL.sort(head);
        int[] expected = {3,1,1,3,1,1};
        int[] actual = sorted.aminoAcidCounts();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void sortTest1(){
        AminoAcidLL test = AminoAcidLL.createFromRNASequence("AAAGGG");
        test = AminoAcidLL.sort(test);
        assertEquals(true, test.isSorted());
    }

    @Test
    public void sortTest2(){
        AminoAcidLL test = AminoAcidLL.createFromRNASequence("AAAGGG");
        test = AminoAcidLL.sort(test);
        assertEquals(true, test.isSorted());
    }

}