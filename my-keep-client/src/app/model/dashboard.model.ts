import { Widget } from "./widget.model";
import { DashboardType } from "./dashboardType.model";

export class Dashboard {
    id: number;
    type: DashboardType;
    widgets: Array<Widget>;

    constructor(inter: DashboardInterface = {}) {
        this.id = inter.id;
        this.type = inter.type;
        this.widgets = inter.widgets;
       
    }
}

interface DashboardInterface {
    id?: number;
    type?: DashboardType;
    widgets?: Array<Widget>;
}