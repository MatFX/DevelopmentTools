package svg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class SVGPathResize extends Application {

    private static final double REQUIRED_WIDTH = 150.0;
    private static final double REQUIRED_HEIGHT = 80.0;

    public static void main(String[] args) {
        Application.launch(args);
    }

    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	Path path = new Path();
    	path.getElements().add(new MoveTo(139.89658,48.04976));
    	path.getElements().add(new LineTo(100.0f, 100.0f));
    	
    	//dreck
    	
    	SVGPath first = new SVGPath();
    	first.setContent("M139.89658,48.04976a4.32992,4.32992,0,0,0-1.66841-1.59667,5.437,5.437,0,0,0-2.60606-.56393,5.06863,5.06863,0,0,0-2.63943.634,5.00262,5.00262,0,0,0-1.70846,1.6901,7.25943,7.25943,0,0,0-.921,2.47259,16.06891,16.06891,0,0,0-.26861,2.99313,19.02959,19.02959,0,0,0,.26027,3.30513,7.47054,7.47054,0,0,0,.87925,2.506,4.17607,4.17607,0,0,0,1.66174,1.58833,5.61814,5.61814,0,0,0,2.62275.5439,5.10428,5.10428,0,0,0,2.63609-.624,4.85122,4.85122,0,0,0,1.71179-1.69844,7.49885,7.49885,0,0,0,.921-2.50929,16.67528,16.67528,0,0,0,.26694-3.04319,18.272,18.272,0,0,0-.26027-3.21837A7.2056,7.2056,0,0,0,139.89658,48.04976Z");
    	first.setTranslateX(-15);
    	first.setTranslateX(-13);
    	
    	//first.setContent("M169.69924,49.9311a8.93964,8.93964,0,0,0,.18436-1.12513c0-7.61652-9.56918-13.79194-21.68833-14.38193a20.08959,20.08959,0,0,0-30.16053.89218,19.57983,19.57983,0,0,0-6.13768-1.00689c-8.43883,0-15.39924,5.22234-16.7663,12.0643-9.62111,1.82086-16.66119,7.272-16.66119,13.79444,0,8.0063,10.53749,14.49663,23.53568,14.49663A34.83466,34.83466,0,0,0,114.05057,72.561c4.31743,3.17583,10.7206,5.2382,17.97131,5.2382a31.84224,31.84224,0,0,0,16.93627-4.46551c.83463.05506,1.64777.15558,2.50617.15558C164.46272,73.48931,175,66.999,175,58.99267,175,55.54427,172.9637,52.41975,169.69924,49.9311ZM123.72465,62.32637a3.67779,3.67779,0,0,1-.07341.52055,1.48362,1.48362,0,0,1-.13013.37372,1.91731,1.91731,0,0,1-.26028.3537,3.38993,3.38993,0,0,1-.69239.51388,7.98875,7.98875,0,0,1-1.25464.59729,10.36352,10.36352,0,0,1-1.69344.47716,10.5141,10.5141,0,0,1-2.06049.1902,10.26042,10.26042,0,0,1-3.90743-.71074,7.68557,7.68557,0,0,1-2.96477-2.12556,9.65052,9.65052,0,0,1-1.872-3.53036,16.73817,16.73817,0,0,1-.65235-4.92683,16.504,16.504,0,0,1,.71742-5.082,10.77073,10.77073,0,0,1,2.0021-3.73058,8.40194,8.40194,0,0,1,3.08656-2.28906,9.85571,9.85571,0,0,1,3.96582-.78082,8.89106,8.89106,0,0,1,1.69344.15683,9.70729,9.70729,0,0,1,1.50658.4071,7.3664,7.3664,0,0,1,1.2463.5806,3.74971,3.74971,0,0,1,.77248.56393,1.99674,1.99674,0,0,1,.302.39041,1.45112,1.45112,0,0,1,.13014.39875,4.65268,4.65268,0,0,1,.07341.57226q.025.33035.025.81586c0,.347-.01168.64234-.03337.88426a2.454,2.454,0,0,1-.11345.59062.82605.82605,0,0,1-.1952.32867.37583.37583,0,0,1-.26028.10511,1.09519,1.09519,0,0,1-.619-.30365,10.31242,10.31242,0,0,0-.96934-.67737,7.683,7.683,0,0,0-1.41649-.67571,5.69381,5.69381,0,0,0-1.96372-.30365,4.54507,4.54507,0,0,0-2.239.54557,4.90978,4.90978,0,0,0-1.67676,1.56163,7.54921,7.54921,0,0,0-1.0511,2.45424,15.17767,15.17767,0,0,0,.025,6.672,6.83726,6.83726,0,0,0,1.09114,2.38583,4.4546,4.4546,0,0,0,1.71013,1.3948,5.33327,5.33327,0,0,0,2.25569.46048,6.08292,6.08292,0,0,0,1.9704-.287,7.83262,7.83262,0,0,0,1.42482-.634,11.45806,11.45806,0,0,0,.97769-.624,1.176,1.176,0,0,1,.594-.277.43724.43724,0,0,1,.26027.07008.52034.52034,0,0,1,.1635.27695,2.923,2.923,0,0,1,.09677.58061c.02169.25026.03337.58061.03337.99771C123.74968,61.87924,123.74134,62.12282,123.72465,62.32637Zm21.16383-3.824a10.27415,10.27415,0,0,1-1.9387,3.73724,8.33539,8.33539,0,0,1-3.18333,2.34246,11.14113,11.14113,0,0,1-4.38793.80751,12.21117,12.21117,0,0,1-4.29116-.68739,7.42449,7.42449,0,0,1-3.05486-2.08885,9.14532,9.14532,0,0,1-1.83192-3.57374,19.09086,19.09086,0,0,1-.61064-5.13538,17.10912,17.10912,0,0,1,.65068-4.9018,10.23172,10.23172,0,0,1,1.9387-3.70221,8.4677,8.4677,0,0,1,3.185-2.34245,11.07094,11.07094,0,0,1,4.40461-.81585,12.13024,12.13024,0,0,1,4.22442.67737,7.41067,7.41067,0,0,1,3.06321,2.08051,9.22647,9.22647,0,0,1,1.85527,3.54872,18.01408,18.01408,0,0,1,.62733,5.057A17.66079,17.66079,0,0,1,144.88848,58.50237Zm11.99589,9.58336a1.58215,1.58215,0,0,1-.07341.357c-.03337.09344-.07675.4388-.13014.4755-.05339.04-.11345.33869-.18019.33869h-7.44779c-.14682,0-.27362-.292-.3804-.322-.10678-.0267-.19354-.22274-.26027-.30282a1.159,1.159,0,0,1-.14682-.4267,3.79729,3.79729,0,0,1-.04338-.64213,5.48421,5.48421,0,0,1,.03-.62138,1.78975,1.78975,0,0,1,.11011-.45584,2.05462,2.05462,0,0,1,.20355-.38809,4.06164,4.06164,0,0,1,.327-.4126L151.135,63.125a12.55385,12.55385,0,0,0,1.08113-1.35863,8.16171,8.16171,0,0,0,.64067-1.12145,4.30783,4.30783,0,0,0,.31366-.93111,4.19885,4.19885,0,0,0,.08676-.81092,2.08752,2.08752,0,0,0-.10678-.66739,1.625,1.625,0,0,0-.31033-.5506,1.45647,1.45647,0,0,0-.51387-.36706,1.87939,1.87939,0,0,0-.73076-.13347,3.15667,3.15667,0,0,0-1.04777.16016,4.7186,4.7186,0,0,0-.80083.36038,5.70642,5.70642,0,0,0-.57394.36037.716.716,0,0,1-.36038.16684.21717.21717,0,0,1-.15683-.06673.45379.45379,0,0,1-.10344-.21356,2.60214,2.60214,0,0,1-.06673-.40042c-.01669-.16684-.02336-.367-.02336-.61064,0-.1635.00333-.30031.01335-.41043a1.9089,1.9089,0,0,1,.04671-.287.83922.83922,0,0,1,.08008-.21356,1.13206,1.13206,0,0,1,.17352-.22023,2.3285,2.3285,0,0,1,.46048-.32534,5.74767,5.74767,0,0,1,.82753-.38374,7.61924,7.61924,0,0,1,1.08114-.31366,5.81425,5.81425,0,0,1,1.23129-.12847,5.19136,5.19136,0,0,1,1.76518.27195,3.44917,3.44917,0,0,1,1.258.75913,3.03583,3.03583,0,0,1,.74411,1.14787,4.14149,4.14149,0,0,1,.24693,1.43484,7.23562,7.23562,0,0,1-.12013,1.32138,5.30332,5.30332,0,0,1-.50052,1.4048,11.18407,11.18407,0,0,1-1.07112,1.66174c-.46049.60731-1.07112,1.02107-1.83192,1.82191l-1.51158,1.35809h5.10534c.06674,0,.1268.317.18353.36038.05672.04.10678.2536.14682.34369a1.98421,1.98421,0,0,1,.09009.42378,4.50315,4.50315,0,0,1,.03.55391A5.37063,5.37063,0,0,1,156.88437,68.08573Z");
        
    	
    	SVGPath svg = new SVGPath();
        //svg.setContent("M 100 100 L 300 100 L 200 300 z");
        
    	svg.setContent("M169.69924,49.9311a8.93964,8.93964,0,0,0,.18436-1.12513c0-7.61652-9.56918-13.79194-21.68833-14.38193a20.08959,20.08959,0,0,0-30.16053.89218,19.57983,19.57983,0,0,0-6.13768-1.00689c-8.43883,0-15.39924,5.22234-16.7663,12.0643-9.62111,1.82086-16.66119,7.272-16.66119,13.79444,0,8.0063,10.53749,14.49663,23.53568,14.49663A34.83466,34.83466,0,0,0,114.05057,72.561c4.31743,3.17583,10.7206,5.2382,17.97131,5.2382a31.84224,31.84224,0,0,0,16.93627-4.46551c.83463.05506,1.64777.15558,2.50617.15558C164.46272,73.48931,175,66.999,175,58.99267,175,55.54427,172.9637,52.41975,169.69924,49.9311ZM123.72465,62.32637a3.67779,3.67779,0,0,1-.07341.52055,1.48362,1.48362,0,0,1-.13013.37372,1.91731,1.91731,0,0,1-.26028.3537,3.38993,3.38993,0,0,1-.69239.51388,7.98875,7.98875,0,0,1-1.25464.59729,10.36352,10.36352,0,0,1-1.69344.47716,10.5141,10.5141,0,0,1-2.06049.1902,10.26042,10.26042,0,0,1-3.90743-.71074,7.68557,7.68557,0,0,1-2.96477-2.12556,9.65052,9.65052,0,0,1-1.872-3.53036,16.73817,16.73817,0,0,1-.65235-4.92683,16.504,16.504,0,0,1,.71742-5.082,10.77073,10.77073,0,0,1,2.0021-3.73058,8.40194,8.40194,0,0,1,3.08656-2.28906,9.85571,9.85571,0,0,1,3.96582-.78082,8.89106,8.89106,0,0,1,1.69344.15683,9.70729,9.70729,0,0,1,1.50658.4071,7.3664,7.3664,0,0,1,1.2463.5806,3.74971,3.74971,0,0,1,.77248.56393,1.99674,1.99674,0,0,1,.302.39041,1.45112,1.45112,0,0,1,.13014.39875,4.65268,4.65268,0,0,1,.07341.57226q.025.33035.025.81586c0,.347-.01168.64234-.03337.88426a2.454,2.454,0,0,1-.11345.59062.82605.82605,0,0,1-.1952.32867.37583.37583,0,0,1-.26028.10511,1.09519,1.09519,0,0,1-.619-.30365,10.31242,10.31242,0,0,0-.96934-.67737,7.683,7.683,0,0,0-1.41649-.67571,5.69381,5.69381,0,0,0-1.96372-.30365,4.54507,4.54507,0,0,0-2.239.54557,4.90978,4.90978,0,0,0-1.67676,1.56163,7.54921,7.54921,0,0,0-1.0511,2.45424,15.17767,15.17767,0,0,0,.025,6.672,6.83726,6.83726,0,0,0,1.09114,2.38583,4.4546,4.4546,0,0,0,1.71013,1.3948,5.33327,5.33327,0,0,0,2.25569.46048,6.08292,6.08292,0,0,0,1.9704-.287,7.83262,7.83262,0,0,0,1.42482-.634,11.45806,11.45806,0,0,0,.97769-.624,1.176,1.176,0,0,1,.594-.277.43724.43724,0,0,1,.26027.07008.52034.52034,0,0,1,.1635.27695,2.923,2.923,0,0,1,.09677.58061c.02169.25026.03337.58061.03337.99771C123.74968,61.87924,123.74134,62.12282,123.72465,62.32637Zm21.16383-3.824a10.27415,10.27415,0,0,1-1.9387,3.73724,8.33539,8.33539,0,0,1-3.18333,2.34246,11.14113,11.14113,0,0,1-4.38793.80751,12.21117,12.21117,0,0,1-4.29116-.68739,7.42449,7.42449,0,0,1-3.05486-2.08885,9.14532,9.14532,0,0,1-1.83192-3.57374,19.09086,19.09086,0,0,1-.61064-5.13538,17.10912,17.10912,0,0,1,.65068-4.9018,10.23172,10.23172,0,0,1,1.9387-3.70221,8.4677,8.4677,0,0,1,3.185-2.34245,11.07094,11.07094,0,0,1,4.40461-.81585,12.13024,12.13024,0,0,1,4.22442.67737,7.41067,7.41067,0,0,1,3.06321,2.08051,9.22647,9.22647,0,0,1,1.85527,3.54872,18.01408,18.01408,0,0,1,.62733,5.057A17.66079,17.66079,0,0,1,144.88848,58.50237Zm11.99589,9.58336a1.58215,1.58215,0,0,1-.07341.357c-.03337.09344-.07675.4388-.13014.4755-.05339.04-.11345.33869-.18019.33869h-7.44779c-.14682,0-.27362-.292-.3804-.322-.10678-.0267-.19354-.22274-.26027-.30282a1.159,1.159,0,0,1-.14682-.4267,3.79729,3.79729,0,0,1-.04338-.64213,5.48421,5.48421,0,0,1,.03-.62138,1.78975,1.78975,0,0,1,.11011-.45584,2.05462,2.05462,0,0,1,.20355-.38809,4.06164,4.06164,0,0,1,.327-.4126L151.135,63.125a12.55385,12.55385,0,0,0,1.08113-1.35863,8.16171,8.16171,0,0,0,.64067-1.12145,4.30783,4.30783,0,0,0,.31366-.93111,4.19885,4.19885,0,0,0,.08676-.81092,2.08752,2.08752,0,0,0-.10678-.66739,1.625,1.625,0,0,0-.31033-.5506,1.45647,1.45647,0,0,0-.51387-.36706,1.87939,1.87939,0,0,0-.73076-.13347,3.15667,3.15667,0,0,0-1.04777.16016,4.7186,4.7186,0,0,0-.80083.36038,5.70642,5.70642,0,0,0-.57394.36037.716.716,0,0,1-.36038.16684.21717.21717,0,0,1-.15683-.06673.45379.45379,0,0,1-.10344-.21356,2.60214,2.60214,0,0,1-.06673-.40042c-.01669-.16684-.02336-.367-.02336-.61064,0-.1635.00333-.30031.01335-.41043a1.9089,1.9089,0,0,1,.04671-.287.83922.83922,0,0,1,.08008-.21356,1.13206,1.13206,0,0,1,.17352-.22023,2.3285,2.3285,0,0,1,.46048-.32534,5.74767,5.74767,0,0,1,.82753-.38374,7.61924,7.61924,0,0,1,1.08114-.31366,5.81425,5.81425,0,0,1,1.23129-.12847,5.19136,5.19136,0,0,1,1.76518.27195,3.44917,3.44917,0,0,1,1.258.75913,3.03583,3.03583,0,0,1,.74411,1.14787,4.14149,4.14149,0,0,1,.24693,1.43484,7.23562,7.23562,0,0,1-.12013,1.32138,5.30332,5.30332,0,0,1-.50052,1.4048,11.18407,11.18407,0,0,1-1.07112,1.66174c-.46049.60731-1.07112,1.02107-1.83192,1.82191l-1.51158,1.35809h5.10534c.06674,0,.1268.317.18353.36038.05672.04.10678.2536.14682.34369a1.98421,1.98421,0,0,1,.09009.42378,4.50315,4.50315,0,0,1,.03.55391A5.37063,5.37063,0,0,1,156.88437,68.08573Z");
    	//svg.setContent("M139.89658,48.04976a4.32992,4.32992,0,0,0-1.66841-1.59667,5.437,5.437,0,0,0-2.60606-.56393,5.06863,5.06863,0,0,0-2.63943.634,5.00262,5.00262,0,0,0-1.70846,1.6901,7.25943,7.25943,0,0,0-.921,2.47259,16.06891,16.06891,0,0,0-.26861,2.99313,19.02959,19.02959,0,0,0,.26027,3.30513,7.47054,7.47054,0,0,0,.87925,2.506,4.17607,4.17607,0,0,0,1.66174,1.58833,5.61814,5.61814,0,0,0,2.62275.5439,5.10428,5.10428,0,0,0,2.63609-.624,4.85122,4.85122,0,0,0,1.71179-1.69844,7.49885,7.49885,0,0,0,.921-2.50929,16.67528,16.67528,0,0,0,.26694-3.04319,18.272,18.272,0,0,0-.26027-3.21837A7.2056,7.2056,0,0,0,139.89658,48.04976Z");
    	svg.setTranslateX(-15);
    	svg.setTranslateX(-13);
    	
    	//resize(svg, REQUIRED_WIDTH, REQUIRED_HEIGHT);
        Scene scene = new Scene(new StackPane(svg, first), 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void resize(SVGPath svg, double width, double height) {

        double originalWidth = svg.prefWidth(-1);
        double originalHeight = svg.prefHeight(originalWidth);

        double scaleX = width / originalWidth;
        double scaleY = height / originalHeight;

        svg.setScaleX(scaleX);
        svg.setScaleY(scaleY);
    }
}