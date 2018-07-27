import { Component, OnInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { GridsterComponent, IGridsterOptions, IGridsterDraggableOptions } from 'angular2gridster';
import { DashboardService } from '../service/dashboard.service';
import { DashboardType } from '../model/dashboardType.model';
import { Widget } from '../model/widget.model';
import { interval } from 'rxjs';

@Component({
  selector: 'app-dashboard-archive',
  templateUrl: './dashboard-archive.component.html',
  styleUrls: ['./dashboard-archive.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DashboardArchiveComponent implements OnInit {

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

  notes: Array<any>;
  archive: Array<any>;
  trash: Array<any>;

  notesID = -1;
  archiveID = -1;
  trashID = -1;
  notesDTO = { widgets: [], type: '' };
  trashDTO = { widgets: [], type: '' };

  open: boolean = false;

  title: string;
  content: string;

  search: string;

  counter1: number = 0;
  counter2: number = 0;

  check1 = interval(10000)
    .subscribe((val) => {
      if (this.counter1 > 0) {
        console.log(this.counter1);
        this.moveToNotes();
      }
    });
  check2 = interval(10000)
    .subscribe((val) => {
      if (this.counter2 > 0) {
        console.log(this.counter2);
        this.moveToTrash();
      }
    });

  ngOnInit() {

  }
  constructor(
    private dashboardService: DashboardService) {
    this.notes = JSON.parse(localStorage.getItem('notes'));
    this.archive = JSON.parse(localStorage.getItem('archive'));
    this.trash = JSON.parse(localStorage.getItem('trash'));
    this.notesID = JSON.parse(localStorage.getItem('notesID'));
    this.archiveID = JSON.parse(localStorage.getItem('archiveID'));
    this.trashID = JSON.parse(localStorage.getItem('trashID'));
  }

  public moveToNotes() {
    this.counter1 = 0;

    this.notesDTO.widgets = JSON.parse(localStorage.getItem('notes'));
    this.notesDTO.type = DashboardType.NOTES.toString();

    this.dashboardService.changeDashboard(this.notesID, this.notesDTO)
      .subscribe(
        data => {
          localStorage.removeItem('notes');
          localStorage.setItem('notes', JSON.stringify(data.widgets));
          this.notes = data.widgets;
        },
        error => {
          console.log(error);
        }
      )
  }

  public moveToTrash() {
    this.counter2 = 0;

    this.trashDTO.widgets = JSON.parse(localStorage.getItem('trash'));
    this.trashDTO.type = DashboardType.TRASH.toString();

    this.dashboardService.changeDashboard(this.trashID, this.trashDTO)
      .subscribe(
        data => {
          localStorage.removeItem('trash');
          localStorage.setItem('trash', JSON.stringify(data.widgets));
          this.trash = data.widgets;
        },
        error => {
          console.log(error);
        }
      )
  }

  public restore(widget: Widget, index: number) {
    this.notes.unshift({
      id: widget.id,
      x: widget.x, y: widget.y,
      xSm: widget.xSm, ySm: widget.ySm,
      xMd: widget.xMd, yMd: widget.yMd,
      xLg: widget.xLg, yLg: widget.yLg,
      xXl: widget.xXl, yXl: widget.yXl,
      w: widget.w, h: widget.h,
      wSm: widget.wSm, hSm: widget.hSm,
      wMd: widget.wMd, hMd: widget.hMd,
      wLg: widget.wLg, hLg: widget.hLg,
      wXl: widget.wXl, hXl: widget.hXl,
      title: widget.title,
      content: widget.content,
      dragAndDrop: true,
      resizable: true,
      date: widget.date,
      dashboardId: this.notesID
    });
    localStorage.removeItem('notes');
    localStorage.setItem('notes', JSON.stringify(this.notes));
    this.archive.splice(index, 1);
    localStorage.removeItem('archive');
    localStorage.setItem('archive', JSON.stringify(this.archive));
    this.counter1++;
  }

  public restoreAll() {
    this.archive.forEach(widget => {
      this.notes.unshift({
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
        date: widget.date,
        dashboardId: this.notesID
      });
    });

    localStorage.removeItem('notes');
    localStorage.setItem('notes', JSON.stringify(this.notes));
    this.archive = [];
    localStorage.removeItem('archive');
    localStorage.setItem('archive', JSON.stringify(this.archive));
    this.counter1++;
  }
  
  public trashIt(widget: Widget, index: number) {
    this.trash.unshift({
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
      date: widget.date,
      dashboardId: this.trashID
    });
    localStorage.removeItem('trash');
    localStorage.setItem('trash', JSON.stringify(this.trash));
    this.archive.splice(index, 1);
    localStorage.removeItem('archive');
    localStorage.setItem('archive', JSON.stringify(this.archive));
    this.counter2++;
  }
  public trashAll() {
    this.archive.forEach(widget => {
      this.trash.unshift({
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
        date: widget.date,
        dashboardId: this.trashID
      });
    });
    localStorage.removeItem('trash');
    localStorage.setItem('trash', JSON.stringify(this.trash));
    this.archive = [];
    localStorage.removeItem('archive');
    localStorage.setItem('archive', JSON.stringify(this.archive));
    this.counter2++;
  }

  onReflow($event) {
    //console.log('onReflow', $event);
  }
  optionsChange(options: IGridsterOptions) {
    this.gridsterOptions = options;
    //console.log('options change:', options);
  }
  itemChange($event) {
    //console.log('item change', $event);
  }


}
