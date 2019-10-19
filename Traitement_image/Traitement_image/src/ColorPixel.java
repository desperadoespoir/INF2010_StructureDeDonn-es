/**
 * Classe de pixel en couleurs 
 * @author :
 * @date : 
 */

public class ColorPixel extends AbstractPixel
{
	public int[] rgb; // donnees de l'image
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	ColorPixel()
	{
		rgb = new int[3];
		rgb[0] = 255;
		rgb[1] = 255;
		rgb[2] = 255;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner 
	 */
	ColorPixel(int[] rgb) // *************************************
	{
		// compléter
		this.rgb = new int[3];
		/*for(int i=0; i<3;i++)
		this.rgb[i]=rgb[i];*/
		this.rgb[0] = rgb[0];
		this.rgb[1] = rgb[1];
		this.rgb[2] = rgb[2];
		
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public int moyenne() {                       // *************************************
		return ((rgb[0]+rgb[1]+ rgb[2])/3);
	}
	
	public BWPixel toBWPixel() // *************************************
	{
		// compléter
		 
		/*if(moyenne()<=127)
			return ( new BWPixel(false));
		
			return(new BWPixel(true));*/
		
		return new BWPixel((moyenne()<= 127)? false:true);
	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()  // *************************************
	{
		// compléter
		return new GrayPixel(moyenne());
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()  // *************************************
	{
		// compléter
		return new ColorPixel(rgb); 
		//return new ColorPixel(this.rgb);
	}
	
	public TransparentPixel toTransparentPixel() // *************************************
	{
		// compléter
	/*	int[] rgba= new int[4];
		for(int i=0;i<3;i++)
		rgba[i]= rgb[i];
		
		rgba[3]=255;*/
		
		int rgba[] = {rgb[0],rgb[1],rgb[2],255};
			
		return new TransparentPixel(rgba);

		}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative()    // *************************************
	{
		// compléter
		int rgb[] = {255-this.rgb[0],255-this.rgb[1],255-this.rgb[2]};
		return new ColorPixel(rgb);

	}
	
	public void setAlpha(int alpha)
	{
		//ne fait rien
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplémentaire en fin)s
	 */
	public String toString()
	{
		return  ((Integer)rgb[0]).toString() + " " + 
				((Integer)rgb[1]).toString() + " " +
				((Integer)rgb[2]).toString() + " ";
	}
}