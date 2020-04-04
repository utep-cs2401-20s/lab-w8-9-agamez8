import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AminoAcidResourcesTester {

  @Test
  public void allCodons() {
    char[] rna = {'A', 'C', 'U', 'G'};
    char[] aa = {'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W'};
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        for (int k = 0; k < 4; k++) {
          String s = new String(new char[]{rna[i], rna[j], rna[k]});
          char aaOut = AminoAcidResources.getAminoAcidFromCodon(s);
          if (aaOut != '*') {
            String[] codonList = AminoAcidResources.getCodonListForAminoAcid(aaOut);
            boolean found = false;
            for (int l = 0; l < codonList.length; l++) {
              found |= (codonList[l].equals(s));
            }
            if (!found) System.err.println("Codon " + s + " not found, said AA was " + aaOut);
          }

          aaOut = AminoAcidResources.getAminoAcidFromCodon(s.toLowerCase());
          if (aaOut != '*') {
            String[] codonList = AminoAcidResources.getCodonListForAminoAcid(aaOut);
            boolean found = false;
            for (int l = 0; l < codonList.length; l++) {
              found |= (codonList[l].equals(s));
            }
            if (!found) System.err.println("Codon " + s + " not found, said AA was " + aaOut);
          }
        }
      }
    }

  }

  @Test
  public void allAAs() {

    char[] aa = {'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W'};
    for (int i = 0; i < aa.length; i++) {
      String[] codonList = AminoAcidResources.getCodonListForAminoAcid(aa[i]);
      for (int l = 0; l < codonList.length; l++) {
        if (aa[i] != AminoAcidResources.getAminoAcidFromCodon(codonList[l])) {
          System.err.println("AA " + aa[i] + " not found, said codon was " + codonList[l]);
        }
      }

      codonList = AminoAcidResources.getCodonListForAminoAcid(Character.toLowerCase(aa[i]));
      for (int l = 0; l < codonList.length; l++) {
        if (aa[i] != AminoAcidResources.getAminoAcidFromCodon(codonList[l])) {
          System.err.println("AA " + aa[i] + " not found, said codon was " + codonList[l]);
        }
      }
    }
  }

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