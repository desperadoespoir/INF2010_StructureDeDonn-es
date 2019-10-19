import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

    public int ordre;
    public Node parent;

    private int valeur;
    private ArrayList<Node> enfants;

    public Node(int valeur) {
        this.valeur = valeur;
        ordre = 0;
        this.parent = null;
        enfants = new ArrayList<Node>();
    }

    public Node(int valeur, Node parent) {
        ordre = 0;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<Node>();
    }

    public int getVal() {
        return valeur;
    }

    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    public void addEnfant(Node enfant) {
        enfants.add(enfant);
    }

    public boolean removeEnfant(Node enfant) {
        if (enfants.contains(enfant)) {
            enfants.remove(enfant);
            return true;
        }
        return false;
    }

    public void removeEnfants(ArrayList<Node> enfants) {
        this.enfants.removeAll(enfants);
    }

    public Node fusion(Node autre) throws DifferentOrderTrees {

        if (ordre != autre.ordre) // On vérifie que les arbres sont du même ordre
        	throw new DifferentOrderTrees();
        if (this.parent == null && autre.parent == null){ // on vérifie que les noeuds sont des racines
	        Node arbreFusion;
	        if (this.getVal() < autre.getVal()){//On verifie la condition val parent < val enfant
	        	arbreFusion = this;
	        	autre.parent = this;
	        	arbreFusion.addEnfant(autre);
	        }	
	        else {
	        	arbreFusion = autre;
	        	arbreFusion.addEnfant(this);
	        	this.parent = arbreFusion;
	        }
	        arbreFusion.parent=null; // nouvelle racine
	        arbreFusion.ordre += 1; // on augmente l'ordre
	        return arbreFusion; // On retourne la fusion des arbres
        }
        else
        	return null;
    }

    public void moveUp() {
    
    	Node parent = this.parent; //save parent
    	Node grandParent=this.parent.parent; // save grandparent
    	
    	//on sauve les enfants
    	ArrayList<Node> enfantsParent = parent.getEnfants();
    	ArrayList<Node> enfants = this.getEnfants();
    	
    	//enlever le noeud remonte de la liste d'enfants de son parent
    	enfantsParent.remove(this);
    
    	// les enfants du parent on maintenant le noeud remonte comme parent
    	for(Node enfant: enfantsParent){
    		enfant.parent =this;
    		
    	}
    	
    	// les enfants du noeud remonte on maintenant le parent du noeud remonte comme parent
    	for(Node enfant: enfants){
    		enfant.parent = parent;
    		
    	}
    	
   
    	//changement du parent
    	parent.enfants=enfants;
    	parent.parent=this;
    	parent.ordre++;
    	
    	//changement du noeud remonte
    	this.enfants=enfantsParent;
    	this.addEnfant(parent);
    	this.ordre--;
    	this.parent=grandParent;
    	
    	//changement du grandparent
    	if(grandParent!=null)
    	{
    		
    		grandParent.removeEnfant(parent);
    		grandParent.addEnfant(this);
    		
    	}
    	
    	
    
    	
    
    }

    public ArrayList<Node> delete() {
       while(this.parent != null)
       {
    	  this.moveUp(); 
       }
       
       for(Node enfants : this.enfants)
       {
    	   enfants.parent = null;
       }
       
       return this.enfants;
   
    }

    public void print(String tabulation) {
    	System.out.print(tabulation+this.valeur);
    	if(this.parent==null && this.getEnfants().size()==0)
    		System.out.println();
    	for(int i=0;i<this.getEnfants().size();i++){
    		if(i==0){
    			enfants.get(i).print("\t");
    			System.out.println();
    		}
    		else
    			enfants.get(i).print(tabulation+"\t");
    	
    	}
    	
    }
   

    
    public Node findValue(int valeur) {
        return findValue(this, valeur);
    }
    
    public Node findValue (Node n, int valeur) {
    	Node noeudCherche = null;
       if(this.valeur == valeur) //la valeur cherchée est sur le noeud courant
    	   return this;
       for (Node enfant : enfants) {
    	   if (this.valeur < valeur);
    	   	{
    	   		noeudCherche = enfant.findValue(valeur);
    	   		if (noeudCherche != null) // Si on a trouvé la valeur
    	   			return noeudCherche;
    	   	}
       }
       return noeudCherche;
    }
    
}