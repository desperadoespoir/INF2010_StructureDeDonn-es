import java.util.ArrayList;

public class Monceau {
    ArrayList<Node> arbres;
    
    public Monceau() {
        arbres = new ArrayList<Node>();
    }
    
public void fusion(Monceau autre) {

	  ArrayList<Node> retenue = new ArrayList<Node>(); // arbre de retenue
	      int i = 0;
	      int j = 0; 
	     while((i < arbres.size()) || (j < autre.arbres.size())){ // pour tous les arbres dans les monceaux
	      	if (i == arbres.size()){ // Si this est le plus petit monceau on ajoute l'autre a la retenue
	      		retenue.add(autre.arbres.get(j));
	      		j++;
	      	}
	      	else if (j == autre.arbres.size()){ // si autre est le plus petit monceau on ajoute this a la retenue
	      		retenue.add(arbres.get(i));
	      		i++;
	      	}    			
	      	else if (arbres.get(i).ordre <= autre.arbres.get(j).ordre){ // On ajoute l'arbre qui a le plus petit ordre entre les deux monceaux
	      		retenue.add(arbres.get(i));
	      		i++;
	      	}
	      	else{
	      		retenue.add(autre.arbres.get(j)); // On ajoute l'arbre qui a le plus petit ordre entre les deux monceaux
	     		j++;
	      		}
	      }
	  int k = 0;
	      	while(k < retenue.size() - 1){ // on parcoure l'arbre de retenue
	      		if (retenue.get(k).ordre > retenue.get(k + 1).ordre){ // si les arbres ne sont pas plac�s par ordres, on les trie
	     			Node noeudTemporaire = retenue.get(k); // on garde en memoire l'arbre d'ordre sup�rieur
	     			retenue.set(k, retenue.get(k + 1)); // on remplace l'arbre d'ordre sup�rieur par l'arbre d'ordre inf�rieur
	      			retenue.set(k + 1, noeudTemporaire); // puis on met l'arbre sauvegard� �a la palce de l'arbre d'ordre inf�rieur
	       		}
	  else if (retenue.get(k).ordre == retenue.get(k + 1).ordre){ // on fusionne les arbres de m�mes ordres
			try{
				retenue.set(k, retenue.get(k).fusion(retenue.get(k + 1))); 
				     			}catch(DifferentOrderTrees notSameOrdre){}
				     			retenue.remove(k + 1);
				      		}
				      		else{
				      			k++;
				      		}	
				      	}
	 	arbres = retenue;
}
				 
			
    /*
     * void insert (int val) : On cree un monceau d'ordre 0 avec un nouveau noeud et on 
     * le fusionne au monceau auquel on veut ajouter la valeur
     */
        
public void insert(int val) {
    Node nouveauNoeud = new Node(val); // on cr�e un nouveau noeud avec la valeur � ins�rer
    Monceau nouveauMonceau = new Monceau();
    nouveauMonceau.arbres.add(nouveauNoeud); // on ajoute le nouveau noeud au monceau
    fusion(nouveauMonceau);
  }
  
  public boolean delete (int val) {
  	boolean delete = false;
  	ArrayList<Node> arbreNoeudInd�sirable =  new ArrayList<>();
  	ArrayList<Node> arbresTemporaires = new ArrayList<>();
  	
  	
  	for (Node arbre : arbres) {
      Node noeudInd�sirable = arbre.findValue(val);
      
      if( noeudInd�sirable != null){ // on a trouv� le noeud �a supprimer
      	arbreNoeudInd�sirable = noeudInd�sirable.delete(); // on enl�eve le noeud de l'arbre
      	arbresTemporaires.addAll(arbreNoeudInd�sirable); // on ajoute l'arbre avec le noeud supprimer aux arbres
      	delete = true;
  		}
  	}
      return delete;
  }
  
  public void print() {
      for (Node arbre : arbres){
      	System.out.print("L'ordre de l'arbre est:" + arbre.ordre + "\n");
      	arbre.print("   ");
      }
  }
  
}

