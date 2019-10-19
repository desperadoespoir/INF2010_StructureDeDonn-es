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
	      		if (retenue.get(k).ordre > retenue.get(k + 1).ordre){ // si les arbres ne sont pas placés par ordres, on les trie
	     			Node noeudTemporaire = retenue.get(k); // on garde en memoire l'arbre d'ordre supérieur
	     			retenue.set(k, retenue.get(k + 1)); // on remplace l'arbre d'ordre supérieur par l'arbre d'ordre inférieur
	      			retenue.set(k + 1, noeudTemporaire); // puis on met l'arbre sauvegardé èa la palce de l'arbre d'ordre inférieur
	       		}
	  else if (retenue.get(k).ordre == retenue.get(k + 1).ordre){ // on fusionne les arbres de mêmes ordres
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
    Node nouveauNoeud = new Node(val); // on crée un nouveau noeud avec la valeur à insérer
    Monceau nouveauMonceau = new Monceau();
    nouveauMonceau.arbres.add(nouveauNoeud); // on ajoute le nouveau noeud au monceau
    fusion(nouveauMonceau);
  }
  
  public boolean delete (int val) {
  	boolean delete = false;
  	ArrayList<Node> arbreNoeudIndésirable =  new ArrayList<>();
  	ArrayList<Node> arbresTemporaires = new ArrayList<>();
  	
  	
  	for (Node arbre : arbres) {
      Node noeudIndésirable = arbre.findValue(val);
      
      if( noeudIndésirable != null){ // on a trouvé le noeud èa supprimer
      	arbreNoeudIndésirable = noeudIndésirable.delete(); // on enlèeve le noeud de l'arbre
      	arbresTemporaires.addAll(arbreNoeudIndésirable); // on ajoute l'arbre avec le noeud supprimer aux arbres
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

