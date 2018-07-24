export class Widget {
	id: number;
    title: String;
	content: String;
	date: String;
	dragAndDrop: boolean;
	resizable: boolean;
	hover: boolean;
	x: number; y: number;
	w: number; h: number;
	xSm: number; ySm: number;
	wSm: number; hSm: number;
	xMd: number; yMd: number;
	wMd: number; hMd: number;
	xLg: number; yLg: number;
	wLg: number; hLg: number;
	xXl: number; yXl: number;
	wXl: number; hXl: number;
	dashboardId: number;

    constructor(inter: WidgetInterface = {}) {
		this.id = inter.id;
        this.title = inter.title;
		this.content = inter.title;
		this.date = inter.date;
        this.dragAndDrop = inter.dragAndDrop;
        this.resizable = inter.resizable;
        this.x = inter.x;
        this.y = inter.y;
        this.w = inter.w;
        this.h = inter.h;
      
        this.xSm = inter.xSm;
        this.ySm = inter.ySm;
        this.wSm = inter.wSm;
        this.hSm = inter.hSm;
        this.xMd = inter.xMd;
        this.yMd = inter.yMd;
        this.wMd = inter.wMd;
        this.hMd = inter.hMd;
        this.xLg = inter.xLg;
        this.yLg = inter.yLg;
        this.wLg = inter.wLg;
        this.hLg = inter.hLg;
        this.xXl = inter.xXl;
        this.yXl = inter.yXl;
        this.wXl = inter.wXl;
        this.hXl = inter.hXl;
		this.dashboardId = inter.dashboardId;
    }
}

interface WidgetInterface {
	id?: number;
    title?: String;
	content?: String;
	date?: String;
	dragAndDrop?: boolean;
	resizable?: boolean;
	hover?: boolean;
	x?: number;
	y?: number;
	w?: number;
	h?: number;
	xSm?: number;
	ySm?: number;
	wSm?: number;
	hSm?: number;
	xMd?: number;
	yMd?: number;
	wMd?: number;
	hMd?: number;
	xLg?: number;
	yLg?: number;
	wLg?: number;
	hLg?: number;
	xXl?: number;
	yXl?: number;
	wXl?: number;
	hXl?: number;
	dashboardId?: number;
}