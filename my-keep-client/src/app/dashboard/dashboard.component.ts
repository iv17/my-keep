import { Component, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { GridsterComponent, IGridsterOptions, IGridsterDraggableOptions } from 'angular2gridster';
import { DashboardService } from '../service/dashboard.service';
import { MatDialog } from '@angular/material';
import { WidgetUpdateComponent } from '../widget-update/widget-update.component';
import { Widget } from '../model/widget.model';
import { DashboardType } from '../model/dashboardType.model';
import { interval } from 'rxjs';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class DashboardComponent implements OnInit {

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

    notesDTO = { widgets: [], type: DashboardType.NOTES };
    archiveDTO = { widgets: [], type: DashboardType.ARCHIVE };
    trashDTO = { widgets: [], type: DashboardType.TRASH };

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
                this.update();
            }
        });
    check2 = interval(10000)
        .subscribe((val) => {
            if (this.counter2 > 0) {
                console.log(this.counter2);
                this.moveToArchive();
            }
        });

    ngOnInit() {
        this.notes = JSON.parse(localStorage.getItem('notes'));
        this.archive = JSON.parse(localStorage.getItem('archive'));
        this.notesID = JSON.parse(localStorage.getItem('notesID'));
        this.archiveID = JSON.parse(localStorage.getItem('archiveID'));
    }

    constructor(
        private dashboardService: DashboardService,
        public dialog: MatDialog) { }

    public populate(gridster: GridsterComponent) {
        this.dashboardService.getDashboard(this.notesID)
            .subscribe(
                data => {
                    localStorage.removeItem('notes');
                    localStorage.setItem('notes', JSON.stringify(data.widgets));
                    this.notes = data.widgets;
                    this.search = "";
                    gridster.reload();
                }
            )
    }

    public update() {
        this.counter1 = 0;

        this.notesDTO.widgets = JSON.parse(localStorage.getItem('notes'));
        
        this.dashboardService.update(this.notesID, this.notesDTO)
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

    public moveToArchive() {
        this.counter2 = 0;

        this.archiveDTO.widgets = JSON.parse(localStorage.getItem('archive'));
       
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

    public moveToTrash() {
        this.counter2 = 0;

        this.trashDTO.widgets = JSON.parse(localStorage.getItem('archive'));
       
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

    public searchIt($event, gridster: GridsterComponent) {
        if ($event.key === "Enter") {
            this.dashboardService.search(this.notesID, this.search)
                .subscribe(
                    data => {
                        localStorage.removeItem('notes');
                        localStorage.setItem('notes', JSON.stringify(data.widgets));
                        this.notes = data.widgets;
                        gridster.reload();
                    }
                )
        }
    }

    public openDialog(widget: Widget, gridster: GridsterComponent): void {
        const dialogRef = this.dialog.open(WidgetUpdateComponent, {
            data: { title: widget.title, content: widget.content }
        });
        dialogRef.afterClosed().subscribe(result => {
            widget.title = result.title;
            widget.content = result.content;
            localStorage.removeItem('notes');
            localStorage.setItem('notes', JSON.stringify(this.notes));
            this.counter1++;
            //gridster.reload();
            
        });

    }

    public pin(widget: Widget, gridster: GridsterComponent): void {
        widget.dragAndDrop = false;
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        this.counter1++;
        //gridster.reload();
    }

    public unpin(widget: Widget, gridster: GridsterComponent): void {
        widget.dragAndDrop = true;
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        this.counter1++;
        //gridster.reload();
    }

    public addWidgetWithData($event) {
        if ($event.key === "Enter") {
            this.notes.unshift({
                id: -1,
                x: 0, y: 0,
                w: 2, h: 2,
                wSm: 2, hSm: 2,
                wMd: 2, hMd: 2,
                wLg: 2, hLg: 2,
                wXl: 2, hXl: 2,
                title: this.title,
                content: this.content,
                dragAndDrop: true,
                resizable: true,
                dashboardId: this.notesID
            });
            this.open = false;
            this.title = '';
            this.content = '';

            this.counter1++;
        }
    }

    public addWidgetWithoutData() {
        this.notes.unshift({
            id: -1,
            x: 0, y: 0,
            w: 2, h: 2,
            wSm: 2, hSm: 1,
            wMd: 2, hMd: 2,
            wLg: 2, hLg: 2,
            wXl: 2, hXl: 2,
            title: '',
            content: '',
            dragAndDrop: true,
            resizable: true,
            dashboardId: this.notesID
        });
        this.counter1++;
    }

    public remove(widget: Widget, index: number) {
        this.archive.unshift({
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
            dashboardId: this.archiveID
        });
        localStorage.removeItem('archive');
        localStorage.setItem('archive', JSON.stringify(this.archive));
        this.notes.splice(index, 1);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        this.counter2++;
    }

    public delete(widget: Widget, index: number) {
        this.trash.unshift({
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
            dashboardId: this.trashID
        });
        localStorage.removeItem('trash');
        localStorage.setItem('trash', JSON.stringify(this.trash));
        this.notes.splice(index, 1);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        this.counter2++;
    }

    public removeAllWidgets() {
        this.notes.forEach(widget => {
            this.archive.unshift({
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
                dashboardId: this.archiveID
            });
        });
        localStorage.removeItem('archive');
        localStorage.setItem('archive', JSON.stringify(this.archive));
        this.notes = [];
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        this.counter2++;
    }

    public onhover(widget: Widget) {
        widget.hover = true;
    }
    public onleave(widget: Widget) {
        widget.hover = false;
    }
    public onopen() {
        this.open = true;
    }
    public onclose() {
        this.open = false;
    }

    newWidget: Widget;
    xSmChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.x = widget.xSm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    ySmChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.y = widget.ySm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    hSmChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.h = widget.hSm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    wSmChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.w = widget.wSm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    xMdChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.x = widget.xMd;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;

    }
    yMdChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.y = widget.yMd;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    hMdChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.h = widget.hMd;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    wMdChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.w = widget.wMd;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    xLgChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.x = widget.xSm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    yLgChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.y = widget.ySm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    hLgChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.h = widget.hSm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    wLgChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.w = widget.wSm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }

    xXlChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.x = widget.xSm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    yXlChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.y = widget.ySm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    hXlChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.h = widget.hSm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
    }
    wXlChange(widget: Widget, index: number) {
        this.notes.splice(index, 1);
        this.newWidget = widget;
        this.newWidget.w = widget.wSm;
        this.notes.unshift(this.newWidget);
        localStorage.removeItem('notes');
        localStorage.setItem('notes', JSON.stringify(this.notes));
        //this.counter1++;
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
        //this.counter1++;
    }

}
