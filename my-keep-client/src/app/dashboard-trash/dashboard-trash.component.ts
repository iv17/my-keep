import { Component, OnInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { IGridsterOptions, IGridsterDraggableOptions, GridsterComponent } from 'angular2gridster';
import { DashboardService } from '../service/dashboard.service';
import { DashboardType } from '../model/dashboardType.model';
import { Widget } from '../model/widget.model';
import { interval } from 'rxjs';
import { Dashboard } from '../model/dashboard.model';

@Component({
  selector: 'app-dashboard-trash',
  templateUrl: './dashboard-trash.component.html',
  styleUrls: ['./dashboard-trash.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DashboardTrashComponent implements OnInit {

  @ViewChild(GridsterComponent) gridster: GridsterComponent;
  itemOptions = {
    minWidth: 2,
    maxWidth: 6,
    maxHeight: 6
  };
  gridsterOptions: IGridsterOptions = {
    // core configuration is default one - for smallest view. It has hidden minWidth: 0.
    lanes: 2, // amount of lanes (cells) in the grid
    direction: 'vertical', // floating top - vertical, left - horizontal
    floating: true,
    dragAndDrop: true, // enable/disable drag and drop for all items in grid
    resizable: true, // enable/disable resizing by drag and drop for all items in grid
    resizeHandles: {
      s: true,
      e: true,
      se: true
    },
    widthHeightRatio: 1, // proportion between item width and height
    lines: {
      visible: true,
      color: '#afafaf',
      width: 2
    },
    shrink: true,
    useCSSTransforms: true,
    responsiveView: true, // turn on adopting items sizes on window resize and enable responsiveOptions
    responsiveDebounce: 500, // window resize debounce time
    responsiveSizes: true,
    // List of different gridster configurations for different breakpoints.
    // Each breakpoint is defined by name stored in "breakpoint" property. There is fixed set of breakpoints
    // available to use with default minWidth assign to each.
    // - sm: 576 - Small devices
    // - md: 768 - Medium devices
    // - lg: 992 - Large devices
    // - xl: 1200 - Extra large
    // MinWidth for each breakpoint can be overwritten like it's visible below.
    // ResponsiveOptions can overwrite default configuration with any option available.
    responsiveOptions: [
      {
        breakpoint: 'sm',
        // minWidth: 480,
        lanes: 4
      },
      {
        breakpoint: 'md',
        minWidth: 768,
        lanes: 5
      },
      {
        breakpoint: 'lg',
        minWidth: 1250,
        lanes: 6
      },
      {
        breakpoint: 'xl',
        minWidth: 1800,
        lanes: 8
      }
    ]
  };
  gridsterDraggableOptions: IGridsterDraggableOptions = {
    handlerClass: 'panel-heading'
  };

  archive: Array<any>;
  trash: Array<any>;

  counter1: number = 0;
  counter2: number = 0;

  archiveID = -1;
  trashID = -1;
  
  archiveDTO: Dashboard = new Dashboard();
 
  open: boolean = false;

  check1 = interval(10000)
    .subscribe((val) => {
      if (this.counter1 > 0) {
        console.log(this.counter1);
        this.moveToArchive();
      }
    });

  ngOnInit() {

  }
  constructor(
    private dashboardService: DashboardService) {
    this.archive = JSON.parse(localStorage.getItem('archive'));
    this.trash = JSON.parse(localStorage.getItem('trash'));
    this.archiveID = JSON.parse(localStorage.getItem('archiveID'));
    this.trashID = JSON.parse(localStorage.getItem('trashID'));
  }

  public moveToArchive() {
    this.counter1 = 0;

    this.archiveDTO.widgets = JSON.parse(localStorage.getItem('archive'));
    this.archiveDTO.type = DashboardType.ARCHIVE;

    this.dashboardService.changeDashboard(this.archiveID, this.archiveDTO)
      .subscribe(
        data => {
          localStorage.removeItem('archive');
          localStorage.setItem('archive', JSON.stringify(data.widgets));
          this.archive = data.widgets;
        },
        error => {
          console.log(error);
        }
      )
  }

  public restore(widget: Widget, index: number) {
    this.archive.unshift({
      id: widget.id,
      x: widget.x, y: widget.y,
      w: widget.w, h: widget.h,
      wSm: widget.wSm, hSm: widget.hSm,
      wMd: widget.wMd, hMd: widget.hMd,
      wLg: widget.wLg, hLg: widget.hLg,
      wXl: widget.wXl, hXl: widget.hXl,
      title: widget.title,
      content: widget.content,
      dragAndDrop: true,
      resizable: true,
      date: widget.date
    });
    localStorage.removeItem('archive');
    localStorage.setItem('archive', JSON.stringify(this.archive));
    this.trash.splice(index, 1);
    localStorage.removeItem('trash');
    localStorage.setItem('trash', JSON.stringify(this.trash));
    this.counter1++;
  }
  
  public restoreAll() {
    this.trash.forEach(widget => {
      this.archive.unshift({
        id: widget.id,
        x: widget.x, y: widget.y,
        w: widget.w, h: widget.h,
        wSm: widget.wSm, hSm: widget.hSm,
        wMd: widget.wMd, hMd: widget.hMd,
        wLg: widget.wLg, hLg: widget.hLg,
        wXl: widget.wXl, hXl: widget.hXl,
        title: widget.title,
        content: widget.content,
        dragAndDrop: true,
        resizable: true,
        date: widget.date
      });
    });
    localStorage.removeItem('archive');
    localStorage.setItem('archive', JSON.stringify(this.archive));
    this.trash = [];
    localStorage.removeItem('trash');
    localStorage.setItem('trash', JSON.stringify(this.trash));
    this.counter1++;
  }

  public deleteAllForever(gridster: GridsterComponent) {
    this.dashboardService.delete(this.trashID)
      .subscribe(
        data => {
          this.trash = [];
          localStorage.removeItem('trash');
          localStorage.setItem('trash', JSON.stringify(this.trash));
          gridster.reload();
        }
      )
  }

  onReflow(event) {
    //console.log('onReflow', event);
  }
  optionsChange(options: IGridsterOptions) {
    this.gridsterOptions = options;
    //console.log('options change:', options);
  }
  itemChange($event: any) {
    //console.log('item change', $event);
  }

}
