import java.awt.PageAttributes.ColorType;
import static java.lang.Math.cos;
import static java.lang.Math.sin;


/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @authors : OBOSSOU EMA-WO, SANYAN, 1780896
 *            GNAGA, GEORGES, 
 * @date    : 25 janvier 2018
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()   
	{
		// Parcourir tous les pixels de l'image de l'objet courant et   
		// convertir chacun des pixels en négatif.
		for(int i = 0; i < height; i++)
			for(int j = 0 ; j < width; j++)	
				imageData[i][j] = imageData[i][j].Negative();	
	}
	
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()    
	{
		// Créer une image temporelle pour contenir la conversion de l'image courante. 
		AbstractPixel[][] imageComvertie = new AbstractPixel[height][width];
		
		// Convertir l'image courante en noir blanc et l'affectée à imageComvertie.
		imageComvertie = this.toBWImage().imageData;
		
		// Affecter imageComvertie à l'image courante.
		this.imageData = imageComvertie;
		imageType      = ImageType.BW;	
	}

		
	/** 
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()  
	{
		// Créer une image temporelle pour contenir la conversion de l'image courante. 
		AbstractPixel[][] imageComvertie = new AbstractPixel[height][width];
		
		// Convertir l'image courante en un format de tons de gris et l'affectée à imageComvertie.
		imageComvertie = this.toGrayImage().imageData;
		
		// L'objet courant contient maintenant une image en gris.
		this.imageData = imageComvertie;
		imageType      = ImageType.Gray;
	}
	
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()     
	{ 
		// Créer une image temporelle pour contenir la conversion de l'image courante. 
		AbstractPixel[][] imageConvertie = new AbstractPixel[height][width];
		
		// Convertir l'image courante en couleur et l'affectée à imageComvertie.
		imageConvertie = this.toColorImage().imageData;
		
		// L'objet courant contient maintenant une image en couleur.
		this.imageData = imageConvertie;
		imageType = ImageType.Color;		
	}
	
	
	/**
	 * Convertit l'image vers une image en transparente.
	 */
	public void convertToTransparentImage()     
	{
		// Créer une image temporelle pour contenir la conversion de l'image courante. 
		AbstractPixel[][] imageConvertie = new AbstractPixel[height][width];
		
		// Mettre une copie de l'image courante en transparent et l'affectée à imageComvertie.
		imageConvertie = this.toTransparentImage().imageData;
		
		// L'objet courant contient maintenant une image en transparent.
		imageData = imageConvertie;
		imageType = ImageType.Transparent;		
	}
	
	
	/**
	 * Fait pivoter l'image d'un angle en radian (angleRadian) autour du pixel pivot
	 * (row, col) = (x, y) dans le sens des aiguilles d'une montre lorsque angleRadian
	 * est positif,	ou dans le sens inverse des aiguilles d'une montre lorsque angleRadian
	 * est négatif. Les pixels vides seront en blancs.
	 * @param x           : abscisse du point de pivot (int).
	 * @param y           : ordonnée du point de pivot (int).
	 * @param angleRadian : angle de rotation (double).
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		// Créer une image temporelle pour contenir une copie de l'image courante pivotée.
		AbstractPixel[][] rotateImage = new AbstractPixel[height][width];
		
		// Mettre tous les pixels de rotateImage en blancs.
		for (int row = 0; row < height; row++) 
			for (int col = 0 ; col < width; col++)
				rotateImage[row][col] = new BWPixel(); 
	     
		// Appliquer une matrice de rotation à tous les pixels 
		// d'une copie de la matrice courante pour les faire pivoter 
		// d'un angleRadian de rotation autour du pivot (x, y).
		for (int row = 0; row < height; row++) 
		{
			for (int col = 0; col < width; col++)
			{
				// Les nouvelles positions de chaque pixel après la rotation ont été
				// obtenues du produit scalaire de la matrice de rotation et des positions
				// actuelles (row, col) de chaque pixel courant.
				int xPivoter = (int)( row*Math.sin(angleRadian) + col*Math.cos(angleRadian    ) - 
				                      x*Math.sin(angleRadian)   - y*Math.cos(angleRadian) + y );
				int yPivoter = (int)( row*Math.cos(angleRadian) - col*Math.sin(angleRadian    ) - 
						              x*Math.cos(angleRadian)   + y*Math.sin(angleRadian) + x );
				
		        // Enregistrer dans rotateImage chaque pixel pivoté qui se trouvent dans 
				// les intervalles [0, height] et [0, width]. Les pixels dépassant ces  
				// intervalles sont simplement perdus.
				if ( (yPivoter < height && yPivoter >= 0) && (xPivoter < width && xPivoter >= 0) )
					rotateImage[row][col] = imageData[yPivoter][xPivoter];							
			}
		}     

		// L'objet courant contient à présent une image en pivotée.	
		imageData = rotateImage;
	}

	
	/**
	 * Modifie la longueur et la largeur de l'image. 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException  
	{
		// Si les nouvelles largeur et hauteur sont invalides (valeurs < 0),
		// alors lancer une exception.
		if(w < 0 || h < 0)
			 throw new IllegalArgumentException();
		
		// Sinon, créer une image temporelle pour contenir une copie 
		// de l'image courante à redimensionner.
		AbstractPixel[][] temp = new AbstractPixel[h][w];

		// Définir les ratios de redimensionnement de l'image.
		double ratioW = (double)w/width;
		double ratioH = (double)h/height;

		// Parcourir l'image courante et agrandir ou rétrécir chaque pixel 
		// selon les facteurs de redimensionnement .
		for (int row = 0; row < h; row++) 
			for (int col = 0 ; col < w; col++)
				temp[row][col] = imageData[(int)(row/ratioW)][(int)(col/ratioH)];

		// L'objet courant contient à présent une image redimensionée en h fois w.
		imageData = temp;
		height = h;
		width = w;	
	}
	
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		// Parcourir l'image courante et y insérer à partir de la position 
		// (row0, col0) les pixels de l'image de l'objet source pm. Les pixels 
		// de pm qui dépassent dans l'image courante ne sont pas recopiés.
		for (int rowDest = row0, rowPM = 0; 
			rowDest < height && rowPM < pm.getHeight(); rowDest++, rowPM++)
			for (int colDest = col0, colPM = 0; 
				colDest < width && colPM < pm.getWidth(); colDest++, colPM++)
				imageData[rowDest][colDest]= pm.imageData[rowPM][colPM];  	
	}
	
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)   
	{
		// Découper l'image si les nouvelles largeur et hauteur sont valides (valeurs > 0).
		if (h > 0 && w > 0)
		{
			// Créer une image temporelle pour contenir une copie 
			// de l'image courante à découper.
			AbstractPixel[][] imageDecouper = new AbstractPixel[h][w];
				
			// Mettre tous les pixels de  imageDecouper en blancs.
			for (int row = 0; row < h; row++) 
				for (int col = 0 ; col < w; col++)
					 imageDecouper[row][col] = new BWPixel(); 
			
			// Recopier dans imageDecouper les pixels se trouvant entre les intervalles  
			// [0, h] et [0, w] de l'image courante. Les pixels en déhors de ces  
			// intervalles sont perdus. 
			for (int row = 0; row < h; row++) 
				for (int col = 0 ; col < w; col++)
					if (row < height && col < width)
						imageDecouper[row][col] = imageData[row][col];
			
			// L'objet courant contient à présent une image découpée en h fois w.	
			imageData = imageDecouper;			
			height = h; 
			width  = w; 
		 }  		
  	}

	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int colOffset, int rowOffset)  
	{	
		
	/*	
		PixelMapPlus imageTranslater = new PixelMapPlus(ImageType.BW, height, width);
		
		
		// Recopier dans imageTranslater à partir du point (translaterH, translaterW) 
		// les pixels se trouvant entre les intervalles [0, h] et [0, w] de l'image courante. 
		// Les pixels en déhors de ces intervalles sont perdus. 
		for (int row = 0; row < height; row++) 
		{
			for (int col = 0; col < width; col++)
			{
				// La position (translaterH, translaterW) dans imageTranslater à partir
				// de laquelle les pixels de l'image courante sont copiés.
				int translaterW = (int)(col + colOffset);
				int translaterH = (int)(row + rowOffset);
				
		        // Enregistrer dans imageTranslater chaque pixel translaté qui se trouvent dans 
				// les intervalles [0, height] et [0, width]. 
				if ( (translaterH < height && translaterH >= 0) && 
					 (translaterW < width  && translaterW >= 0) )
					imageTranslater.imageData[translaterH][translaterW] = imageData[row][col];							
			}		
		}
		// L'objet courant contient à présent une image découpée en h fois w.	
		imageData = imageTranslater.imageData;	
*/	
 
		
		// Créer une image temporelle pour contenir une copie 
		// de l'image courante à translater.
		AbstractPixel[][] imageTranslater = new AbstractPixel[height][width];
			
		// Mettre tous les pixels de imageTranslater en blancs.
		for (int row = 0; row < height; row++) 
			for (int col = 0 ; col < width; col++)
				imageTranslater[row][col] = new BWPixel(); 
		
		// Recopier dans imageTranslater à partir du point (translaterH, translaterW) 
		// les pixels se trouvant entre les intervalles [0, h] et [0, w] de l'image courante. 
		// Les pixels en déhors de ces intervalles sont perdus. 
		for (int row = 0; row < height; row++) 
		{
			for (int col = 0; col < width; col++)
			{
				// La position (translaterH, translaterW) dans imageTranslater à partir
				// de laquelle les pixels de l'image courante sont copiés.
				int translaterW = (int)(col + colOffset);
				int translaterH = (int)(row + rowOffset);
				
		        // Enregistrer dans imageTranslater chaque pixel translaté qui se trouvent dans 
				// les intervalles [0, height] et [0, width]. 
				if ( (translaterH < height && translaterH >= 0) && 
					 (translaterW < width  && translaterW >= 0) )
					imageTranslater[translaterH][translaterW] = imageData[row][col];							
			}
		} 

		// L'objet courant contient à présent une image découpée en h fois w.	
		imageData = imageTranslater;	
		

		
	}
	
	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException //*************************************
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();
		
		
		// compléter
		// Créer une image temporelle pour contenir une copie 
		// de l'image courante à translater.
		int zoomHeight = (int)(height/(zoomFactor));
		int zoomWidth  = (int)(width/(zoomFactor));
		
		int rowZoom = y;
		int colZoom = x;
		
