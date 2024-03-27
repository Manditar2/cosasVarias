import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  Logged = true
  sidebar_options = [
    {
      id: 1,
      name: "Trace router",
      url: 'assets/img/tcp_image.jpg'
    },
    {
      id: 2,
      name: "HTTP and Wget",
      url: 'assets/img/wget_image.jpg'
    },
    {
      id: 3,
      name: "Proxy and Redis",
      url: 'assets/img/girl_sitting.jpg'
    },
    {
      id: 4,
      name: "MultiThreading",
      url: 'assets/img/new_image.jpg'
    },
    {
      id: 5,
      name: "Análisis en Frecuencia",
      url: 'assets/img/warriorChart_image.jpg'
    }
  ]
}
