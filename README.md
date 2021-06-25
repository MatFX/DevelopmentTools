# DevelopmentTools

A collection of different tools that I use.

### Change .gitconfigure file

I use two different git hub accounts for the different projects. In the Eclipse IDE is not easy to change the author before uploading the source changes.
That the reason I made this tool. 

![Bild](https://github.com/MatFX/DevelopmentTools/blob/master/screenshotChangeGitConfig.png "Bild")

The button 'Change Config Path' is to select the folder of the .configure file. After defining this path, the tool 
will searching for other files with the suffix .gitconfigure. 
The founded files are selectable over the combobox in the top of the border pane. After the selection of a item from the combobox, the 
content of file will be displayed in the center of the pane. The button 'Use select config' is to replace the content of the original .gitconfigure
whith the selected gitconfigure file.

## Converting tools

When I build a user specified UI Component in JavaFx, I draw first the sketch in Illustrator. After drawing the component, I export the sketch
as a SVG file. This SVG file is the base for the JavaFx implementation. 
To import the geometric shapes and gradients, I created three tools to make the implementation easier.
In the followed three sub headlines, I will explain the different tools. In the project folder you will find a example.svg. The converted result
is located under the example package folder. 

### Create round rectangle

![RoundRectangle](https://github.com/MatFX/DevelopmentTools/blob/master/RectangleConvert.png)

First, you must define a scale basis. In the example.svg it's the layer called base_background. From this component 
enter the width and height in the two total fields from the ui.  
The next step is to fill the other fields with the values from the rectangle to be converted("inlay" from the example.svg).
By clicking on the "Convert values" button, the code snippet will be created and shown in the text area. 

### Convert polygon points from SVG

![PolygonConvert](https://github.com/MatFX/DevelopmentTools/blob/master/PolygonConverter_.png)

The fields total width and height have the same background as in the other dialog. Accordingly, they must be filled 
with the values from the base component.
The raw data points ("arrow_up" from example.svg) are to be inserted in the lower left text area. With the button "Import/resize" the converting begins
and the result will be shown in the right bottom text area. 


### Convert stop points from SVG

![StopPoints](https://github.com/MatFX/DevelopmentTools/blob/master/StopArrayConvert.png)

The stop point converter is needed when you want to draw a colorized gradient on any geometric shape.
The values are to copy in the left text area ("Unbenannter_Verlauf_27" from example.svg). 
After button "convert" is pressed, the will be builded a code-snippet and export it to the right text area.
Remark: The opacity is after converting a part of the color hex string. 









