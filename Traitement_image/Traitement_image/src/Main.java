/**
 * Fichier principal pour le problème
 * @author Tarek Ould Bachir, Wail Khemir
 * @date : 2015-09-06
 */

public class Main 
{
	/**
	 * Fonction principale
	 * @param args (non utilise)
	 */
	public static void main(String[] args) 
	{
		/**
		 * Exercice 1
		 */
		
		PixelMap pmc = new PixelMap("./ed.ppm");
		PixelMap pmg = pmc.toGrayImage();
		PixelMap pmb = pmc.toBWImage();
		
		PixelMap pmt = pmc.toTransparentImage();
		for(int i = 0; i < pmt.height; ++i)
			for(int j = 0; j < pmt.width; ++j)
				pmt.getPixel(i, j).setAlpha(127);
		
		String wName = "Edsger Dijkstra (original)";
		new DisplayImageWindow(wName, pmc, 50, 50);
		
		wName = "Edsger Dijkstra (gris)";
		new DisplayImageWindow(wName, pmg, 50+50, 50+50);
		
		wName = "Edsger Dijkstra (B&W)";
		new DisplayImageWindow(wName, pmb, 50+100, 50+100);
		
		wName = "Edsger Dijkstra (Transparent)";
		new DisplayImageWindow(wName, pmt, 200, 200);
		
		/**
		 * Exercice 2
		 */

		PixelMapPlus pmp = new PixelMapPlus("./ed.ppm");
		
		PixelMapPlus hpmp = new PixelMapPlus( pmp );
		hpmp.zoomIn(0, 0, 2);
		hpmp.resize(hpmp.width/2, hpmp.height/2);

		PixelMapPlus gpmp = new PixelMapPlus( pmp );
		gpmp.zoomIn(0, gpmp.height, 2);
		gpmp.resize(gpmp.width/2, gpmp.height/2);
		gpmp.convertToGrayImage();
		
		PixelMapPlus bwpmp = new PixelMapPlus( pmp );
		bwpmp.zoomIn(pmp.getWidth(), 0, 2);
		bwpmp.resize(bwpmp.width/2, bwpmp.height/2);
		bwpmp.convertToBWImage();
		
		PixelMapPlus npmp = new PixelMapPlus( pmp );
		npmp.zoomIn(npmp.getWidth(), npmp.getHeight(), 2);
		npmp.resize(npmp.width/2, npmp.height/2);
		npmp.negate();
		
		pmp.inset(hpmp, 0, 0);
		pmp.inset(gpmp, pmp.getHeight()/2, 0);
		pmp.inset(bwpmp, 0, pmp.getWidth()/2);
		pmp.inset(npmp, pmp.getHeight()/2, pmp.getWidth()/2);
		
		wName = "Edsger Dijkstra";
		new DisplayImageWindow(wName, pmp);	
		
		
		/**
		 * Les tests :
		 */

		PixelMapPlus logoPoly = new PixelMapPlus("./logo.ppm");
		
		// Test pour rétrecir avec resize 
		PixelMapPlus logoResizePetit = new PixelMapPlus(logoPoly);
		logoResizePetit.resize(logoResizePetit.width/2, logoResizePetit.height/2);
		wName = "Logo Resize Grand";
		new DisplayImageWindow(wName, logoResizePetit , 700, 20);
		
		// Test pour agrandir avec resize 
		PixelMapPlus logoResizeGrand = new PixelMapPlus(logoPoly);
		logoResizeGrand.resize(logoResizeGrand.width*2, logoResizeGrand.height*2);
		wName = "Logo Resize Grand";
		new DisplayImageWindow(wName, logoResizeGrand , 700, 180);
				
		// Test pour faire pivoter l'image de 10 degres autour du pixel 
		// (row,col)=(0, 0) dans le sens des aiguilles d'une montre.
		PixelMapPlus logoRotate = new PixelMapPlus(logoPoly);
		logoRotate.rotate(0, 0, 0.174532925);
		wName = "Logo Rotate";
		new DisplayImageWindow(wName, logoRotate, 700, 680);
				
		// Test pour tester le zoom 
		PixelMapPlus dijkstra = new PixelMapPlus("./ed.ppm");
		PixelMapPlus logoZoomIn = new PixelMapPlus(dijkstra);
		logoZoomIn.zoomIn(logoZoomIn.width/2, logoZoomIn.height/4, 2);
		wName = "Dijkstra ZoomIn";
		new DisplayImageWindow(wName, logoZoomIn, 1350, 20);
				
		// Test pour faire translater l'image de x=+50, y=-50
		PixelMapPlus logoTranslate = new PixelMapPlus(logoPoly);
		logoTranslate.translate(50, -50);
		wName = "Translation x=+50, y=-50";
		new DisplayImageWindow(wName, logoTranslate, 1350, 750);
		
		// Test pour decouper l'image
		PixelMapPlus logoDecouper = new PixelMapPlus(logoPoly);
		logoDecouper.crop(logoDecouper.height*2, logoDecouper.width/2);
		wName = "Logo Decouper";
		new DisplayImageWindow(wName, logoDecouper, 1025, 630);
	}
}
