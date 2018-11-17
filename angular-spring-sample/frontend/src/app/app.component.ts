import { Component } from '@angular/core';

// a component is state + html + css
@Component({
  selector: 'app-component',
  templateUrl: './app.component.html', // html for the component
  // template: `
  //   <h1>Hello {{city}}!</h1>  <!-- double-curly is one-way binding -->
  // `
  styleUrls: ['./app.component.css']   // style for the component's html
})
// the class encapsulates the component's state
export class AppComponent {
  city = 'Denver';
}
