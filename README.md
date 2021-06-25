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


