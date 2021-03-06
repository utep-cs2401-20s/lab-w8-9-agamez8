class AminoAcidLL{
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL(){

  }


  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  AminoAcidLL(String inCodon){
    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    counts = new int[codons.length];
    incrementCodons(inCodon);
    next = null;
  }

  //increments codons
  private void incrementCodons(String inCodon) {
    for (int i = 0; i < codons.length; i++) {
      if (codons[i].equals(inCodon)) {
        counts[i]++;
      }
    }
  }
  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  private void addCodon(String inCodon){
    if(aminoAcid == AminoAcidResources.getAminoAcidFromCodon(codons)) {
      incrementCodons(codons);
    }

    if(next != null) {
      next.addCodon(codons);
    }

    else {
      next = AminoAcidLL(codons);
    }

  }


  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){
    return counts[0];
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){
    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    int diff = 0;
    for(int i=0; i<codons.length; i++){
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts. 
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList){
    if (isSorted(inList) == true) {
      return totalDiff(inList);
    }
    if(next == null){
      return inList.totalCount() + aminoAcidCompare(inList);
    }
    if(inList.next == null){
      return totalCount() + next.aminoAcidCompare(inList);
    }
    else {
      return 0;
    }

  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){
    return 0;
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    if (next == null) {
      return new char[]{aminoAcid};
    }
    else{
      char[] amino = next.aminoAcidList();
      char[] list = new char[amino.length + 1];

      for (int i = 0; i < amino.length; i++) {
        list[0] = amino;
        list[i + 1] = amino[i];
      }

      list[0] = aminoAcid;
      return list;

    }
  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    return counts;
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    if(next == null){
      return true;
    }
    if(aminoAcid > next.aminoAcid){
      return false;
    }
    return next.isSorted();
  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){
    AminoAcidLL list = new AminoAcidLL(inSequence.substring(0, 3));
    inSequence = inSequence;
    this.next = null;
    inSequence = inSequence.substring(3);
    return null;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList){
    AminoAcidLL sortedList = new AminoAcidLL();
    AminoAcidLL current = inList;

    boolean sorted = false;
    boolean moved = false;

    return null;
  }

}