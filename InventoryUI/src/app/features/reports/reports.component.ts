import { Component, OnInit } from '@angular/core';
// because it is referencing the tableau js library
declare var tableau: any;

@Component({
 selector: 'app-reports',
 templateUrl: './reports.component.html',
 styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit{
tableauViz: any

 constructor() { }

 ngOnInit() {
   var placeholderDiv = document.getElementById('tableauViz');
   var url = 'https://public.tableau.com/views/Test_2466/Barchartsdashboard?:embed=y&:display_count=yes';
   var options = {
     hideTabs: true,

     onFirstInteractive: function() {
       // The viz is now ready and can be safely used.
     }
   };
   this.tableauViz = new tableau.Viz(placeholderDiv, url, options);
}
ngOnDestroy()
{
  this.tableauViz.dispose();
}
}
