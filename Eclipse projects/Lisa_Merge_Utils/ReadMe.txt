README:
=======

Depending upon which merge activity you want to perform please change the 
directory to the resepective folder.

For instance, to run merge process for 1303 Grid off, change to "1303_GRID_OFF"
directory.

To run merge do the following:
 
 1. Execute RunMerge.bat from Windows command shell.

 2. Alternatively you can also run it as an Ant task.
    ant merge-process-all

Debugging

 From Windows commmand shell execute
    ant help

    It lists all the supported ant task so that you can step through each
    of the individual merge step.   


Merge Output
 - Each merge activity will generate specific spreadsheet file. Like for 1303 
   Grid off you would see LisaMatchesReport_1303_Grid_Off.csv created under
   data folder. 
 - The final merged image for 1303_Grid_Off will only have images from 1303 Grid
   off scenarios.
 - If you are running merge for 1304 Grid On, the merge image will have
   everything from 1304 Grid On plus everything from 1303 Grid On as well.
 - Once the merge process completes save the generated csv file to xlsx format
   using appropriate naming convention.

