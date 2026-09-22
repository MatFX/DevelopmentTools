module DevelopmentTools
{
	requires javafx.graphics;
	requires javafx.controls;
	requires java.xml.bind;
	requires java.desktop;
	requires javafx.swing;
	
	
	
	
	exports eu.matfx;
	
    opens eu.matfx.colorizer.item to javafx.base;
	
}