//		colZoom = (colZoom - zoomWidth <= 0)? zoomWidth : colZoom;        
//		colZoom = (colZoom + zoomWidth >= width)? (width + zoomWidth - 2*colZoom) : colZoom;
	          
		
		
		PixelMapPlus imageZoom = new PixelMapPlus(imageType, zoomHeight, zoomWidth);
		
		
		// La position (translaterH, translaterW) dans imageTranslater à partir
		// de laquelle les pixels de l'image courante sont copiés.
	
		
		
		// Recopier dans imageTranslater à partir du point (translaterH, translaterW) 
		// les pixels se trouvant entre les intervalles [0, h] et [0, w] de l'image courante. 
		// Les pixels en déhors de ces intervalles sont perdus. 
		for (int row = 0; row < zoomHeight; row++) 	
			for (int col = 0; col < zoomWidth; col++)				
		        // Enregistrer dans imageTranslater chaque pixel translaté qui se trouvent dans 
				imageZoom.imageData[row][col] = imageData[row + rowZoom/2][col + colZoom/2];							
		
	

		// L'objet courant contient à présent une image découpée en h fois w.	
		imageZoom.resize(width, height );
		imageData = imageZoom.imageData;		
      

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		 
		/*
		    int semiWidth = (int) (width/(zoomFactor*2));
	        int semiHeight = (int) (height/(zoomFactor*2));
	        if(x - semiWidth <= 0)
	            x += semiWidth - x;
	        if(x + semiWidth >= width)
	            x -= width + (semiWidth - x);
	        if(y - semiHeight <= 0)
	            y += semiHeight - y;
	        if(y + semiHeight >= height)
	            y -= height + (semiHeight - y);
	        PixelMapPlus imageTemp = 
	        		new PixelMapPlus(this.imageType,(int)(height/zoomFactor),(int)(width/zoomFactor));
	        for(int row=0; row<height/zoomFactor; row++)
	        {
	            for(int col=0; col<width/zoomFactor; col++)
	            {
	                imageTemp.imageData[(int) (row)][(int) (col)] = 
	                		this.imageData[(int) (row+y-height/(2*zoomFactor))][(int) (col+x-width/(2*zoomFactor))];
	            }
	        }
	        imageTemp.resize(width, height);
	        imageData = imageTemp.imageData;
		*/
		
	}
}
